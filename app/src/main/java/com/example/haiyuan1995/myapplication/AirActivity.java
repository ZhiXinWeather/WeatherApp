package com.example.haiyuan1995.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import Adapter.AirAdapter;
import Adapter.AirRankingAdapter;
import App.MyApplication;
import GsonBean.AirQualityData;
import GsonBean.AirRankingData;
import RecycleViewAnim.SlideInBottomAnimatorAdapter;
import WeatherApiURL.Url;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by haiyuan 1995 on 2016/9/10.
 * 空气质量和排行榜
 */

public class AirActivity extends AppCompatActivity {
    @BindView(R.id.id_air_aqi)
    TextView idAirAqi;
    @BindView(R.id.id_air_quality)
    TextView idAirQuality;
    @BindView(R.id.id_toolbar)
    Toolbar idToolbar;
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.appbar_layout)
    AppBarLayout appbarLayout;
    @BindView(R.id.id_air_pm25)
    TextView idAirPm25;
    @BindView(R.id.id_air_pm10)
    TextView idAirPm10;
    @BindView(R.id.id_air_no2)
    TextView idAirNo2;
    @BindView(R.id.id_air_so2)
    TextView idAirSo2;
    @BindView(R.id.id_air_recyclerview)
    RecyclerView idAirRecyclerview;
    @BindView(R.id.id_air_recyclerview_ranking)
    RecyclerView idAirRecyclerviewRanking;

    private String locationStr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉原来的actionbar
        setContentView(R.layout.activity_air);

        ButterKnife.bind(this);
        initView();
        initLocation();
        initAirData();
        initAirRanking();

    }

    private void initAirRanking() {
        String airRanking = Url.Weather_Air_Ranking_Url + "?key=" + MyApplication.Key;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, airRanking, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Gson gson = new Gson();
                AirRankingData airRankingData = gson.fromJson(s, AirRankingData.class);

                setAirRankingData(airRankingData);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Snackbar.make(idToolbar, "网络错误,数据暂未更新!", Snackbar.LENGTH_LONG).show();
            }
        });

        stringRequest.setTag("AirActivity_airRanking");
        MyApplication.getRequestQueue().add(stringRequest);

    }

    private void setAirRankingData(AirRankingData airRankingData) {
        AirRankingAdapter airRankingAdapter=new AirRankingAdapter(AirActivity.this,airRankingData);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(AirActivity.this, LinearLayoutManager.VERTICAL, false);
        SlideInBottomAnimatorAdapter slideInBottomAnimatorAdapter = new SlideInBottomAnimatorAdapter(airRankingAdapter, idAirRecyclerview);
        slideInBottomAnimatorAdapter.getViewAnimator().setInitialDelayMillis(500);
        idAirRecyclerviewRanking.setLayoutManager(mLinearLayoutManager);
        idAirRecyclerviewRanking.setAdapter(slideInBottomAnimatorAdapter);
    }

    private void initView() {
        idToolbar.setNavigationIcon(R.drawable.back);
        setSupportActionBar(idToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        idToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //在nestedscroll中添加 android:fillViewport="true"
        //然后关闭recycleview的滑动，可以解决嵌套的冲突
        idAirRecyclerview.setNestedScrollingEnabled(false);
        idAirRecyclerviewRanking.setNestedScrollingEnabled(false);
        collapsingToolbarLayout.setTitle("");

    }

    private void initLocation() {
        locationStr = getSharedPreferences("config", MODE_PRIVATE).getString("locationStr", "");
    }

    private void initAirData() {//获取空气质量数据，all参数为获取市内所有监测站数据
        String airQuality;
        if (locationStr.length() == 0 || locationStr == null) {
            airQuality = Url.Weather_Air_Url + "?key=" + MyApplication.Key + "&location=ip&scope=all";

        } else {
            airQuality = Url.Weather_Air_Url + "?key=" + MyApplication.Key + "&location=" + locationStr + "&scope=all";
        }

        StringRequest stringRequest = new StringRequest(Request.Method.GET, airQuality, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Gson gson = new Gson();
                AirQualityData airQualityData = gson.fromJson(s, AirQualityData.class);

                setAirData(airQualityData);
                initAdapter(airQualityData);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Snackbar.make(idToolbar, "网络错误,数据暂未更新!", Snackbar.LENGTH_LONG).show();
            }
        });

        stringRequest.setTag("AirActivity_airQuality");
        MyApplication.getRequestQueue().add(stringRequest);


    }

    private void initAdapter(AirQualityData airQualityData) {
        AirAdapter airAdapter = new AirAdapter(AirActivity.this, airQualityData);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(AirActivity.this, LinearLayoutManager.VERTICAL, false);
        SlideInBottomAnimatorAdapter slideInBottomAnimatorAdapter = new SlideInBottomAnimatorAdapter(airAdapter, idAirRecyclerview);
        slideInBottomAnimatorAdapter.getViewAnimator().setInitialDelayMillis(500);
        idAirRecyclerview.setLayoutManager(mLinearLayoutManager);
        idAirRecyclerview.setAdapter(slideInBottomAnimatorAdapter);

    }

    private void setAirData(AirQualityData airQualityData) {

        AirQualityData.ResultsBean.AirBean airBean = airQualityData.getResults().get(0).getAir();
        int aqi;
        if (airBean.getCity().getAqi()!=null) {
            aqi = Integer.parseInt(airBean.getCity().getAqi());
        }else {
            aqi=0;//无aqi数据时为零
        }

        if (aqi>0&&aqi<=50){
            idAirAqi.setTextColor(ContextCompat.getColor(AirActivity.this,R.color.green));
            idAirQuality.setTextColor(ContextCompat.getColor(AirActivity.this,R.color.green));
        }else if (aqi>50&&aqi<=100){
            idAirAqi.setTextColor(ContextCompat.getColor(AirActivity.this,R.color.yellow));
            idAirQuality.setTextColor(ContextCompat.getColor(AirActivity.this,R.color.yellow));
        }else if (aqi>100&&aqi<=150){
            idAirAqi.setTextColor(ContextCompat.getColor(AirActivity.this,R.color.orange));
            idAirQuality.setTextColor(ContextCompat.getColor(AirActivity.this,R.color.orange));
        }else if (aqi>150&&aqi<=200){
            idAirAqi.setTextColor(ContextCompat.getColor(AirActivity.this,android.R.color.holo_red_light));
            idAirQuality.setTextColor(ContextCompat.getColor(AirActivity.this,android.R.color.holo_red_light));
        }else if (aqi>200&&aqi<300){
            idAirAqi.setTextColor(ContextCompat.getColor(AirActivity.this,R.color.brown));
            idAirQuality.setTextColor(ContextCompat.getColor(AirActivity.this,R.color.brown));
        }else{
            idAirAqi.setTextColor(ContextCompat.getColor(AirActivity.this,R.color.brownishBlack));
            idAirQuality.setTextColor(ContextCompat.getColor(AirActivity.this,R.color.brownishBlack));
        }
        idAirAqi.setText(airBean.getCity().getAqi());
        idAirQuality.setText(airBean.getCity().getQuality());
        idAirPm25.setText(airBean.getCity().getPm25());
        idAirPm10.setText(airBean.getCity().getPm10());
        idAirNo2.setText(airBean.getCity().getNo2());
        idAirSo2.setText(airBean.getCity().getSo2());

        collapsingToolbarLayout.setTitle(airQualityData.getResults().get(0).getLocation().getName());
        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.white));
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.white));
        collapsingToolbarLayout.setExpandedTitleGravity(Gravity.BOTTOM);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getRequestQueue().cancelAll("AirActivity_airQuality");
        MyApplication.getRequestQueue().cancelAll("AirActivity_airRanking");
    }
}
