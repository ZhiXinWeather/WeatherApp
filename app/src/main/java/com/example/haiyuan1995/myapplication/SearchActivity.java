package com.example.haiyuan1995.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

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
import WeatherApiURL.Url;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by haiyuan 1995 on 2016/9/5.
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
    private String listText="";
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
               //隐藏自动提示的listview
                adapter.clear();
                idSearchListview.setVisibility(View.GONE);
                if (listText.length()>0)
                {
                    query=listText;
                }
                try {
                    query = URLEncoder.encode(query, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String search_weather = Url.Weather_Daily_Url + "?key=" + MyApplication.Key + "&location=" + query + "&start=0&days=5";
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

                    }
                });
                searchWeather.setTag("SearchWeather");
                MyApplication.getRequestQueue().add(searchWeather);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //一旦搜索字符串被改变，隐藏天气结果的recycleview
                idSearchRecycleview.setVisibility(View.GONE);

                if (newText.length() > 0 && newText != null) {
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

                        }
                    });
                    searchCity.setTag("SearchCity");
                    MyApplication.getRequestQueue().add(searchCity);

                } else {
                    //搜索内容为空时清空列表
                    adapter.clear();
                }
                return false;
            }
        });

    }

    private void initWeatherData(DailyWeatherData dailyWeatherData) {

        SearchResultAdapter resultAdapter = new SearchResultAdapter(SearchActivity.this, dailyWeatherData);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.HORIZONTAL, false);
        idSearchRecycleview.setVisibility(View.VISIBLE);
        idSearchRecycleview.setLayoutManager(linearLayoutManager);
        idSearchRecycleview.setAdapter(resultAdapter);

    }

    private void initListData(SearchCityData cityData) {

        idSearchListview.setVisibility(View.VISIBLE);//如被隐藏显示
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < cityData.getResults().size(); i++) {
            arrayList.add(cityData.getResults().get(i).getName());
        }
        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, arrayList);
        idSearchListview.setAdapter(adapter);

        idSearchListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listText= (String) ((TextView)view).getText();
                Log.i("listview","listview被点击了"+listText);
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
}
