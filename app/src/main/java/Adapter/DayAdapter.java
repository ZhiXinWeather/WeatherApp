package Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.haiyuan1995.myapplication.R;
import Utils.SelectWeatherImage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import GsonBean.DailyWeatherData;

/**
 * Created by LanHaiYuan.
 * 2016/04/15
 */
public class DayAdapter extends RecyclerView.Adapter<DayAdapter.MyViewHolder> {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private DailyWeatherData mDailyWeatherData;

    public interface RecyItemOnclick
    {
        void onItemClick(View view,int postion);
    }
    private  RecyItemOnclick recyitemonclick;

    public void setRecyItemOnclick(RecyItemOnclick Listener){
        this.recyitemonclick=Listener;
    }

    public DayAdapter(Context mContext , DailyWeatherData mDailyWeatherData) {
        this.mContext = mContext;
        this.mDailyWeatherData = mDailyWeatherData;
        mLayoutInflater=LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=mLayoutInflater.inflate(R.layout.recycleview_item,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view,recyitemonclick);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
/**获取时间数据
 * @hour 小时
 * @apm  apm=0 表示上午，apm=1表示下午。
 * */
        Calendar mCalendar=Calendar.getInstance();
        long timemillis=System.currentTimeMillis();
        mCalendar.setTimeInMillis(timemillis);
        int isNight=mCalendar.get(Calendar.HOUR_OF_DAY);
        String weatherdate=mDailyWeatherData.getResults().get(0).getDaily().get(position).getDate();
        int year=Integer.valueOf(weatherdate.substring(0,4));
        int month=Integer.valueOf(weatherdate.substring(5,7))-1;//月份要减1
        int day=Integer.valueOf(weatherdate.substring(8));
        Date date=new Date(year,month,day);
        SimpleDateFormat  format=new SimpleDateFormat("EEEE");//转换为星期几


        holder.tv_tianqi.setText(mDailyWeatherData.getResults().get(0).getDaily().get(position).getText_day());
        holder.tv_date.setText(format.format(date));
        holder.tv_wendu.setText(mDailyWeatherData.getResults().get(0).getDaily().get(position).getHigh()+"℃");
        String weatherCode=mDailyWeatherData.getResults().get(0).getDaily().get(position).getCode_day();

        int resourceID=SelectWeatherImage.selectImageView(weatherCode);
        Glide.with(mContext).load(resourceID).into(holder.iv_weather_png);

    }


    @Override
    public int getItemCount() {
        return mDailyWeatherData.getResults().get(0).getDaily().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_date;
        ImageView iv_weather_png;
        TextView tv_tianqi;
        TextView tv_wendu;
        RecyItemOnclick recyItemOnclick;
        public MyViewHolder(View itemView,RecyItemOnclick recyItemOnclick) {
            super(itemView);
            tv_date= (TextView) itemView.findViewById(R.id.id_recycleview_item_date);
            iv_weather_png= (ImageView) itemView.findViewById(R.id.id_recycleview_item_weather_png);
            tv_tianqi= (TextView) itemView.findViewById(R.id.id_recycleview_item_tianqi);
            tv_wendu= (TextView) itemView.findViewById(R.id.id_recycleview_item_wendu);

            this.recyItemOnclick=recyItemOnclick;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (recyItemOnclick!=null)
            {
                int positon=getLayoutPosition();
                recyItemOnclick.onItemClick(view,positon);
            }
        }
    }
}
