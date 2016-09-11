package CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.example.haiyuan1995.myapplication.R;

/**
 * Created by haiyuan 1995 on 2016/9/10.
 * 自定义控件，继承自View，用于显示温度趋势图
 */

public class WeatherChartView extends View {

    /**
     * x轴集合
     */
    private float mXAxis[] = new float[6];

    /**
     * 白天y轴集合
     */
    private float mYAxisDay[] = new float[6];

    /**
     * 夜间y轴集合
     */
    private float mYAxisNight[] = new float[6];

    /**
     * x,y轴集合数
     */
    private static final int LENGTH = 6;

    /**
     * 白天温度集合
     */
    private int mTempDay[] = new int[6];

    /**
     * 夜间温度集合
     */
    private int mTempNight[] = new int[6];

    /**
     * 控件高
     */
    private int mHeight;

    /**
     * 字体大小
     */
    private float mTextSize;

    /**
     * 圓半径
     */
    private float mRadius;

    /**
     * 圓半径今天
     */
    private float mRadiusToday;

    /**
     * 文字移动位置距离
     */
    private float mTextSpace;

    /**
     * 线的大小
     */
    private float mStokeWidth;


    /**
     * 白天折线颜色
     */
    private int mColorDay;

    /**
     * 夜间折线颜色
     */
    private int mColorNight;

    /**
     * 字体颜色
     */
    private int mTextColor;

    /**
     * 屏幕密度
     */
    private float mDensity;

    /**
     * 控件边的空白空间
     */
    private float mSpace;




    public WeatherChartView(Context context) {
        super(context);
    }

