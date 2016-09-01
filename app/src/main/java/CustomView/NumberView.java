package CustomView;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

/**
 *  显示跳动的数字
 */

public class NumberView extends TextView {

    private  int duration=1500;//时长
    private  int number=0;

    public NumberView(Context context) {
        super(context);
    }

    public NumberView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NumberView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {


    }

    public void showNumberWithAnimation(){
        ObjectAnimator objectAnimator=ObjectAnimator.ofInt(this,"number",0,number);
        objectAnimator.setDuration(duration);

        //加速器，快到慢
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator.start();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        setText(number+"℃");
    }
}
