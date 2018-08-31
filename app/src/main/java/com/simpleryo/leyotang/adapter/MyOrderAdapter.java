package com.simpleryo.leyotang.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.activity.BusinessHomeActivty;
import com.simpleryo.leyotang.activity.OrderDetailActivity;
import com.simpleryo.leyotang.activity.RemarkCourseActivity;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.bean.CollectionListBean;
import com.simpleryo.leyotang.bean.MultipleItem;
import com.simpleryo.leyotang.bean.OrderListBean;
import com.simpleryo.leyotang.utils.XStringPars;
import com.simpleryo.leyotang.viewholder.EmptyViewHolder;
import com.simpleryo.leyotang.viewholder.MyAttentionViewHolder;
import com.simpleryo.leyotang.viewholder.MyCollectionViewHolder;
import com.simpleryo.leyotang.viewholder.MyOrderViewHolder;
import com.simpleryo.leyotang.viewholder.SuperViewHolder;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.NumberFormat;

/**
 * @ClassNname：MyOrderAdapter.java
 * @Describe 我的关注，收藏，订单适配器
 * @author huanglei
 * @time 2018/5/22 16:09
 */

public class MyOrderAdapter extends BaseMultiAdapter<MultipleItem> {
    Context context;

    public MyOrderAdapter(Context context) {
        super(context);
        this.context = context;
        EventBus.getDefault().register(this);
    }

