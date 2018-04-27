package com.simpleryo.leyotang.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.bean.HomeDataBean;
import com.simpleryo.leyotang.viewholder.AllCourseViewHolder;
import com.simpleryo.leyotang.viewholder.SuperViewHolder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;


/**
 * @author huanglei
 * @version V1.0
 * @Title: JingXuanAdapter
 * @Package com.hpkj.kexue.adapter
 * @Description: 精选推荐item适配器
 * @date 2017/11/10 18:55
 */

public class AllCoureseAdapter extends BaseAdapter<HomeDataBean.DataBeanX.CourseTypesBean> {
    public Transformation transformation = new RoundedTransformationBuilder()
            .cornerRadiusDp(30)
            .oval(true)
            .build();

    public AllCoureseAdapter(Context context) {
        super(context);
    }

    @Override
    public SuperViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        return new AllCourseViewHolder(layoutInflater.inflate(R.layout.layout_course_all_item, parent, false));
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        final HomeDataBean.DataBeanX.CourseTypesBean bean = listData.get(position);
//        if (position == 0) {
//            ((AllCourseViewHolder) holder).iv_course_type.setImageResource(R.mipmap.iv_course_one);
//        } else if (position == 1) {
//            ((AllCourseViewHolder) holder).iv_course_type.setImageResource(R.mipmap.iv_course_two);
//        } else if (position == 2) {
//            ((AllCourseViewHolder) holder).iv_course_type.setImageResource(R.mipmap.iv_course_three);
//        } else if (position == 3) {
//            ((AllCourseViewHolder) holder).iv_course_type.setImageResource(R.mipmap.iv_course_four);
//        } else if (position == 4) {
//            ((AllCourseViewHolder) holder).iv_course_type.setImageResource(R.mipmap.iv_course_five);
//        } else if (position == 5) {
//            ((AllCourseViewHolder) holder).iv_course_type.setImageResource(R.mipmap.iv_course_six);
//        } else if (position == 6) {
//            ((AllCourseViewHolder) holder).iv_course_type.setImageResource(R.mipmap.iv_course_seven);
//        } else if (position == 7) {
//            ((AllCourseViewHolder) holder).iv_course_type.setImageResource(R.mipmap.iv_course_eight);
//        }
        Picasso.with(mContext).load(bean.getImageUrl()).transform(transformation).into(((AllCourseViewHolder) holder).iv_course_type);
        ((AllCourseViewHolder) holder).tv_course_type.setText(bean.getName());
    }

    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }
}
