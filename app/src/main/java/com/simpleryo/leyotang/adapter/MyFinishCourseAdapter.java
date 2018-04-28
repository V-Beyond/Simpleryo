package com.simpleryo.leyotang.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;

import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.activity.CourseDetailActivity;
import com.simpleryo.leyotang.bean.BuyedCouseListBean;
import com.simpleryo.leyotang.viewholder.MyCourseItemViewHolder;
import com.simpleryo.leyotang.viewholder.SuperViewHolder;
import com.squareup.picasso.Picasso;


/**
 * @author huanglei
 * @version V1.0
 * @Title: JingXuanAdapter
 * @Package com.hpkj.kexue.adapter
 * @Description: 精选推荐item适配器
 * @date 2017/11/10 18:55
 */

public class MyFinishCourseAdapter extends BaseAdapter<BuyedCouseListBean.DataBeanX> {

    public MyFinishCourseAdapter(Context context) {
        super(context);
    }

    @Override
    public SuperViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        return new MyCourseItemViewHolder(layoutInflater.inflate(R.layout.layout_my_course_item, parent, false));
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        final BuyedCouseListBean.DataBeanX bean = listData.get(position);
        if (bean.getCoverUrl() != null) {
            Picasso.with(mContext).load(bean.getCoverUrl()).into(((MyCourseItemViewHolder) holder).iv_collection_img);
        } else {
            Picasso.with(mContext).load("http://p3.so.qhimgs1.com/bdr/_240_/t01144f848052b04663.jpg").into(((MyCourseItemViewHolder) holder).iv_collection_img);
        }
        if (bean.getName() != null) {
            ((MyCourseItemViewHolder) holder).tv_collection_name.setText(bean.getName());
        } else {
            ((MyCourseItemViewHolder) holder).tv_collection_name.setText("暂无课程名称");
        }
        ((MyCourseItemViewHolder) holder).tv_complete_count.setText("已结束");
        ((MyCourseItemViewHolder) holder).tv_course_time.setVisibility(View.GONE);
        ((MyCourseItemViewHolder) holder).horizontal_progressbar.setProgressColor(Color.parseColor("#6b6a6b"));
        ((MyCourseItemViewHolder) holder).horizontal_progressbar.setProgress(100);
        ((MyCourseItemViewHolder) holder).tv_to_course_detail.setTextColor(Color.parseColor("#15858c"));
        ((MyCourseItemViewHolder) holder).tv_to_course_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, CourseDetailActivity.class).putExtra("courseId", bean.getCourseId()));
            }
        });

    }
    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }
}
