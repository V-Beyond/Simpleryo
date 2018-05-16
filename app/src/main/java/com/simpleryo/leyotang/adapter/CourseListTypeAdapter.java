package com.simpleryo.leyotang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
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
import com.squareup.picasso.Transformation;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * @author huanglei
 * @version V1.0
 * @Title: JingXuanAdapter
 * @Package com.hpkj.kexue.adapter
 * @Description: 精选推荐item适配器
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

    public Transformation transformation = new RoundedTransformationBuilder()
            .cornerRadius(10)
            .oval(false)
            .build();

    @Override
    public void onBindItemHolder(SuperViewHolder holder, final int position) {
        final MultipleItem bean= mDataList.get(position);
        if (holder instanceof HotCourseItemViewHolder){
            Picasso.with(mContext).load(bean.getCourseListBean().getCoverUrl()).into(((HotCourseItemViewHolder) holder).iv_collection_img);
            ((HotCourseItemViewHolder) holder).tv_collection_name.setText(bean.getCourseListBean().getName());
            ((HotCourseItemViewHolder) holder).tv_price.setText(XStringPars.foramtPrice(Integer.valueOf(bean.getCourseListBean().getPrice()))+"$/"+course_price);
            ((HotCourseItemViewHolder) holder).tv_popular.setText(bean.getCourseListBean().getClassCount()+" people");
            if (bean.getCourseListBean().isHasCollect()){
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
        }
        if (holder instanceof ExcellentCourseItemViewHolder){
            Picasso.with(mContext).load(bean.getCourseListBean().getCoverUrl()).into(((ExcellentCourseItemViewHolder) holder).iv_collection_img);
            ((ExcellentCourseItemViewHolder) holder).tv_collection_name.setText(bean.getCourseListBean().getName());
            ((ExcellentCourseItemViewHolder) holder).tv_price.setText(XStringPars.foramtPrice(Integer.valueOf(bean.getCourseListBean().getPrice()))+"$/"+course_price);
            ((ExcellentCourseItemViewHolder) holder).tv_popular.setText(bean.getCourseListBean().getClassCount()+" people");
            if (bean.getCourseListBean().isHasCollect()){
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
        }
        if (holder instanceof IntroductoryCourseItemViewHolder){
            Picasso.with(mContext).load(bean.getCourseListBean().getCoverUrl()).into(((IntroductoryCourseItemViewHolder) holder).iv_collection_img);
            ((IntroductoryCourseItemViewHolder) holder).tv_collection_name.setText(bean.getCourseListBean().getName());
            ((IntroductoryCourseItemViewHolder) holder).tv_price.setText(XStringPars.foramtPrice(Integer.valueOf(bean.getCourseListBean().getPrice()))+"$/"+course_price);
            ((IntroductoryCourseItemViewHolder) holder).tv_popular.setText(bean.getCourseListBean().getClassCount()+" people");
            if (bean.getCourseListBean().isHasCollect()){
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
        }
    }
}
