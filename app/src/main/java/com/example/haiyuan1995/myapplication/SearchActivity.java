package com.example.haiyuan1995.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import Adapter.SearchResultAdapter;
import App.MyApplication;
import GsonBean.DailyWeatherData;
import GsonBean.SearchCityData;
import RecycleViewAnim.ScaleInAnimatorAdapter;
import Utils.HideKeyBoard;
import WeatherApiURL.Url;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by haiyuan 1995 on 2016/9/5.
 * 城市搜索界面
 */

public class SearchActivity extends AppCompatActivity {
    @BindView(R.id.id_search_toolbar)
    Toolbar idSearchToolbar;
    @BindView(R.id.id_search_searchview)
    SearchView idSearchSearchview;
    @BindView(R.id.id_search_listview)
    ListView idSearchListview;
    @BindView(R.id.id_search_recycleview)
    RecyclerView idSearchRecycleview;
    private ArrayAdapter<String> adapter;
    private String selectCityName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//让toolbar覆盖到状态栏上

        initToolbar();
        initSearch();
    }

    private void initSearch() {
        idSearchSearchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { //实际的查询方法,点击搜索后调用

                //搜索的方法
                searchSubmit(query);

                return true;//这两个方法的返回值是用于判断searchview是否有输入内容
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //一旦搜索字符串被改变，隐藏显示天气的recycleview
                idSearchRecycleview.setVisibility(View.GONE);

                if (newText.length() > 0) {
                    try {
                        newText = URLEncoder.encode(newText, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    String search_url = Url.Weather_SearchCity_Url + "?key=" + MyApplication.Key + "&q=" + newText;
                    final StringRequest searchCity = new StringRequest(StringRequest.Method.GET, search_url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            Gson gson = new Gson();
                            SearchCityData cityData = gson.fromJson(s, SearchCityData.class);
                            initListData(cityData);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Snackbar.make(idSearchSearchview,"搜索错误",Snackbar.LENGTH_SHORT).show();
                        }
                    });
                    searchCity.setTag("SearchCity");
                    MyApplication.getRequestQueue().add(searchCity);

                } else {
                    //搜索内容为空时清空列表
                    if (adapter!=null){
                        adapter.clear();
                    }
                    idSearchListview.setVisibility(View.GONE);
                }
                return false;
            }
        });

    }

    private void searchSubmit(String query) {
        //隐藏自动提示的listview
        idSearchListview.setVisibility(View.GONE);
        HideKeyBoard.hide(SearchActivity.this);//隐藏键盘
        selectCityName=query;//所选择的城市
        try {
            query = URLEncoder.encode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //获取7天的天气数据
        String search_weather = Url.Weather_Daily_Url + "?key=" + MyApplication.Key + "&location=" + query + "&start=0&days=7";
        final StringRequest searchWeather = new StringRequest(StringRequest.Method.GET, search_weather, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Gson gson = new Gson();
                DailyWeatherData dailyWeatherData = gson.fromJson(s, DailyWeatherData.class);
                initWeatherData(dailyWeatherData);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Snackbar.make(idSearchSearchview,"搜索错误,暂无数据",Snackbar.LENGTH_SHORT).show();
            }
        });
        searchWeather.setTag("SearchWeather");
        MyApplication.getRequestQueue().add(searchWeather);
    }

    private void initWeatherData(DailyWeatherData dailyWeatherData) {

        SearchResultAdapter resultAdapter = new SearchResultAdapter(SearchActivity.this, dailyWeatherData);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.HORIZONTAL, false);
        ScaleInAnimatorAdapter scaleInAnimatorAdapter=new ScaleInAnimatorAdapter(resultAdapter,idSearchRecycleview);
        scaleInAnimatorAdapter.getViewAnimator().setInitialDelayMillis(500);
        idSearchRecycleview.setVisibility(View.VISIBLE);
        idSearchRecycleview.setLayoutManager(linearLayoutManager);
        idSearchRecycleview.setAdapter(scaleInAnimatorAdapter);

    }

    private void initListData(SearchCityData cityData) {

        idSearchListview.setVisibility(View.VISIBLE);//如被隐藏显示
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < cityData.getResults().size(); i++) {
            arrayList.add(cityData.getResults().get(i).getName()+"\t\t\t\t"+cityData.getResults().get(i).getCountry());
        }
        adapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1, arrayList);
        idSearchListview.setAdapter(adapter);

        idSearchListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //获取所点击的Item的文字内容，用来搜索
                String text=(String) ((TextView)view).getText();
                text=text.substring(0,text.indexOf("\t"));

                searchSubmit(text);

            }
        });
    }

    private void initToolbar() {
        idSearchToolbar.setTitle("");
        idSearchToolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        setSupportActionBar(idSearchToolbar);

        idSearchToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_ok)
        {
            if(!TextUtils.isEmpty(selectCityName)){
                Toast.makeText(getApplicationContext(),"切换城市:"+selectCityName,Toast.LENGTH_SHORT).show();

                try {
                    //转换为utf-8格式再储存
                    selectCityName=URLEncoder.encode(selectCityName,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                getSharedPreferences("config",MODE_PRIVATE).
                        edit().putString("selectCityName",selectCityName)
                        .apply();
                sendBroadcast(new Intent("CityChange"));
                finish();
            }else{
                Toast.makeText(getApplicationContext(),"没有变动",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getRequestQueue().cancelAll("SearchWeather");
        MyApplication.getRequestQueue().cancelAll("SearchCity");
    }
}
