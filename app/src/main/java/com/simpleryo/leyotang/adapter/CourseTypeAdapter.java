package com.simpleryo.leyotang.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.bean.HomeDataBean;
import com.simpleryo.leyotang.viewholder.CourseTypeItemViewHolder;
import com.simpleryo.leyotang.viewholder.SuperViewHolder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

/**
 * @ClassNname：CourseTypeAdapter.java
 * @Describe 课程分类适配器
 * @author huanglei
 * @time 2018/5/14 10:11
 */

public class CourseTypeAdapter extends BaseAdapter<HomeDataBean.DataBeanX.CourseTypesBean> {
    public Transformation transformation = new RoundedTransformationBuilder()
            .cornerRadiusDp(30)
            .oval(true)
            .build();
    public CourseTypeAdapter(Context context) {
        super(context);
    }

    @Override
    public SuperViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        return new CourseTypeItemViewHolder(layoutInflater.inflate(R.layout.layout_course_type_item, parent, false));
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        final HomeDataBean.DataBeanX.CourseTypesBean bean = listData.get(position);
        if (position==7){
            ((CourseTypeItemViewHolder) holder).iv_course_type.setImageResource(R.mipmap.iv_course_more);
            ((CourseTypeItemViewHolder) holder).tv_course_type.setText("更多分类");
        }else{
            Picasso.with(mContext).load(bean.getImageUrl()).transform(transformation).into(((CourseTypeItemViewHolder) holder).iv_course_type);
            ((CourseTypeItemViewHolder) holder).tv_course_type.setText(bean.getName());
        }

    }
    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }
}
