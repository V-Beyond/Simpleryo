package com.simpleryo.leyotang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.bean.HomeDataBean;
import com.simpleryo.leyotang.utils.XStringPars;
import com.simpleryo.leyotang.viewholder.HotCourseItemViewHolder;
import com.simpleryo.leyotang.viewholder.SuperViewHolder;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.text.NumberFormat;


/**
 * @author huanglei
 * @version V1.0
 * @Title: JingXuanAdapter
 * @Package com.hpkj.kexue.adapter
 * @Description: 精选推荐item适配器
 * @date 2017/11/10 18:55
 */

public class HotCourseAdapter extends BaseAdapter<HomeDataBean.DataBeanX.CoursesBeanX.CoursesBean> {

    public HotCourseAdapter(Context context) {
        super(context);
    }

    @Override
    public SuperViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        return new HotCourseItemViewHolder(layoutInflater.inflate(R.layout.layout_hot_course_item, parent, false));
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        final HomeDataBean.DataBeanX.CoursesBeanX.CoursesBean bean = listData.get(position);
        int collectCount=bean.getCollectCount();
        Picasso.with(mContext).load(bean.getCoverUrl()).transform(raduisTransformation).into(((HotCourseItemViewHolder) holder).iv_collection_img);
        ((HotCourseItemViewHolder) holder).tv_collection_name.setText(bean.getName());
        if(bean.getPrice()!=0){
            ((HotCourseItemViewHolder) holder).tv_price.setText(XStringPars.foramtPrice(Integer.valueOf(bean.getPrice()))+"$/"+course_price);
        }else{
            if (bean.getType().equalsIgnoreCase("series")){
                ((HotCourseItemViewHolder) holder).tv_price.setText("免费购买");
            }else if(bean.getType().equalsIgnoreCase("single")){
                ((HotCourseItemViewHolder) holder).tv_price.setText("免费预约");
            }
        }
        if (bean.isHasCardCoupon()){
            ((HotCourseItemViewHolder) holder).iv_course_item_coupon.setVisibility(View.VISIBLE);
        }
        if (bean.getDistance()!=null){
            ((HotCourseItemViewHolder) holder).tv_distance.setVisibility(View.VISIBLE);
            ((HotCourseItemViewHolder) holder).tv_distance.setText(bean.getDistance());
        }
        ((HotCourseItemViewHolder) holder).tv_popular.setText(collectCount+" people");
        ((HotCourseItemViewHolder) holder).tv_collection_count.setText(collectCount+"人收藏");
        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        int totalCount=bean.getUpperLimit();
        int classCount=bean.getClassCount();
        float percent= (float)classCount/ (float)totalCount*100;
        ((HotCourseItemViewHolder) holder).horizontal_progressbar.setProgress((int) percent);
        ((HotCourseItemViewHolder) holder).tv_percent.setText(classCount+"/"+totalCount);
        if (bean.isHasCollect()){
            ((HotCourseItemViewHolder) holder).iv_collection_course.setImageResource(R.mipmap.iv_course_item_collected);
        }else{
            ((HotCourseItemViewHolder) holder).iv_collection_course.setImageResource(R.mipmap.iv_course_item_uncollect);
        }
        ((HotCourseItemViewHolder) holder).iv_collection_course.setOnClickListener(new View.OnClickListener() {
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
