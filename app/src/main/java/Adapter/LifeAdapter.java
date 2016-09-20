package Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.haiyuan1995.myapplication.R;

import GsonBean.LifeWeatherData;

public  class LifeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context context;
    private LayoutInflater layoutInflater;
    private LifeWeatherData lifeWeatherData;
    public enum  ITEM_TYPE{
        ITEM1,ITEM2,ITEM3,ITEM4,ITEM5
    }
    public LifeAdapter(Context context,LifeWeatherData lifeWeatherData){
        this.context=context;
        this.lifeWeatherData=lifeWeatherData;
        layoutInflater=LayoutInflater.from(context);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==ITEM_TYPE.ITEM1.ordinal()){
            return new Item1ViewHolder(layoutInflater.inflate(R.layout.activity_life_recyclerview_item,parent,false));
        }if (viewType==ITEM_TYPE.ITEM2.ordinal()){
            return new Item2ViewHolder(layoutInflater.inflate(R.layout.activity_life_recyclerview_item,parent,false));
        }if (viewType==ITEM_TYPE.ITEM3.ordinal()){
            return new Item3ViewHolder(layoutInflater.inflate(R.layout.activity_life_recyclerview_item,parent,false));
        }if (viewType==ITEM_TYPE.ITEM4.ordinal()){
            return new Item4ViewHolder(layoutInflater.inflate(R.layout.activity_life_recyclerview_item,parent,false));
        }else {
            return new Item5ViewHolder(layoutInflater.inflate(R.layout.activity_life_recyclerview_item,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof Item1ViewHolder){
                ((Item1ViewHolder) holder).tv_life.setText("穿衣");
                ((Item1ViewHolder) holder).tv_zhishu.setText(lifeWeatherData.getResults().get(0).getSuggestion().getDressing().getBrief());
                ((Item1ViewHolder) holder).life_content.setText("Tip: "+lifeWeatherData.getResults().get(0).getSuggestion().getDressing().getDetails());

        }else if (holder instanceof Item2ViewHolder){
                ((Item2ViewHolder) holder).tv_life.setText("运动");
                ((Item2ViewHolder) holder).tv_zhishu.setText(lifeWeatherData.getResults().get(0).getSuggestion().getSport().getBrief());
                ((Item2ViewHolder) holder).life_content.setText("Tip: "+lifeWeatherData.getResults().get(0).getSuggestion().getSport().getDetails());

        }else if (holder instanceof Item3ViewHolder){
                ((Item3ViewHolder) holder).tv_life.setText("交通");
                ((Item3ViewHolder) holder).tv_zhishu.setText(lifeWeatherData.getResults().get(0).getSuggestion().getTraffic().getBrief());
                ((Item3ViewHolder) holder).life_content.setText("Tip: "+lifeWeatherData.getResults().get(0).getSuggestion().getTraffic().getDetails());

        }else  if (holder instanceof Item4ViewHolder){
                ((Item4ViewHolder) holder).tv_life.setText("紫外线");
                ((Item4ViewHolder) holder).tv_zhishu.setText(lifeWeatherData.getResults().get(0).getSuggestion().getUv().getBrief());
                ((Item4ViewHolder) holder).life_content.setText("Tip: "+lifeWeatherData.getResults().get(0).getSuggestion().getUv().getDetails());

        }else {
                ((Item5ViewHolder) holder).tv_life.setText("舒适度");
                ((Item5ViewHolder) holder).tv_zhishu.setText(lifeWeatherData.getResults().get(0).getSuggestion().getComfort().getBrief());
                ((Item5ViewHolder) holder).life_content.setText("Tip: "+lifeWeatherData.getResults().get(0).getSuggestion().getComfort().getDetails());
        }
    }
public  int getItemViewType(int position){
    if (position==0){
        return  ITEM_TYPE.ITEM1.ordinal();
    }if (position==1) {
        return ITEM_TYPE.ITEM2.ordinal();
    }if (position==2) {
        return ITEM_TYPE.ITEM3.ordinal();
    }if (position==3) {
        return ITEM_TYPE.ITEM4.ordinal();
    }else
        return ITEM_TYPE.ITEM5.ordinal();
}
    @Override
    public int getItemCount() {
        return 5;
    }

    private class Item1ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_life,tv_zhishu,life_content;
        public Item1ViewHolder(View itemview) {
            super(itemview);
            tv_life= (TextView) itemview.findViewById(R.id.id_life);
            tv_zhishu= (TextView) itemview.findViewById(R.id.id_life_zhishu);
            life_content= (TextView) itemview.findViewById(R.id.life_content);

        }
    }

    private class Item2ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_life,tv_zhishu,life_content;
        public Item2ViewHolder(View itemview) {
            super(itemview);
            tv_life= (TextView) itemview.findViewById(R.id.id_life);
            tv_zhishu= (TextView) itemview.findViewById(R.id.id_life_zhishu);
            life_content= (TextView) itemview.findViewById(R.id.life_content);
        }
    }

    private class Item3ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_life,tv_zhishu,life_content;
        public Item3ViewHolder(View itemview) {
            super(itemview);
            tv_life= (TextView) itemview.findViewById(R.id.id_life);
            tv_zhishu= (TextView) itemview.findViewById(R.id.id_life_zhishu);
            life_content= (TextView) itemview.findViewById(R.id.life_content);
        }
    }
    private class Item4ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_life,tv_zhishu,life_content;
        public Item4ViewHolder(View itemview) {
            super(itemview);
            tv_life= (TextView) itemview.findViewById(R.id.id_life);
            tv_zhishu= (TextView) itemview.findViewById(R.id.id_life_zhishu);
            life_content= (TextView) itemview.findViewById(R.id.life_content);
        }
    }
    private class Item5ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_life,tv_zhishu,life_content;
        public Item5ViewHolder(View itemview) {
            super(itemview);
            tv_life= (TextView) itemview.findViewById(R.id.id_life);
            tv_zhishu= (TextView) itemview.findViewById(R.id.id_life_zhishu);
            life_content= (TextView) itemview.findViewById(R.id.life_content);
        }
    }

}