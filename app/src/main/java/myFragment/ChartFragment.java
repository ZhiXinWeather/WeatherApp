package myFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.haiyuan1995.myapplication.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import App.MyApplication;
import CustomView.WeatherChartView;
import GsonBean.DailyWeatherData;
import WeatherApiURL.Url;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by haiyuan 1995 on 2016/9/12.
 * 图表fragment
 */

public class ChartFragment extends Fragment {
    @BindView(R.id.id_chartview_weatherChartView)
    WeatherChartView idChartviewWeatherChartView;

    private String locationStr;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.chartview_fragment, container, false);
        ButterKnife.bind(this, view);
        initLocation();
        initData();
        return view;
    }

    private void initLocation() {
        locationStr = getActivity().getSharedPreferences("config", getActivity().MODE_PRIVATE).getString("locationStr", "");

    }

    private void initData() {

        String dailyWeather;
        if (locationStr.length() == 0 || locationStr == null) {
            dailyWeather = Url.Weather_Daily_Url + "?key=" + MyApplication.Key + "&location=ip&start=0&days=5";

        } else {
            dailyWeather = Url.Weather_Daily_Url + "?key=" + MyApplication.Key + "&location=" + locationStr + "&start=0&days=5";
        }

        StringRequest stringRequest = new StringRequest(Request.Method.GET, dailyWeather, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Gson gson = new Gson();
                DailyWeatherData dailyWeatherData = gson.fromJson(s, DailyWeatherData.class);

                initChartView(dailyWeatherData);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Snackbar.make(idChartviewWeatherChartView, "网络错误,数据暂未更新!", Snackbar.LENGTH_LONG).show();
            }
        });

        stringRequest.setTag("Fragment_DailyWeather");
        MyApplication.getRequestQueue().add(stringRequest);
    }

    private void initChartView(DailyWeatherData dailyWeatherData) {
        List<Integer> highList=new ArrayList();
        List<Integer> lowList=new ArrayList();
        for (int i=0;i<dailyWeatherData.getResults().get(0).getDaily().size();i++){
            highList.add(Integer.valueOf(dailyWeatherData.getResults().get(0).getDaily().get(i).getHigh()));
            lowList.add(Integer.valueOf(dailyWeatherData.getResults().get(0).getDaily().get(i).getLow()));
        }
        //自定义控件chartView中的接收数据必须为6组
        int[] tempDay = new int[6];
        int[] tempNight=new int[6];
        //因为数据不足，第一组数据用0代替
        tempDay[0]=0;
        tempNight[0]=0;
        for(int i=0;i<highList.size();i++)
        {
            tempDay[i+1] = highList.get(i);
            tempNight[i+1]=lowList.get(i);
        }
        idChartviewWeatherChartView.setTempDay(tempDay);
        idChartviewWeatherChartView.setTempNight(tempNight);
        /**
         * 重要：必须调用invalidate()方法刷新UI
         */
        idChartviewWeatherChartView.invalidate();

    }
}
