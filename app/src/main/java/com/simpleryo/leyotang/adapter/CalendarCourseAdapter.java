package com.simpleryo.leyotang.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.activity.MyCourseActivity;
import com.simpleryo.leyotang.activity.MyCourseDetailActivity;
import com.simpleryo.leyotang.bean.CalendarListBean;
import com.simpleryo.leyotang.viewholder.CalendarCourseItemViewHolder;
import com.simpleryo.leyotang.viewholder.SuperViewHolder;


/**
 * @author huanglei
 * @version V1.0
 * @Title: JingXuanAdapter
 * @Package com.hpkj.kexue.adapter
 * @Description: 精选推荐item适配器
 * @date 2017/11/10 18:55
 */

public class CalendarCourseAdapter extends BaseAdapter<CalendarListBean.DataBean.CourseListBean> {

    public CalendarCourseAdapter(Context context) {
        super(context);
    }

    @Override
    public SuperViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        return new CalendarCourseItemViewHolder(layoutInflater.inflate(R.layout.layout_calendar_course_item, parent, false));
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        final CalendarListBean.DataBean.CourseListBean bean = listData.get(position);
        if (position <7) {
            if (bean.getCourse().getName() != null) {
                ((CalendarCourseItemViewHolder) holder).tv_course_name.setText(bean.getCourse().getName());
            } else {
                ((CalendarCourseItemViewHolder) holder).tv_course_name.setText("暂无名称");
            }
            ((CalendarCourseItemViewHolder) holder).tv_course_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext.startActivity(new Intent(mContext, MyCourseDetailActivity.class).putExtra("id", bean.getOrderId()));
                }
            });
        }else  if (position ==7) {
            ((CalendarCourseItemViewHolder) holder).tv_course_name.setVisibility(View.GONE);
            ((CalendarCourseItemViewHolder) holder).tv_course_more.setVisibility(View.VISIBLE);
            ((CalendarCourseItemViewHolder) holder).tv_course_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext.startActivity(new Intent(mContext, MyCourseActivity.class));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }
}
