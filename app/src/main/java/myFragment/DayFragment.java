package myFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.example.haiyuan1995.myapplication.R;
import com.example.haiyuan1995.myapplication.SearchActivity;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import Adapter.DayAdapter;
import App.MyApplication;
import CustomView.FabAnim;
import CustomView.NumberView;
import GsonBean.DailyWeatherData;
import GsonBean.WeatherNowData;
import RecycleViewAnim.SlideInRightAnimatorAdapter;
import Utils.SelectWeatherImage;
import WeatherApiURL.Url;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主界面的fragment
 */

public class DayFragment extends Fragment implements DayAdapter.RecyItemOnclick,View.OnClickListener {


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
    @BindView(R.id.fab1)
    FloatingActionButton fab1;
    @BindView(R.id.fab2)
    FloatingActionButton fab2;
    @BindView(R.id.fab3)
    FloatingActionButton fab3;
    @BindView(R.id.fab4)
    FloatingActionButton fab4;


    private DayAdapter mDayAdapter;
    private String locationStr;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.app_bar_main, container, false);
        ButterKnife.bind(this, view);
        initLocation();
        initView(view);

        initData();
        initDailyWeather();

        return view;
    }



    private void initLocation() {
        locationStr = getActivity().getSharedPreferences("config", getActivity().MODE_PRIVATE).getString("locationStr", "");

    }

    public void initView(View view) {

        fab1 = (FloatingActionButton) view.findViewById(R.id.fab1);

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float h = view.getHeight();//得到主按钮的高度
                int i=fab2.getVisibility();

                if (i==View.VISIBLE)//显示和隐藏按钮
                {
                    hideFab(h);

                }else{
                    showFab(h);
                }
            }
        });
        fab1.setImageResource(R.mipmap.sun);

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"fab3",Toast.LENGTH_SHORT).show();
            }
        });
        fab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"fab4",Toast.LENGTH_SHORT).show();
            }
        });

        idMainRecyclerview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                SwipeRefreshLayout idMainRefresh= (SwipeRefreshLayout) getActivity().findViewById(R.id.id_main_refresh);
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
    public void hideFab(float h) {
        FabAnim.hideView(fab2,h*1.2f);
        FabAnim.hideView(fab3,h*2.4f);
        FabAnim.hideView(fab4,h*3.6f);
    }

    public void showFab(float h) {
        FabAnim.showView(fab2,h*1.2f);
        FabAnim.showView(fab3,h*2.4f);
        FabAnim.showView(fab4,h*3.6f);
    }


    public void initDailyWeather() {//初始化近期天气数据列表,根据经纬度查询
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
        String weathernow;
        if (locationStr.length() == 0 || locationStr == null) {
            weathernow = Url.Weather_Now_Url + "?key=" + MyApplication.Key + "&location=ip";
        } else {
            //默认用IP地址作为查询天气的城市
            weathernow = Url.Weather_Now_Url + "?key=" + MyApplication.Key + "&location=" + locationStr;
        }

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
        String dateTime = data.getResults().get(0).getLast_update();
        dateTime = dateTime.replace("T", "");

        try {
            long last_update = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss").parse(dateTime).getTime();
            String last_time = String.valueOf((System.currentTimeMillis() - last_update) / 1000 / 60);
            if (Integer.valueOf(last_time) > 0) {
                idFragmentLastUpdate.setText(last_time + "分钟前 发布");
            } else {
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

        //发送一条广播用于显示通知栏
        Intent  intent=new Intent();
        intent.putExtra("city",data.getResults().get(0).getLocation().getName());
        intent.putExtra("content",data.getResults().get(0).getNow().getText()+"\t\t\t\t"+data.getResults().get(0).getNow().getTemperature()+"℃");
        intent.putExtra("code",data.getResults().get(0).getNow().getCode());
        intent.setAction("Notification");
        getActivity().sendBroadcast(intent);
    }

    public void initAdapter(DailyWeatherData dailyWeatherData) {
        mDayAdapter = new DayAdapter(getActivity(), dailyWeatherData);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3,GridLayoutManager.HORIZONTAL,false);
        mDayAdapter.setRecyItemOnclick(this);
        SlideInRightAnimatorAdapter rightAnimatorAdapter=new SlideInRightAnimatorAdapter(mDayAdapter,idMainRecyclerview);
        rightAnimatorAdapter.getViewAnimator().setInitialDelayMillis(500);
        idMainRecyclerview.setLayoutManager(gridLayoutManager);
        idMainRecyclerview.setAdapter(rightAnimatorAdapter);
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

    @Override
    public void onClick(View v) {


    }

}