    public WeatherChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }



    public WeatherChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    //自定义控件初始化
    private void init(Context context,AttributeSet attrs) {
        TypedArray ta=context.obtainStyledAttributes(attrs, R.styleable.WeatherChartView);
        //字体缩放比例，用于适配
        float densityText=getResources().getDisplayMetrics().scaledDensity;

        //字体大小，默认为14*densityText
        mTextSize=ta.getDimensionPixelSize(R.styleable.WeatherChartView_textSize, (int) (14*densityText));

        //白天折线颜色默认为主题中的特点色（Accent）,夜间为主要色colorPrimary
        /**注意：getResources().getColor(R.color.colorPrimary)旧版的方法已过时，
         * 用新版的ContextCompat.getColor(context,android.R.color.white)代替，
         * 该方法同时兼容新旧版
         */
        mColorDay=ta.getColor(R.styleable.WeatherChartView_dayColor,
                ContextCompat.getColor(context,R.color.colorAccent));
        mColorNight=ta.getColor(R.styleable.WeatherChartView_nightColor,
                ContextCompat.getColor(context,R.color.colorPrimary));

        //文字颜色
        mTextColor=ta.getColor(R.styleable.WeatherChartView_textColor,
                ContextCompat.getColor(context,android.R.color.white));

        //回收资源，令其可以被复用，节省内存开支
        //http://blog.csdn.net/Monicabg/article/details/45014327
        ta.recycle();

        //屏幕密度
        mDensity=getResources().getDisplayMetrics().density;

        mRadius=mDensity*3;//圆形点的大小
        mRadiusToday=mDensity*5;//表示今天的圆形点，稍微大点
        mSpace=mDensity*3;//控件边的空白空间
        mTextSpace=mDensity*10;
        mStokeWidth=mDensity*2;//线的宽度
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mHeight==0) {
            // 设置控件高度，x轴集合
            setHeightAndXAxis();
        }

        // 计算y轴集合数值
        computeYAxisValues();
        // 画白天折线图
        drawChart(canvas, mColorDay, mTempDay, mYAxisDay, 0);
        // 画夜间折线图
        drawChart(canvas, mColorNight, mTempNight, mYAxisNight, 1);

    }


    /**
     * 绘制折线图
     * @param canvas 画布
     * @param color  画图颜色
     * @param temp   温度集合
     * @param yAxis  y轴集合
     * @param type   折线种类：0，白天；1，夜间
     * **/
    private void drawChart(Canvas canvas, int color, int[] temp, float[] yAxis, int type) {

        // 线画笔
        Paint linePaint = new Paint();
        // 抗锯齿
        linePaint.setAntiAlias(true);
        // 线宽
        linePaint.setStrokeWidth(mStokeWidth);
        linePaint.setColor(color);
        // 空心
        linePaint.setStyle(Paint.Style.STROKE);

        // 点画笔
        Paint pointPaint = new Paint();
        pointPaint.setAntiAlias(true);
        pointPaint.setColor(color);

        // 字体画笔
        Paint textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(mTextColor);
        textPaint.setTextSize(mTextSize);
        // 文字居中
        textPaint.setTextAlign(Paint.Align.CENTER);

        int alpha1 = 102;
        int alpha2 = 255;

        for (int i = 0; i < LENGTH; i++) {
            /**
             * 绘制线段
             * */
            if (i < LENGTH - 1) {
                // 昨天
                if (i == 0) {
                    linePaint.setAlpha(alpha1);
                    //绘制“—”线段，形成虚线
                    linePaint.setPathEffect(new DashPathEffect(new float[]{2 * mDensity, 2 * mDensity}, 0));
                    //绘制的路径
                    Path path=new Path();
                    // 路径起点
                    path.moveTo(mXAxis[i],yAxis[i]);
                    // 路径连接到下一个点的位置
                    path.lineTo(mXAxis[i + 1], yAxis[i + 1]);
                    //在画布中绘制
                    canvas.drawPath(path, linePaint);
                }else{
                    linePaint.setAlpha(alpha2);
                    linePaint.setPathEffect(null);
                    //绘制线段，起点位置到终点位置
                    canvas.drawLine(mXAxis[i],yAxis[i],mXAxis[i+1],yAxis[i+1],linePaint);
                }

                /**
                 * 绘制圆形点
                 * **/
                if (i != 1) {//首先判断不是今天，因为今天的圆形更大
                    // 昨天
                    if (i == 0) {
                        pointPaint.setAlpha(alpha1);
                        canvas.drawCircle(mXAxis[i], yAxis[i], mRadius, pointPaint);
                    } else {
                        pointPaint.setAlpha(alpha2);
                        canvas.drawCircle(mXAxis[i], yAxis[i], mRadius, pointPaint);
                    }
                    // 今天
                } else {
                    pointPaint.setAlpha(alpha2);
                    canvas.drawCircle(mXAxis[i], yAxis[i], mRadiusToday, pointPaint);
                }

                /***
                 * 绘制文字
                 * */
                if (i == 0) {//昨天
                    textPaint.setAlpha(alpha1);
                    drawText(canvas, textPaint, i, temp, yAxis, type);
                } else {
                    textPaint.setAlpha(alpha2);
                    drawText(canvas, textPaint, i, temp, yAxis, type);
                }

            }
        }
    }



    /**
     * 绘制文字
     *
     * @param canvas    画布
     * @param textPaint 画笔
     * @param i         索引
     * @param temp      温度集合
     * @param yAxis     y轴集合
     * @param type      折线种类：0，白天；1，夜间
     */
    private void drawText(Canvas canvas, Paint textPaint, int i, int[] temp, float[] yAxis, int type) {
        switch (type){
            case 0://绘制白天温度，yAxis[i]-mRadius-mTextSpace这个位置为白天文字的Y轴
                canvas.drawText(temp[i]+"°",mXAxis[i],yAxis[i]-mRadius-mTextSpace,textPaint);
                break;
            case 1:
                canvas.drawText(temp[i]+"°",mXAxis[i],yAxis[i]+mTextSize+mTextSpace,textPaint);
                break;
        }

    }

    /**
     * 计算y轴集合数值
     * */
    private void computeYAxisValues() {
        // 存放白天最低温度
        int minTempDay = mTempDay[0];
        // 存放白天最高温度
        int maxTempDay = mTempDay[0];

        for (int item:mTempDay){//循环得到白天最高温度
            if (item<minTempDay)
            {
                minTempDay=item;
            }
            if (item>maxTempDay)
            {
                maxTempDay=item;
            }
        }

        // 存放夜间最低温度
        int minTempNight = mTempNight[0];
        // 存放夜间最高温度
        int maxTempNight = mTempNight[0];
        for (int item : mTempNight) {
            if (item < minTempNight) {
                minTempNight = item;
            }
            if (item > maxTempNight) {
                maxTempNight = item;
            }
        }

        //白天，夜间的最低温度
        int minTemp=minTempNight<minTempDay?minTempNight:minTempDay;
        //白天，夜间的最高温度
        int maxTemp=maxTempDay>maxTempNight?maxTempDay:maxTempNight;

        // 份数（白天，夜间综合温差）,即高度差，每一度为一份
        float parts = maxTemp - minTemp;
        // y轴一端到控件一端之间的距离
        float length = mSpace + mTextSize + mTextSpace + mRadius;
        // y轴高度为总高度减去2个length之后剩下的高度
        float yAxisHeight = mHeight - length * 2;


        //当出现温度相等时，被除数不能为零
        if (parts==0){
            for (int i=0;i<LENGTH;i++){
                mYAxisDay[i]=yAxisHeight/2+length;
                mYAxisNight[i]=yAxisHeight/2+length;
            }
        }else{
            float partValue=yAxisHeight / parts;
            for (int i = 0; i < LENGTH; i++) {
                //Y轴的位置为总高度减去每一份的高度*该温度与日夜最低温的差再减去length高度
                mYAxisDay[i]=mHeight-partValue*(mTempDay[i]-minTemp)-length;
                mYAxisNight[i]=mHeight-partValue*(mTempNight[i]-minTemp)-length;

            }
        }


    }


    /**
     *  设置控件高度，x轴集合
     * */
    private void setHeightAndXAxis() {
        //以下获取的宽高都是控件显示在屏幕中的宽高，只有当空间超出屏幕后
        //getMeasuredHeight()等于getHeight()加上屏幕之外没有显示的大小
        mHeight=getHeight();//设置控件高度
        int width=getWidth();//控件宽度

        //把宽度平分12份
        float w=width/12;

        //x轴点的坐标集合
        mXAxis[0]=w;
        mXAxis[1] = w * 3;
        mXAxis[2] = w * 5;
        mXAxis[3] = w * 7;
        mXAxis[4] = w * 9;
        mXAxis[5] = w * 11;
    }



    /**
     * 提供方法以
     * 设置白天温度
     *
     * @param tempDay 温度数组集合
     */
    public void setTempDay(int[] tempDay) {
        mTempDay = tempDay;
    }

    /**
     * 设置夜间温度
     *
     * @param tempNight 温度数组集合
     */
    public void setTempNight(int[] tempNight) {
        mTempNight = tempNight;
    }
}
