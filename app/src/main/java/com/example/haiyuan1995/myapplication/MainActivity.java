package com.example.haiyuan1995.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import java.util.ArrayList;
import java.util.List;

import Adapter.MyFragmentPagerAdapter;
import Anim.AlphaTransformer;
import CustomView.NumberView;
import butterknife.BindView;
import butterknife.ButterKnife;
import myFragment.DayFragment;
import myFragment.NightFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.id_fragment_viewpager)
    ViewPager idFragmentViewpager;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.id_main_refresh)
    SwipeRefreshLayout idMainRefresh;


    private List<Fragment> list;
    // 获取位置
    private LocationManager locationManager;
    private String locationProvider;

    private static String jingweidu;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉原来的actionbar
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//让toolbar覆盖到状态栏上

        ButterKnife.bind(this);
        initLocation();
//        initView();
        initFragmentViewPager();


        idMainRefresh.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
      idMainRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
          @Override
          public void onRefresh() {
              new Handler().postDelayed(new Runnable() {
                  @Override
                  public void run() {
                      myFragmentPagerAdapter.notifyDataSetChanged();
                      idMainRefresh.setRefreshing(false);
                  }
              },2000);
          }
      });
//        initData();
//        initDailyWeather();


    }

    private void initLocation() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //获取所有可用的位置提供器
        List<String> providers = locationManager.getProviders(true);
        if (providers.contains(LocationManager.GPS_PROVIDER)) {//如果是gps
            locationProvider = LocationManager.GPS_PROVIDER;
        } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            locationProvider = LocationManager.NETWORK_PROVIDER;
        } else {
            System.out.println("没有可用的定位");
            return;
        }

        //获取location

        Location location = locationManager.getLastKnownLocation(locationProvider);
        if (location != null) {
            //不为空,显示地理位置经纬度
            returnLocation(location);
        } else {
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            returnLocation(location);
        }

//监听位置变化
        locationManager.requestLocationUpdates(locationProvider, 3000, 1, locationListener);

    }

    private void returnLocation(Location location) {
        //心知天气API
        //经纬度 例如：q=39.93:116.40（注意纬度前经度在后，冒号分隔）
        if (location != null) {
            jingweidu = location.getLatitude() + ":" + location.getLongitude();
        }
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            //如果位置发生变化,修改返回值
            returnLocation(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        public String getjingwendu() {

            return jingweidu;
        }


    };


    private void initFragmentViewPager() {
/**
 * 通过Fragment作为ViewPager的数据源
 */
        list = new ArrayList<Fragment>();
        DayFragment dayFragment = new DayFragment();
        NightFragment nightFragment = new NightFragment();
        SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString("locationStr", jingweidu);
        editor.commit();
//

        list.add(dayFragment);
        list.add(nightFragment);

        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), list);
        idFragmentViewpager.setAdapter(myFragmentPagerAdapter);


        idFragmentViewpager.setPageTransformer(true, new AlphaTransformer());

        idFragmentViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                NumberView n = (NumberView) list.get(position).getView().findViewById(R.id.id_main_temperature);
                n.showNumberWithAnimation();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        /**
         * 解决viewpager和SwipeRefreshLayout下拉冲突的问题，
         * viewpager在move的时候SwipeRefreshLayout
         * 控件设置不可用
         * */

        idFragmentViewpager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_MOVE:
                        idMainRefresh.setEnabled(false);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        idMainRefresh.setEnabled(true);
                        break;
                }
                return false;
            }
        });

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationManager != null) {
            //移除监听器

            locationManager.removeUpdates(locationListener);
        }
    }


}
