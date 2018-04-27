package com.simpleryo.leyotang.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.activity.CourseDetailActivity;
import com.simpleryo.leyotang.bean.OrderListBean;
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

public class MyCourseAdapter extends BaseAdapter<OrderListBean.DataBean> {

    public MyCourseAdapter(Context context) {
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
        final OrderListBean.DataBean bean = listData.get(position);
        if (bean.getImageUrl()!=null){
            Picasso.with(mContext).load(bean.getImageUrl()).into(((MyCourseItemViewHolder) holder).iv_collection_img);
        }else{
            Picasso.with(mContext).load("http://p3.so.qhimgs1.com/bdr/_240_/t01144f848052b04663.jpg").into(((MyCourseItemViewHolder) holder).iv_collection_img);
        }
        if (bean.getCourseName()!=null){
            ((MyCourseItemViewHolder) holder).tv_collection_name.setText(bean.getCourseName());
        }else{
            ((MyCourseItemViewHolder) holder).tv_collection_name.setText("暂无课程名称");
        }
        ((MyCourseItemViewHolder) holder).tv_to_course_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, CourseDetailActivity.class).putExtra("courseId",bean.getCourseId()));
            }
        });

    }
    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }
}
