package myFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.example.haiyuan1995.myapplication.R;
import com.example.haiyuan1995.myapplication.SelectWeatherImage;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import Adapter.DayAdapter;
import App.MyApplication;
import CustomView.NumberView;
import GsonBean.DailyWeatherData;
import GsonBean.WeatherNowData;
import WeatherApiURL.Url;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主界面的fragment
 */

public class DayFragment extends Fragment implements DayAdapter.RecyItemOnclick {


    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.id_main_path)
    TextView idMainPath;
    @BindView(R.id.id_main_temperature)
    NumberView idMainTemperature;
    @BindView(R.id.id_main_imageview)
    ImageView idMainImageview;
    @BindView(R.id.id_main_tianqi)
    TextView idMainTianqi;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;
    @BindView(R.id.id_main_recyclerview)
    RecyclerView idMainRecyclerview;
    @BindView(R.id.content_main)
    RelativeLayout contentMain;
    @BindView(R.id.id_main_toolbar)
    Toolbar idMainToolbar;
    @BindView(R.id.appbar_layout)
    AppBarLayout appbarLayout;
    @BindView(R.id.id_fragment_last_update)
    TextView idFragmentLastUpdate;


    private DayAdapter mDayAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.app_bar_main, container, false);
        initView(view);
        initData();
        initDailyWeather();


        ButterKnife.bind(this, view);
        return view;
    }

    public void initView(View view) {

        fab = (FloatingActionButton) view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        fab.setImageResource(R.mipmap.sun);
    }


    public void initDailyWeather() {//初始化近期天气数据列表
        final String dailyWeather = Url.Weather_Daily_Url + "?key=" + MyApplication.Key + "&location=ip&&start=0&days=5";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, dailyWeather, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Gson gson = new Gson();
                DailyWeatherData dailyWeatherData = gson.fromJson(s, DailyWeatherData.class);

                initAdapter(dailyWeatherData);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Snackbar.make(activityMain, "网络错误,数据暂未更新!", Snackbar.LENGTH_LONG).show();
            }
        });

        stringRequest.setTag("Fragment_DailyWeather");
        MyApplication.getRequestQueue().add(stringRequest);

    }

    public void initData() {

        //默认用IP地址作为查询天气的城市
        String weathernow = Url.Weather_Now_Url + "?key=" + MyApplication.Key + "&location=ip";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, weathernow, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Gson gson = new Gson();
                WeatherNowData weatherNowData = gson.fromJson(s, WeatherNowData.class);

                setData(weatherNowData);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Snackbar.make(activityMain, "网络错误,数据暂未更新!", Snackbar.LENGTH_LONG).show();
            }
        });

        stringRequest.setTag("myFragment");
        MyApplication.getRequestQueue().add(stringRequest);
    }


    public void setData(WeatherNowData data) {
        //时间转换为多少分钟前更新数据
        String dateTime=data.getResults().get(0).getLast_update();
        dateTime=dateTime.replace("T","");

        try {
            long last_update= new SimpleDateFormat("yyyy-MM-ddHH:mm:ss").parse(dateTime).getTime();
            String  last_time= String.valueOf((System.currentTimeMillis()-last_update)/1000/60);
            if (Integer.valueOf(last_time)>0) {
                idFragmentLastUpdate.setText(last_time + "分钟前 发布");
            }else
            {
                idFragmentLastUpdate.setText("最近发布");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }



        idMainTemperature.setNumber(Integer.valueOf(data.getResults().get(0).getNow().getTemperature()));
        idMainTemperature.showNumberWithAnimation();
        idMainTianqi.setText(data.getResults().get(0).getNow().getText());
        String path = data.getResults().get(0).getLocation().getPath();
        path = path.substring(3, path.length() - 3);
        idMainPath.setText(path);

        int resourceId = SelectWeatherImage.selectImageView(data.getResults().get(0).getNow().getCode());
        Glide.with(getActivity()).load(resourceId).into(idMainImageview);

    }

    public void initAdapter(DailyWeatherData dailyWeatherData) {
        mDayAdapter = new DayAdapter(getActivity(), dailyWeatherData);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
//        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        mDayAdapter.setRecyItemOnclick(this);

        idMainRecyclerview.setLayoutManager(linearLayoutManager);
        idMainRecyclerview.setAdapter(mDayAdapter);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MyApplication.getRequestQueue().cancelAll("myFragment");
        MyApplication.getRequestQueue().cancelAll("Fragment_DailyWeather");
    }

    public void onItemClick(View view, int postion) {

        //点击后没内容
    }
}
