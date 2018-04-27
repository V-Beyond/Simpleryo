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
 * @author huanglei
 * @version V1.0
 * @Title: JingXuanAdapter
 * @Package com.hpkj.kexue.adapter
 * @Description: 精选推荐item适配器
 * @date 2017/11/10 18:55
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
//        if (position == 0) {
//            ((CourseTypeItemViewHolder) holder).iv_course_type.setImageResource(R.mipmap.iv_course_one);
//        } else if (position == 1) {
//            ((CourseTypeItemViewHolder) holder).iv_course_type.setImageResource(R.mipmap.iv_course_two);
//        } else if (position == 2) {
//            ((CourseTypeItemViewHolder) holder).iv_course_type.setImageResource(R.mipmap.iv_course_three);
//        } else if (position == 3) {
//            ((CourseTypeItemViewHolder) holder).iv_course_type.setImageResource(R.mipmap.iv_course_four);
//        } else if (position == 4) {
//            ((CourseTypeItemViewHolder) holder).iv_course_type.setImageResource(R.mipmap.iv_course_five);
//        } else if (position == 5) {
//            ((CourseTypeItemViewHolder) holder).iv_course_type.setImageResource(R.mipmap.iv_course_six);
//        } else if (position == 6) {
//            ((CourseTypeItemViewHolder) holder).iv_course_type.setImageResource(R.mipmap.iv_course_seven);
//        } else if (position == 7) {
//            ((CourseTypeItemViewHolder) holder).iv_course_type.setImageResource(R.mipmap.iv_course_eight);
//        }
        Picasso.with(mContext).load(bean.getImageUrl()).transform(transformation).into(((CourseTypeItemViewHolder) holder).iv_course_type);
        if (position==7){
            ((CourseTypeItemViewHolder) holder).tv_course_type.setText("更多");
        }else{

            ((CourseTypeItemViewHolder) holder).tv_course_type.setText(bean.getName());
        }

    }
    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }
}
