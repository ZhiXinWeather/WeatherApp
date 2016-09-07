package Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.haiyuan1995.myapplication.R;
import com.example.haiyuan1995.myapplication.SelectWeatherImage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import GsonBean.DailyWeatherData;

/**
 * Created by haiyuan1995 on 2016/9/7.
 */

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.MyViewHolder>{

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private DailyWeatherData mDailyWeatherData;

    public SearchResultAdapter(Context context,DailyWeatherData dailyWeatherData) {
        this.mContext=context;
        this.mDailyWeatherData=dailyWeatherData;
        mLayoutInflater=LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mLayoutInflater.inflate(R.layout.search_recycleview_item,parent,false);
        MyViewHolder viewHolder=new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String now_hight=mDailyWeatherData.getResults().get(0).getDaily().get(0).getHigh();
        String now_low=mDailyWeatherData.getResults().get(0).getDaily().get(0).getLow();
/**获取时间数据
 * @hour 小时
 * @apm  apm=0 表示上午，apm=1表示下午。
 * */
        Calendar mCalendar=Calendar.getInstance();
        long timemillis=System.currentTimeMillis();
        mCalendar.setTimeInMillis(timemillis);
        int isNight=mCalendar.get(Calendar.HOUR_OF_DAY);
        String weatherdate=mDailyWeatherData.getResults().get(0).getDaily().get(position).getDate();
        int year=Integer.valueOf(weatherdate.substring(0,4));
        int month=Integer.valueOf(weatherdate.substring(5,7))-1;//月份要减1
        int day=Integer.valueOf(weatherdate.substring(8));
        Date date=new Date(year,month,day);
        SimpleDateFormat format=new SimpleDateFormat("EEEE");//转换为星期几

        int backgroundID=SelectWeatherImage.selectBackground(format.format(date));

        int apm=mCalendar.get(Calendar.AM_PM);
        if (apm==0||(apm==1&&isNight<18)){
            holder.tv_weather.setText(mDailyWeatherData.getResults().get(0).getDaily().get(position).getText_day());
            holder.tv_date.setText(format.format(date));
            holder.tv_city.setText(mDailyWeatherData.getResults().get(0).getLocation().getName());
            holder.tv_temperature.setText(mDailyWeatherData.getResults().get(0).getDaily().get(position).getHigh());
            holder.tv_wind_direction.setText(mDailyWeatherData.getResults().get(0).getDaily().get(position).getWind_direction());
            String weatherCode=mDailyWeatherData.getResults().get(0).getDaily().get(position).getCode_day();
            int resourceID= SelectWeatherImage.selectImageView(weatherCode);
            Glide.with(mContext).load(resourceID).into(holder.iv_weather_image);
            Glide.with(mContext).load(backgroundID).into(holder.iv_background);

        }else if(apm==1&&isNight >= 18) {
            holder.tv_weather.setText(mDailyWeatherData.getResults().get(0).getDaily().get(position).getText_night());
            holder.tv_date.setText(format.format(date));
            holder.tv_city.setText(mDailyWeatherData.getResults().get(0).getLocation().getName());
            holder.tv_temperature.setText(mDailyWeatherData.getResults().get(0).getDaily().get(position).getLow());
            holder.tv_wind_direction.setText(mDailyWeatherData.getResults().get(0).getDaily().get(position).getWind_direction());
            String weatherCode = mDailyWeatherData.getResults().get(0).getDaily().get(position).getCode_night();
            int resourceID = SelectWeatherImage.selectImageView(weatherCode);
            Glide.with(mContext).load(resourceID).into(holder.iv_weather_image);
            Glide.with(mContext).load(backgroundID).into(holder.iv_background);
        }
    }

    @Override
    public int getItemCount() {
        return mDailyWeatherData.getResults().get(0).getDaily().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_temperature;
        TextView tv_city;
        TextView tv_weather;
        TextView tv_wind_direction;
        TextView tv_future_temperature;
        TextView tv_date;

        ImageView iv_weather_image;
        ImageView iv_background;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_temperature= (TextView) itemView.findViewById(R.id.id_search_item_temperature);
            tv_city= (TextView) itemView.findViewById(R.id.id_search_item_city);
            tv_weather= (TextView) itemView.findViewById(R.id.id_search_item_weather);
            tv_wind_direction= (TextView) itemView.findViewById(R.id.id_search_item_wind_direction);
            tv_date= (TextView) itemView.findViewById(R.id.id_search_item_date);
            tv_future_temperature= (TextView) itemView.findViewById(R.id.id_search_item_future_temperature);
            iv_weather_image= (ImageView) itemView.findViewById(R.id.id_search_item_weather_image);
            iv_background= (ImageView) itemView.findViewById(R.id.id_search_item_imageview);
        }
    }
}