    @Override
    public SuperViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == MultipleItem.ORDER) {//订单
            return new MyOrderViewHolder(mInflater.inflate(R.layout.layout_order_item, parent, false));
        } else if (viewType == MultipleItem.MYATTENTION) {//关注
            return new MyAttentionViewHolder(mInflater.inflate(R.layout.layout_my_attention_item, parent, false));
        } else if (viewType == MultipleItem.MYCOLLECTION) {//收藏
            return new MyCollectionViewHolder(mInflater.inflate(R.layout.layout_my_collection_item, parent, false));
        } else {//所有都没有的情况
            return new EmptyViewHolder(mInflater.inflate(R.layout.layout_empty_item, parent, false));
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCollect(BusEntity bus) {

    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, final int position) {
        final MultipleItem bean;
        bean = mDataList.get(position);
        if (holder instanceof MyOrderViewHolder) {//我的订单
            final OrderListBean.DataBeanX dataBean = bean.getOrderListBean();
            if (dataBean.getImageUrl()!=null){
                Picasso.with(context).load(dataBean.getImageUrl()).transform(raduisTransformation).into(((MyOrderViewHolder) holder).iv_order_img);
            }else{
                Picasso.with(mContext).load("http://p3.so.qhimgs1.com/bdr/_240_/t01144f848052b04663.jpg").transform(transformation).into(((MyOrderViewHolder) holder).iv_order_img);
            }
            ((MyOrderViewHolder) holder).tv_order_name.setText(dataBean.getCourseName());
            ((MyOrderViewHolder) holder).tv_time.setText(XStringPars.getStrTime(dataBean.getCreationTime()));
            ((MyOrderViewHolder) holder).tv_price.setText(XStringPars.foramtPrice(Integer.valueOf(dataBean.getUnitPrice()))+"$");
            ((MyOrderViewHolder) holder).tv_count.setText("x"+dataBean.getQuantity());
            if (dataBean.getStatus().equalsIgnoreCase("COMPLETED")) {
                ((MyOrderViewHolder) holder).tv_state.setText(context.getResources().getString(R.string.Successful_trade));
                ((MyOrderViewHolder) holder).tv_detail.setVisibility(View.GONE);
                ((MyOrderViewHolder) holder).tv_order_remark.setText(context.getResources().getString(R.string.View_evaluation));
            } else if (dataBean.getStatus().equalsIgnoreCase("NEW")) {
                ((MyOrderViewHolder) holder).tv_state.setText(context.getResources().getString(R.string.Waiting_for_payment));
                ((MyOrderViewHolder) holder).tv_detail.setText(context.getResources().getString(R.string.To_pay));
                ((MyOrderViewHolder) holder).tv_order_remark.setText(context.getResources().getString(R.string.Cancel_order));
                ((MyOrderViewHolder) holder).tv_order_remark.setBackgroundResource(R.mipmap.iv_no_remark);
//                ((MyOrderViewHolder) holder).tv_order_remark.setEnabled(false);
                ((MyOrderViewHolder) holder).tv_order_remark.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EventBus.getDefault().post(new BusEntity(112,dataBean));
                    }
                });
                ((MyOrderViewHolder) holder).tv_detail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EventBus.getDefault().post(new BusEntity(111,dataBean));
                    }
                });
            } else if (dataBean.getStatus().equalsIgnoreCase("RECEIVED")) {
                ((MyOrderViewHolder) holder).tv_state.setText(context.getResources().getString(R.string.Attended));
                ((MyOrderViewHolder) holder).tv_detail.setVisibility(View.GONE);
                ((MyOrderViewHolder) holder).tv_order_remark.setText(context.getResources().getString(R.string.To_evaluate));
                ((MyOrderViewHolder) holder).tv_order_remark.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mContext.startActivity(new Intent(mContext, RemarkCourseActivity.class).putExtra("orderId", dataBean.getId()));
                    }
                });
                ((MyOrderViewHolder) holder).tv_order_remark.setBackgroundResource(R.mipmap.iv_no_remark);
            }
            else if (dataBean.getStatus().equalsIgnoreCase("CANCALLED")) {
                ((MyOrderViewHolder) holder).tv_state.setText(context.getResources().getString(R.string.Cancelled));
                ((MyOrderViewHolder) holder).tv_order_remark.setVisibility(View.GONE);
                ((MyOrderViewHolder) holder).tv_detail.setVisibility(View.GONE);
            }
            else if (dataBean.getStatus().equalsIgnoreCase("PAYED")) {
                ((MyOrderViewHolder) holder).tv_state.setText(context.getResources().getString(R.string.Payment_done));
                ((MyOrderViewHolder) holder).tv_order_remark.setVisibility(View.INVISIBLE);
                ((MyOrderViewHolder) holder).tv_detail.setVisibility(View.VISIBLE);
                ((MyOrderViewHolder) holder).tv_detail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mContext.startActivity(new Intent(mContext, OrderDetailActivity.class).putExtra("orderId", dataBean.getId()));
                    }
                });
            }
            ((MyOrderViewHolder) holder).ll_order_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext.startActivity(new Intent(mContext, OrderDetailActivity.class).putExtra("orderId", dataBean.getId()));
                }
            });
        } else if (holder instanceof MyAttentionViewHolder) {//我的关注
            if (bean.getStoreDataBean()!=null){
                Picasso.with(context).load(bean.getStoreDataBean().getCoverUrl()).transform(raduisTransformation).into(((MyAttentionViewHolder) holder).iv_attention_img);
                ((MyAttentionViewHolder) holder).tv_my_attentoin_name.setText(bean.getStoreDataBean().getName());
                int followCount= Integer.parseInt(bean.getStoreDataBean().getFollowCount());
                ((MyAttentionViewHolder) holder).tv_store_follow_count.setText(followCount+" people");
                // 创建一个数值格式化对象
                NumberFormat numberFormat = NumberFormat.getInstance();
                // 设置精确到小数点后2位
                numberFormat.setMaximumFractionDigits(2);
                int totalCount=100;
                if (followCount>=1&&followCount<=99){
                    totalCount=100;
                }
                if (followCount>=100&&followCount<=999){
                    totalCount=1000;
                }
                if (followCount>=1000&&followCount<=9999){
                    totalCount=1000;
                }
                float percent= (float)followCount / (float) totalCount * 100;
                ((MyAttentionViewHolder) holder).horizontal_progressbar.setProgress((int) percent);
                ((MyAttentionViewHolder) holder).tv_to_store_detail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mContext.startActivity(new Intent(mContext,BusinessHomeActivty.class).putExtra("storeId",bean.getStoreDataBean().getStoreId()));
                    }
                });
            }
        } else if (holder instanceof MyCollectionViewHolder) {//我的收藏
            final CollectionListBean.DataBean dataBean=bean.getCollectDataBean();
            if (dataBean!=null){
                if (dataBean.getCoach()!=null){
                    Picasso.with(context).load(dataBean.getCoverUrl()).transform(raduisTransformation).into(((MyCollectionViewHolder) holder).iv_collection_img);
                }
                ((MyCollectionViewHolder) holder).tv_collection_name.setText(dataBean.getName());

                if(dataBean.getPrice()!=0){
                    ((MyCollectionViewHolder) holder).tv_price.setText(XStringPars.foramtPrice(dataBean.getPrice())+"$/"+course_price);
                }else{
                    if (dataBean.getType()!=null){
                        if (dataBean.getType().equalsIgnoreCase("series")){
                            ((MyCollectionViewHolder) holder).tv_price.setText(context.getResources().getString(R.string.free_trail));
                        }else if(dataBean.getType().equalsIgnoreCase("single")){
                            ((MyCollectionViewHolder) holder).tv_price.setText(context.getResources().getString(R.string.free_trail_booking));
                        }
                    }
                }
                int collectCount= Integer.parseInt(dataBean.getCollectCount());
                // 创建一个数值格式化对象
                NumberFormat numberFormat = NumberFormat.getInstance();
                // 设置精确到小数点后2位
                numberFormat.setMaximumFractionDigits(2);
                int totalCount=dataBean.getUpperLimit();
                float percent= (float)dataBean.getClassCount() / (float)totalCount*100;
                ((MyCollectionViewHolder) holder).horizontal_progressbar.setProgress((int) percent);
                ((MyCollectionViewHolder) holder).tv_percent.setText(dataBean.getClassCount()+"/"+totalCount);
                ((MyCollectionViewHolder) holder).tv_collection_count.setText(collectCount+"  people");
                ((MyCollectionViewHolder) holder).tv_collection_count_txt.setText(collectCount+"人收藏");
                if (dataBean.isHasCollect()){
                    ((MyCollectionViewHolder) holder).iv_collection_course.setImageResource(R.mipmap.iv_course_item_collected);
                }else{
                    ((MyCollectionViewHolder) holder).iv_collection_course.setImageResource(R.mipmap.iv_course_item_uncollect);
                }
                ((MyCollectionViewHolder) holder).iv_collection_course.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EventBus.getDefault().post(new BusEntity(001,position+"",dataBean));
                    }
                });
                if (dataBean.getRecommends()!=null&&dataBean.getRecommends().size()>0){
                    if (dataBean.getRecommends().get(0).getValue().equalsIgnoreCase("HOT")){
                        ((MyCollectionViewHolder) holder).rl_collect.setBackgroundResource(R.mipmap.iv_collection_bg);
                    }
                    else if (dataBean.getRecommends().get(0).getValue().equalsIgnoreCase("EXCELLENT")){
                        ((MyCollectionViewHolder) holder).rl_collect.setBackgroundResource(R.mipmap.iv_collection_blue);
                    }
                    else if (dataBean.getRecommends().get(0).getValue().equalsIgnoreCase("OFFCIAL")){
                        ((MyCollectionViewHolder) holder).rl_collect.setBackgroundResource(R.mipmap.iv_collection_ping);
                    }
                }

            }
        }
    }



}
