package com.simpleryo.leyotang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.bean.HomeDataBean;
import com.simpleryo.leyotang.utils.XStringPars;
import com.simpleryo.leyotang.viewholder.IntroductoryCourseItemViewHolder;
import com.simpleryo.leyotang.viewholder.SuperViewHolder;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.text.NumberFormat;


/**
 * @author huanglei
 * @version V1.0
 * @Title: JingXuanAdapter
 * @Package com.hpkj.kexue.adapter
 * @Description: 精选推荐item适配器
 * @date 2017/11/10 18:55
 */

public class IntroductoryCourseAdapter extends BaseAdapter<HomeDataBean.DataBeanX.CoursesBeanX.CoursesBean> {

    public IntroductoryCourseAdapter(Context context) {
        super(context);
        EventBus.getDefault().register(this);
    }

    @Override
    public SuperViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        return new IntroductoryCourseItemViewHolder(layoutInflater.inflate(R.layout.layout_introductory_course_item, parent, false));
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCollect(BusEntity bus) {

    }
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        final HomeDataBean.DataBeanX.CoursesBeanX.CoursesBean bean = listData.get(position);
        int collectCount=bean.getCollectCount();
        Picasso.with(mContext).load(bean.getCoverUrl()).transform(raduisTransformation).into(((IntroductoryCourseItemViewHolder) holder).iv_collection_img);
        ((IntroductoryCourseItemViewHolder) holder).tv_collection_name.setText(bean.getName());
        if(bean.getPrice()!=0){
            ((IntroductoryCourseItemViewHolder) holder).tv_price.setText(XStringPars.foramtPrice(Integer.valueOf(bean.getPrice()))+"$/"+course_price);
        }else{
            if (bean.getType().equalsIgnoreCase("series")){
                ((IntroductoryCourseItemViewHolder) holder).tv_price.setText("免费购买");
            }else if(bean.getType().equalsIgnoreCase("single")){
                ((IntroductoryCourseItemViewHolder) holder).tv_price.setText("免费预约");
            }
        }
        if (bean.isHasCardCoupon()){
            ((IntroductoryCourseItemViewHolder) holder).iv_course_item_coupon.setVisibility(View.VISIBLE);
        }
        if (bean.getDistance()!=null){
            ((IntroductoryCourseItemViewHolder) holder).tv_distance.setVisibility(View.VISIBLE);
            ((IntroductoryCourseItemViewHolder) holder).tv_distance.setText(new DecimalFormat("0.00").format(Double.valueOf(bean.getDistance())/1000)+"/km");
        }
        ((IntroductoryCourseItemViewHolder) holder).tv_popular.setText(collectCount+" people");
        ((IntroductoryCourseItemViewHolder) holder).tv_collection_count.setText(collectCount+"人收藏");
        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        int totalCount=bean.getUpperLimit();
        float percent= (float)bean.getClassCount() / (float)totalCount*100;
        ((IntroductoryCourseItemViewHolder) holder).horizontal_progressbar.setProgress((int) percent);
        ((IntroductoryCourseItemViewHolder) holder).tv_percent.setText(bean.getClassCount()+"/"+totalCount);
        if (bean.isHasCollect()){
            ((IntroductoryCourseItemViewHolder) holder).iv_collection_course.setImageResource(R.mipmap.iv_course_item_collected);
        }else{
            ((IntroductoryCourseItemViewHolder) holder).iv_collection_course.setImageResource(R.mipmap.iv_course_item_uncollect);
        }
        ((IntroductoryCourseItemViewHolder) holder).iv_collection_course.setOnClickListener(new View.OnClickListener() {
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
