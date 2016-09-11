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
import GsonBean.AirRankingData;

/**
 * Created by haiyuan 1995 on 2016/9/11.
 * 空气质量的recycleview的适配器
 */

public class AirRankingAdapter extends RecyclerView.Adapter<AirRankingAdapter.MyHolder> {
    private  Context mContext;
    private LayoutInflater mLayoutInflater;
    private AirRankingData mAirRankingData;
    public AirRankingAdapter(Context context, AirRankingData airRankingData) {
        this.mContext=context;
        this.mAirRankingData=airRankingData;
        this.mLayoutInflater=LayoutInflater.from(context);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mLayoutInflater.inflate(R.layout.air_recycleview_item,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.tv_city.setText(mAirRankingData.getResults().get(position).getLocation().getName());
        holder.tv_aqi.setText(mAirRankingData.getResults().get(position).getAqi());
        holder.tv_no.setText(position+1+"");//名次

        if (position%2!=0){
            holder.linearLayout.setBackgroundColor(ContextCompat.getColor(mContext,R.color.white_half));
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView tv_city;
        TextView tv_aqi;
        TextView tv_no;
        LinearLayout linearLayout;
        private MyHolder(View itemView) {
            super(itemView);
            tv_aqi= (TextView) itemView.findViewById(R.id.id_air_item_aqi);
            tv_city= (TextView) itemView.findViewById(R.id.id_air_item_station);
            tv_no= (TextView) itemView.findViewById(R.id.id_air_item_quality);
            linearLayout= (LinearLayout) itemView.findViewById(R.id.id_air_item_linearlayout);
        }
    }
}
