package com.simpleryo.leyotang.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.bean.CourseListBean;
import com.simpleryo.leyotang.viewholder.ExcellentCourseItemViewHolder;
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

public class BusinessCourseAdapter extends BaseAdapter<CourseListBean.DataBeanX> {

    public BusinessCourseAdapter(Context context) {
        super(context);
    }

    @Override
    public SuperViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        return new ExcellentCourseItemViewHolder(layoutInflater.inflate(R.layout.layout_excellent_course_item, parent, false));
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        final CourseListBean.DataBeanX bean = listData.get(position);
        if (bean.getCoverUrl()!=null){
            Picasso.with(mContext).load(bean.getCoverUrl()).into(((ExcellentCourseItemViewHolder) holder).iv_collection_img);
        }else{
            Picasso.with(mContext).load("http://p0.so.qhmsg.com/bdr/_240_/t01eb2a6c6319b04655.jpg").into(((ExcellentCourseItemViewHolder) holder).iv_collection_img);
        }
        if (bean.getName()!=null){
            ((ExcellentCourseItemViewHolder) holder).tv_collection_name.setText(bean.getName());
        }else{
            ((ExcellentCourseItemViewHolder) holder).tv_collection_name.setText("暂无课程名称");
        }
        ((ExcellentCourseItemViewHolder) holder).tv_price.setText(bean.getPrice()+"$/hour");
        ((ExcellentCourseItemViewHolder) holder).tv_popular.setText(bean.getClassCount()+" people");

    }
    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }
}
