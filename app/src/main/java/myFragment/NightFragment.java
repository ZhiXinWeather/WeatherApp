package myFragment;


import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.haiyuan1995.myapplication.R;

import Adapter.NightAdapter;
import GsonBean.DailyWeatherData;
import GsonBean.WeatherNowData;
import RecycleViewAnim.SlideInRightAnimatorAdapter;


/**
 * Created by haiyuan 1995 on 2016/8/31.
 */

public class NightFragment extends DayFragment {

private NightAdapter nightAdapter;
private RelativeLayout activityMain;
    @Override
    public void initView(View view) {
        super.initView(view);
        activityMain= (RelativeLayout) view.findViewById(R.id.activity_main);
        activityMain.setBackgroundResource(R.mipmap.bg_night);
        fab1.setImageResource(R.mipmap.moon);
    }

    @Override
    public void initAdapter(DailyWeatherData dailyWeatherData) {
        super.initAdapter(dailyWeatherData);
        nightAdapter = new NightAdapter(getActivity(), dailyWeatherData);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),3,GridLayoutManager.HORIZONTAL,false);
        SlideInRightAnimatorAdapter rightAnimatorAdapter=new SlideInRightAnimatorAdapter(nightAdapter,idMainRecyclerview);
        rightAnimatorAdapter.getViewAnimator().setInitialDelayMillis(500);
        nightAdapter.setRecyItemOnclick(this);
        idMainRecyclerview.setLayoutManager(gridLayoutManager);
        idMainRecyclerview.setAdapter(rightAnimatorAdapter);

    }

    @Override
    public void setData(WeatherNowData data) {
        super.setData(data);
        idMainTemperature.setNumber(Integer.valueOf(data.getResults().get(0).getNow().getTemperature()));
        idMainTemperature.showNumberWithAnimation();
        idMainTemperature.setTextColor(Color.parseColor("#ffffffff"));
        idMainTianqi.setTextColor(Color.parseColor("#ffffffff"));
        idMainPath.setTextColor(Color.parseColor("#ffffffff"));

    }
}
