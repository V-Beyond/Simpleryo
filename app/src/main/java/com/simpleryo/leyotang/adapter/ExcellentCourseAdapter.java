package com.simpleryo.leyotang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.bean.HomeDataBean;
import com.simpleryo.leyotang.viewholder.ExcellentCourseItemViewHolder;
import com.simpleryo.leyotang.viewholder.SuperViewHolder;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;


/**
 * @author huanglei
 * @version V1.0
 * @Title: JingXuanAdapter
 * @Package com.hpkj.kexue.adapter
 * @Description: 精选推荐item适配器
 * @date 2017/11/10 18:55
 */

public class ExcellentCourseAdapter extends BaseAdapter<HomeDataBean.DataBeanX.CoursesBeanX.CoursesBean> {

    public ExcellentCourseAdapter(Context context) {
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
        final HomeDataBean.DataBeanX.CoursesBeanX.CoursesBean bean = listData.get(position);
        Picasso.with(mContext).load(bean.getCoverUrl()).into(((ExcellentCourseItemViewHolder) holder).iv_collection_img);
        ((ExcellentCourseItemViewHolder) holder).tv_collection_name.setText(bean.getName());
        ((ExcellentCourseItemViewHolder) holder).tv_price.setText(bean.getPrice()+"$/hour");
        ((ExcellentCourseItemViewHolder) holder).tv_popular.setText(bean.getClassCount()+" people");
        if (bean.isHasCollect()){
            ((ExcellentCourseItemViewHolder) holder).iv_collection_star.setImageResource(R.mipmap.iv_collection_star);
        }else{
            ((ExcellentCourseItemViewHolder) holder).iv_collection_star.setImageResource(R.mipmap.iv_collection_white_star);
        }
        ((ExcellentCourseItemViewHolder) holder).rl_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new BusEntity(121,bean));//收藏or取消收藏
            }
        });
    }
    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }
}
