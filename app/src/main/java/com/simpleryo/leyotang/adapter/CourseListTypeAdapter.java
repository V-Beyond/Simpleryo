package com.simpleryo.leyotang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.bean.MultipleItem;
import com.simpleryo.leyotang.utils.XStringPars;
import com.simpleryo.leyotang.viewholder.EmptyViewHolder;
import com.simpleryo.leyotang.viewholder.ExcellentCourseItemViewHolder;
import com.simpleryo.leyotang.viewholder.HotCourseItemViewHolder;
import com.simpleryo.leyotang.viewholder.IntroductoryCourseItemViewHolder;
import com.simpleryo.leyotang.viewholder.SuperViewHolder;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.NumberFormat;


/**
 * @author huanglei
 * @version V1.0
 * @Title: CourseListTypeAdapter
 * @Package com.simpleryo.leyotang.adapter
 * @Description: 课程分类适配器
 * @date 2017/11/10 18:55
 */

public class CourseListTypeAdapter extends BaseMultiAdapter<MultipleItem> {
    Context context;

    public CourseListTypeAdapter(Context context) {
        super(context);
        this.context = context;
        EventBus.getDefault().register(this);
    }

    @Override
    public SuperViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == MultipleItem.HOMEHOTCOURSE) {
            return new HotCourseItemViewHolder(mInflater.inflate(R.layout.layout_hot_course_item, parent, false));
        } else if (viewType == MultipleItem.HOMEEXCELLENT) {
            return new ExcellentCourseItemViewHolder(mInflater.inflate(R.layout.layout_excellent_course_item, parent, false));
        } else if (viewType == MultipleItem.HOMEINTRODUCTORYCOURSE) {
            return new IntroductoryCourseItemViewHolder(mInflater.inflate(R.layout.layout_introductory_course_item, parent, false));
        } else {//所有都没有的情况
            return new EmptyViewHolder(mInflater.inflate(R.layout.layout_empty_item, parent, false));
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCollect(BusEntity bus) {

    }


    @Override
    public void onBindItemHolder(SuperViewHolder holder, final int position) {
        final MultipleItem bean= mDataList.get(position);
        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        int totalCount=bean.getCourseListBean().getUpperLimit();//上线人数
        int classCount=bean.getCourseListBean().getClassCount();//已下单人数
        float percent= (float)classCount/ (float)totalCount*100;//计算百分比
        String coverUrl=bean.getCourseListBean().getCoverUrl();//封面图片
        int price=bean.getCourseListBean().getPrice();//价格
        boolean hasCollect=bean.getCourseListBean().isHasCollect();//是否收藏
        if (holder instanceof HotCourseItemViewHolder){//热门
            Picasso.with(mContext).load(coverUrl).transform(raduisTransformation).into(((HotCourseItemViewHolder) holder).iv_collection_img);
            ((HotCourseItemViewHolder) holder).tv_collection_name.setText(bean.getCourseListBean().getName());
            ((HotCourseItemViewHolder) holder).tv_price.setText(XStringPars.foramtPrice(price)+"$/"+course_price);
            ((HotCourseItemViewHolder) holder).tv_popular.setText(classCount+" people");
            if (hasCollect){
                ((HotCourseItemViewHolder) holder).iv_collection_star.setImageResource(R.mipmap.iv_collection_star);
            }else{
                ((HotCourseItemViewHolder) holder).iv_collection_star.setImageResource(R.mipmap.iv_collection_white_star);
            }
            ((HotCourseItemViewHolder) holder).rl_collect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EventBus.getDefault().post(new BusEntity(131,bean.getCourseListBean()));//收藏or取消收藏
                }
            });
            ((HotCourseItemViewHolder) holder).horizontal_progressbar.setProgress((int) percent);
            ((HotCourseItemViewHolder) holder).tv_percent.setText(classCount+"/"+totalCount);
        }
        if (holder instanceof ExcellentCourseItemViewHolder){//精品
            Picasso.with(mContext).load(coverUrl).transform(raduisTransformation).into(((ExcellentCourseItemViewHolder) holder).iv_collection_img);
            ((ExcellentCourseItemViewHolder) holder).tv_collection_name.setText(bean.getCourseListBean().getName());
            ((ExcellentCourseItemViewHolder) holder).tv_price.setText(XStringPars.foramtPrice(price)+"$/"+course_price);
            ((ExcellentCourseItemViewHolder) holder).tv_popular.setText(classCount+" people");
            if (hasCollect){
                ((ExcellentCourseItemViewHolder) holder).iv_collection_star.setImageResource(R.mipmap.iv_collection_star);
            }else{
                ((ExcellentCourseItemViewHolder) holder).iv_collection_star.setImageResource(R.mipmap.iv_collection_white_star);
            }
            ((ExcellentCourseItemViewHolder) holder).rl_collect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EventBus.getDefault().post(new BusEntity(131,bean.getCourseListBean()));//收藏or取消收藏
                }
            });
            ((ExcellentCourseItemViewHolder) holder).horizontal_progressbar.setProgress((int) percent);
            ((ExcellentCourseItemViewHolder) holder).tv_percent.setText(classCount+"/"+totalCount);
        }
        if (holder instanceof IntroductoryCourseItemViewHolder){//官方
            Picasso.with(mContext).load(coverUrl).transform(raduisTransformation).into(((IntroductoryCourseItemViewHolder) holder).iv_collection_img);
            ((IntroductoryCourseItemViewHolder) holder).tv_collection_name.setText(bean.getCourseListBean().getName());
            ((IntroductoryCourseItemViewHolder) holder).tv_price.setText(XStringPars.foramtPrice(price)+"$/"+course_price);
            ((IntroductoryCourseItemViewHolder) holder).tv_popular.setText(classCount+" people");
            if (hasCollect){
                ((IntroductoryCourseItemViewHolder) holder).iv_collection_star.setImageResource(R.mipmap.iv_collection_star);
            }else{
                ((IntroductoryCourseItemViewHolder) holder).iv_collection_star.setImageResource(R.mipmap.iv_collection_white_star);
            }
            ((IntroductoryCourseItemViewHolder) holder).rl_collect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EventBus.getDefault().post(new BusEntity(131,bean.getCourseListBean()));//收藏or取消收藏
                }
            });
            ((IntroductoryCourseItemViewHolder) holder).horizontal_progressbar.setProgress((int) percent);
            ((IntroductoryCourseItemViewHolder) holder).tv_percent.setText(classCount+"/"+totalCount);
        }
    }
}
