package CustomView;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.OvershootInterpolator;

/**
 * Created by haiyuan 1995 on 2016/9/5.
 */

public class FabAnim {
    /**
     * 显示Veiw
     *  @param view 要显示的View
     * @param to 显示的结束位置
     */
    public static void showView(View view, float to){
        //垂直方向上的属性动画
        AnimatorSet animatorSet=new AnimatorSet();
        ObjectAnimator translation=ObjectAnimator.ofFloat(view,"TranslationY",0,to);
        ObjectAnimator alpha=ObjectAnimator.ofFloat(view,View.ALPHA,0f,1f);

        animatorSet.playTogether(translation,alpha);
        animatorSet.setInterpolator(new OvershootInterpolator());//插入器,表示向前甩一定值后再回到原来位置
        animatorSet.setDuration(500);

        animatorSet.start();
        view.setVisibility(View.VISIBLE);
//渐变动画        ObjectAnimator.ofFloat(view,View.ALPHA,0,1).setDuration(1000).start();
//旋转动画        ObjectAnimator.ofFloat(view,"rotation",0,360*50).setDuration(10000).start();



    }

    /**
     * 隐藏View
     *  @param view 要隐藏的View
     * @param from View初始位置
     */
    public static void hideView(final View view, float from){
       AnimatorSet animatorset=new AnimatorSet();

        ObjectAnimator translation=ObjectAnimator.ofFloat(view,"TranslationY",from,0);
        ObjectAnimator alpha=ObjectAnimator.ofFloat(view,View.ALPHA,1f,0f);
        animatorset.playTogether(translation,alpha);
        animatorset.setDuration(300);
        animatorset.start();
        //anim.setInterpolator(new OvershootInterpolator());
        translation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
            //【重要】在动画完成后再将可见性设置为GONE，否则，直接设置为不可见将看不到动画
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });




    }

}
