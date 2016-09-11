package Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.haiyuan1995.myapplication.R;

import GsonBean.AirQualityData;

/**
 * Created by haiyuan 1995 on 2016/9/11.
 * 空气质量的recycleview的适配器
 */

public class AirAdapter extends RecyclerView.Adapter<AirAdapter.MyHolder> {
        private  Context mContext;
    private LayoutInflater mLayoutInflater;
    private AirQualityData mAirQualityData;
    public AirAdapter(Context context, AirQualityData airQualityData) {
        this.mContext=context;
        this.mAirQualityData=airQualityData;
        this.mLayoutInflater=LayoutInflater.from(context);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mLayoutInflater.inflate(R.layout.air_recycleview_item,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        AirQualityData.ResultsBean.AirBean.StationsBean stationsBean=mAirQualityData.
                getResults().get(0).getAir().getStations().get(position);
        int aqi;
        if (stationsBean.getAqi()!=null) {
            aqi = Integer.parseInt(stationsBean.getAqi());
        }else {
            aqi=0;//无aqi数据时为零
        }
            String airquality;
//
                if (aqi>0&&aqi<=50){
                    airquality="优";
                }else if (aqi>50&&aqi<=100){
                    airquality="良";
                }else if (aqi>100&&aqi<=150){
                    airquality="轻度污染";
                }else if (aqi>150&&aqi<=200){
                    airquality="中度污染";
                }else if (aqi>200&&aqi<300){
                    airquality="重度污染";
                }else if (aqi==0){
                    airquality="暂无数据";
                }else{
                    airquality="严重污染";
                }

        holder.tv_station.setText(stationsBean.getStation());
        if (aqi==0) {
            holder.tv_aqi.setText("0");
        }else{
            holder.tv_aqi.setText(stationsBean.getAqi());
        }
        holder.tv_quality.setText(airquality);
        if (position%2!=0){
            holder.linearLayout.setBackgroundColor(ContextCompat.getColor(mContext,R.color.white_half));
        }
    }

    @Override
    public int getItemCount() {
        return mAirQualityData.getResults().get(0).getAir().getStations().size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
            TextView tv_station;
        TextView tv_aqi;
        TextView tv_quality;
        LinearLayout linearLayout;
        private MyHolder(View itemView) {
            super(itemView);
            tv_aqi= (TextView) itemView.findViewById(R.id.id_air_item_aqi);
            tv_station= (TextView) itemView.findViewById(R.id.id_air_item_station);
            tv_quality= (TextView) itemView.findViewById(R.id.id_air_item_quality);
            linearLayout= (LinearLayout) itemView.findViewById(R.id.id_air_item_linearlayout);
        }
    }
}
