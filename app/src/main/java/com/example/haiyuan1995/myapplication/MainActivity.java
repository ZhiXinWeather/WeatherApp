package com.example.haiyuan1995.myapplication;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import Adapter.DayAdapter;
import Adapter.MyFragmentPagerAdapter;
import Anim.AlphaTransformer;
import CustomView.NumberView;
import butterknife.BindView;
import butterknife.ButterKnife;
import myFragment.DayFragment;
import myFragment.NightFragment;

public class MainActivity extends FragmentActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.id_fragment_viewpager)
    ViewPager idFragmentViewpager;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private List<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉原来的actionbar
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//让toolbar覆盖到状态栏上

        ButterKnife.bind(this);

//        initView();
        initFragmentViewPager();
//        initData();
//        initDailyWeather();


    }

    private void initFragmentViewPager() {
/**
 * 通过Fragment作为ViewPager的数据源
 */
        list = new ArrayList<Fragment>();
        DayFragment dayFragment = new DayFragment();
        NightFragment nightFragment = new NightFragment();
        list.add(dayFragment);
        list.add(nightFragment);

        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), list);
        idFragmentViewpager.setAdapter(myFragmentPagerAdapter);
        idFragmentViewpager.setPageTransformer(true,new AlphaTransformer());

        idFragmentViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                    if (state==2)//滑动完成
                    {
                        int i=idFragmentViewpager.getCurrentItem();
                        NumberView n= (NumberView) list.get(i).getView().findViewById(R.id.id_main_temperature);
                        n.showNumberWithAnimation();
                    }
            }
        });


    }

    //    private void initDailyWeather() {//初始化近期天气数据列表
//        final String dailyWeather = Url.Weather_Daily_Url + "?key=" + MyApplication.Key + "&location=ip&&start=0&days=5";
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, dailyWeather, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String s) {
//                Gson gson = new Gson();
//                DailyWeatherData dailyWeatherData = gson.fromJson(s, DailyWeatherData.class);
//
//                initAdapter(dailyWeatherData);
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                Snackbar.make(drawerLayout, "网络错误,数据暂未更新!", Snackbar.LENGTH_LONG).show();
//            }
//        });
//
//        stringRequest.setTag("MainActivity_DailyWeather");
//        MyApplication.getRequestQueue().add(stringRequest);
//
//    }
//
//    private void initData() {
//
//        //默认用IP地址作为查询天气的城市
//        String weathernow = Url.Weather_Now_Url + "?key=" + MyApplication.Key + "&location=ip";
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, weathernow, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String s) {
//                Gson gson = new Gson();
//                WeatherNowData weatherNowData = gson.fromJson(s, WeatherNowData.class);
//
//                setData(weatherNowData);
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                Snackbar.make(drawerLayout, "网络错误,数据暂未更新!", Snackbar.LENGTH_LONG).show();
//            }
//        });
//
//        stringRequest.setTag("MainActivity");
//        MyApplication.getRequestQueue().add(stringRequest);
//    }
//
//    private void initView() {
//
//
//        idMainToolbar.setTitle("");
//
//        setSupportActionBar(idMainToolbar);//设置title要在这个之前
//
//        //减去状态栏的高度
//        FrameLayout.LayoutParams param = (FrameLayout.LayoutParams) idMainToolbar.getLayoutParams();
//        param.setMargins(0, MyApplication.status_bar_height, 0, 0);
//        idMainToolbar.setLayoutParams(param);
//
//        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawerLayout, idMainToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawerLayout.setDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//
//
//        fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//    }
//
//    private void setData(WeatherNowData data) {
//
//        idMainTemperature.setNumber(Integer.valueOf(data.getResults().get(0).getNow().getTemperature()));
//        idMainTemperature.showNumberWithAnimation();
//        idMainTianqi.setText(data.getResults().get(0).getNow().getText());
//        String path = data.getResults().get(0).getLocation().getPath();
//        path = path.substring(3, path.length()-3);
//        idMainPath.setText(path);
//
//        int resourceId=SelectWeatherImage.selectImageView(data.getResults().get(0).getNow().getCode());
//                Glide.with(MainActivity.this).load(resourceId).into(idMainImageview);
//
//        }
//
//    private void initAdapter(DailyWeatherData dailyWeatherData) {
//        mDayAdapter=new DayAdapter(MainActivity.this,dailyWeatherData);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
////        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
//        mDayAdapter.setRecyItemOnclick(this);
//
//        idMainRecyclerview.setLayoutManager(linearLayoutManager);
//        idMainRecyclerview.setAdapter(mDayAdapter);
//
//
//
//    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        MyApplication.getRequestQueue().cancelAll("MainActivity");
//        MyApplication.getRequestQueue().cancelAll("MainActivity_DailyWeather");
//    }
//
//    @Override
//    public void onItemClick(View view, int postion) {
//
//        //点击后没内容
//    }
}
