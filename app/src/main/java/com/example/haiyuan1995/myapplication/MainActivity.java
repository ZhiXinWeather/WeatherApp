package com.example.haiyuan1995.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Adapter.MyFragmentPagerAdapter;
import Anim.AlphaTransformer;
import CustomView.LoadingView.ShapeLoadingDialog;
import Utils.SelectWeatherImage;
import butterknife.BindView;
import butterknife.ButterKnife;
import MyFragment.ChartFragment;
import MyFragment.DayFragment;
import MyFragment.NightFragment;

public class MainActivity extends AppCompatActivity{


    @BindView(R.id.id_fragment_viewpager)
    ViewPager idFragmentViewpager;
    @BindView(R.id.id_main_refresh)
    SwipeRefreshLayout idMainRefresh;


    private List<Fragment> list;
    // 获取位置
    private LocationManager locationManager;
    private String locationProvider;

    private static String jingweidu;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private  NotificationManager manager;
    private  MyBroadcastReceive broadcastReceive;

    private long EXIT_TIME=0;//点击两个返回键之间的时间
    private ShapeLoadingDialog shapeLoadingDialog;//自定义形状变化的dialog

    private View id_main_layout;//让加载动画有个背景布局
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉原来的actionbar
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//让toolbar覆盖到状态栏上
        //把加载背景中的某些控件去掉
        id_main_layout=findViewById(R.id.id_main_layout);
        id_main_layout.findViewById(R.id.id_main_recyclerview).setVisibility(View.GONE);
        id_main_layout.findViewById(R.id.fab1).setVisibility(View.GONE);

        ButterKnife.bind(this);
        shapeLoadingDialog=new ShapeLoadingDialog(this);
        shapeLoadingDialog.setCanceledOnTouchOutside(false);
        shapeLoadingDialog.show();
        //避免刷新太快没有动画
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initLocation();

                initBroadcastReciver();

                initFragmentViewPager();
                id_main_layout.setVisibility(View.GONE);//加载完成后把背景布局去掉，显示真正的布局
                shapeLoadingDialog.dismiss();
            }
        },2000);


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


    }

    private void initBroadcastReciver() {
        //动态注册广播接收器
        broadcastReceive=new MyBroadcastReceive();
        IntentFilter intentFilter=new IntentFilter("Notification");
        intentFilter.addAction("CityChange");
        registerReceiver(broadcastReceive,intentFilter);
    }

    private void initNotification(Intent intent) {
        String title=intent.getStringExtra("city");
        String content=intent.getStringExtra("content");
        String code=intent.getStringExtra("code");
        int iconID= SelectWeatherImage.selectImageView(code);

        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder= new NotificationCompat.Builder(this);
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        mBuilder.setContentTitle(title).setContentText(content).setContentIntent(pendingIntent)
                .setWhen(System.currentTimeMillis()).setSmallIcon(iconID);

        Notification notification=mBuilder.build();
        notification.flags=Notification.FLAG_NO_CLEAR;
        manager.notify(1,notification);
        Log.d("MyService", "onCreate executed");
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
        list = new ArrayList<>();
        DayFragment dayFragment = new DayFragment();
        NightFragment nightFragment = new NightFragment();
        ChartFragment chartFragment=new ChartFragment();

        SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
        final Editor editor = sp.edit();
        editor.putString("locationStr", jingweidu);
        editor.commit();
//

        list.add(dayFragment);
        list.add(nightFragment);
        list.add(chartFragment);

        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), list);
        idFragmentViewpager.setAdapter(myFragmentPagerAdapter);


        idFragmentViewpager.setPageTransformer(true, new AlphaTransformer());

        idFragmentViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
//                myFragmentPagerAdapter.notifyDataSetChanged();
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
        //载入完成，隐藏进度动画


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationManager != null) {
            //移除监听器

            locationManager.removeUpdates(locationListener);
        }
        unregisterReceiver(broadcastReceive);
        manager.cancelAll();
    }


    class MyBroadcastReceive extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("Notification")) {
                initNotification(intent);
            }else if(intent.getAction().equals("CityChange")) {
                myFragmentPagerAdapter.notifyDataSetChanged();
            }
        }
    }


    /**
     * 监听返回键是否被按下**/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK&&event.getAction()==KeyEvent.ACTION_DOWN)//返回键按键被按下
        {
            //System.currentTimeMillis()  当前时间与协调世界时 1970 年 1 月 1 日午夜之间的时间差（以毫秒为单位测量）。
            if(System.currentTimeMillis()-EXIT_TIME>2500)//第一次按下必定通过，第二次按下则判断两次返回键的时间差
            {
                Toast.makeText(this, "再按一次返回键退出程序", Toast.LENGTH_SHORT).show();
                EXIT_TIME=System.currentTimeMillis();//吧Exit_Time设置为当前时间
            }
            else
            {
                finish();
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
