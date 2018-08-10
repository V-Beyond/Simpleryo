package com.simpleryo.leyotang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.viewholder.SuperViewHolder;
import com.squareup.picasso.Transformation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @ClassNname：BaseAdapter.java
 * @Describe 基类适配器
 * @author huanglei
 * @time 2018/3/27 13:09
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<SuperViewHolder> {

    LayoutInflater layoutInflater;
    public List<T> listData = new ArrayList<>();
    Context mContext;
    String  course_price;
    public Transformation transformation = new RoundedTransformationBuilder()
            .cornerRadiusDp(30)
            .oval(true)
            .build();
    public Transformation raduisTransformation = new RoundedTransformationBuilder()
            .cornerRadius(20)
            .oval(false)
            .build();
    public BaseAdapter(Context context) {
        mContext = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        course_price=context.getResources().getString(R.string.course_price);
    }

    /**
     * 时间格式化
     * @param time
     * @return
     */
    public static String getFormatTime(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("MM-dd  HH:mm");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;
    }
    @Override
    public SuperViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(getLayoutId(), parent, false);
        return new SuperViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SuperViewHolder holder, int position) {
        onBindItemHolder(holder, position);
    }

    //局部刷新关键：带payload的这个onBindViewHolder方法必须实现
    @Override
    public void onBindViewHolder(SuperViewHolder holder, int position, List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            onBindItemHolder(holder, position, payloads);
        }

    }

    public abstract int getLayoutId();

    public abstract void onBindItemHolder(SuperViewHolder holder, int position);

    public void onBindItemHolder(SuperViewHolder holder, int position, List<Object> payloads) {

    }

    @Override
    public int getItemCount() {
        if (listData != null && listData.size() > 0) {
            return listData.size();
        }
        return 0;
    }

    public List<T> getDataList() {
        return listData;
    }

    public void setDataList(Collection<T> list) {
        if (this.listData != null) {
            this.listData.clear();
        }
        this.listData.addAll(list);
        notifyDataSetChanged();
    }

    public void addAll(Collection<T> list) {
        if (list == null) {
            return;
        }
        int lastIndex = this.listData.size();
        if (this.listData != null) {
            this.listData.addAll(list);
            notifyItemRangeInserted(lastIndex, list.size());
        }
    }
    //不重写这个方法，获取的数据会混乱不堪
    public int getItemViewType(int position) {
        return position;
    }
    public void remove(int position) {
        this.listData.remove(position);
        notifyItemRemoved(position);

        if (position != (getDataList().size())) { // 如果移除的是最后一个，忽略
            notifyItemRangeChanged(position, this.listData.size() - position);
        }
    }

    public void reFresh(List<T> list) {
        this.listData = list;
        notifyDataSetChanged();
    }

    public void clear() {
        if (this.listData != null) {
            this.listData.clear();
            notifyDataSetChanged();
        }
    }

}
