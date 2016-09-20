package com.example.haiyuan1995.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import Adapter.LifeAdapter;
import App.MyApplication;
import GsonBean.LifeWeatherData;
import WeatherApiURL.Url;

/**
 * Created by admin on 2016/9/6.
 * 生活小提示
 */
public class LifeActivity extends AppCompatActivity implements View.OnClickListener {
    private LifeAdapter lifeAdapter;
    private String Locationstr;
    private RecyclerView recyclerView_life;
    private RelativeLayout activitymain;
    private Toolbar id_toobar;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_life);
        id_toobar= (Toolbar) findViewById(R.id.id_toolbar);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//让toolbar覆盖到状态栏上
        initToolbar();
        initLocation();
        initLifWeather();
        initData();
    }

    public void initToolbar(){
        id_toobar.setTitle("生活指数");
        id_toobar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        setSupportActionBar(id_toobar);
        id_toobar.setNavigationOnClickListener(this);
    }
    public void onClick(View v) {
        finish();
    }

    private void initData() {
        recyclerView_life= (RecyclerView) findViewById(R.id.id_recycleview_life);
        activitymain= (RelativeLayout) findViewById(R.id.activity_main_relativelayout);
    }

    private void initLocation() {
        Locationstr = this.getSharedPreferences("config",this.MODE_PRIVATE).getString("locationStr", "");

    }
    public void initLifWeather() {
        final String lifeWeather;
        if (Locationstr.length() == 0 || Locationstr == null) {
            lifeWeather = Url.Weather_Life_Url + "?key=" + MyApplication.Key + "&location=ip";

        } else {
            lifeWeather = Url.Weather_Life_Url + "?key=" + MyApplication.Key + "&location=" + Locationstr ;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.GET, lifeWeather, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Gson gson = new Gson();
                LifeWeatherData lifeWeatherData = gson.fromJson(s, LifeWeatherData.class);

                initAdapter(lifeWeatherData);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Snackbar.make(activitymain, "网络错误,数据暂未更新!", Snackbar.LENGTH_LONG).show();
            }
        });

        stringRequest.setTag("Activity_Life");
        MyApplication.getRequestQueue().add(stringRequest);

    }

    public void initAdapter(LifeWeatherData lifeWeatherData) {
        lifeAdapter = new LifeAdapter(LifeActivity.this, lifeWeatherData);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(LifeActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView_life.setLayoutManager(linearLayoutManager);
        recyclerView_life.setAdapter(lifeAdapter);

    }



}
