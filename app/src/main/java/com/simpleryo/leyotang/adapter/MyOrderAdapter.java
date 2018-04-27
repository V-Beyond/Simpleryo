package com.simpleryo.leyotang.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.activity.BusinessHomeActivty;
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
import com.squareup.picasso.Transformation;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by 77429 on 2018/3/25.
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
        if (viewType == MultipleItem.ORDER) {
            return new MyOrderViewHolder(mInflater.inflate(R.layout.layout_order_item, parent, false));
        } else if (viewType == MultipleItem.MYATTENTION) {
            return new MyAttentionViewHolder(mInflater.inflate(R.layout.layout_my_attention_item, parent, false));
        } else if (viewType == MultipleItem.MYCOLLECTION) {
            return new MyCollectionViewHolder(mInflater.inflate(R.layout.layout_my_collection_item, parent, false));
        } else {//所有都没有的情况
            return new EmptyViewHolder(mInflater.inflate(R.layout.layout_empty_item, parent, false));
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCollect(BusEntity bus) {

    }

    public Transformation transformation = new RoundedTransformationBuilder()
            .cornerRadius(20)
            .oval(false)
            .build();

    @Override
    public void onBindItemHolder(SuperViewHolder holder, final int position) {
        final MultipleItem bean;
        bean = mDataList.get(position);
        if (holder instanceof MyOrderViewHolder) {//我的订单
            final OrderListBean.DataBean dataBean = bean.getOrderListBean();
            if (dataBean.getImageUrl()!=null){
                Picasso.with(context).load("http://p3.so.qhimgs1.com/bdr/_240_/t01144f848052b04663.jpg").transform(transformation).into(((MyOrderViewHolder) holder).iv_order_img);
            }else{
                Picasso.with(mContext).load("http://p3.so.qhimgs1.com/bdr/_240_/t01144f848052b04663.jpg").transform(transformation).into(((MyOrderViewHolder) holder).iv_order_img);
            }
            ((MyOrderViewHolder) holder).tv_order_name.setText(dataBean.getCourseName());
            ((MyOrderViewHolder) holder).tv_time.setText(XStringPars.getStrTime(dataBean.getCreationTime()));
            ((MyOrderViewHolder) holder).tv_price.setText(dataBean.getUnitPrice()+"$");
            ((MyOrderViewHolder) holder).tv_count.setText("x"+dataBean.getQuantity());
            if (dataBean.getStatus().equalsIgnoreCase("COMPLETED")) {
                ((MyOrderViewHolder) holder).tv_state.setText("交易成功");
                ((MyOrderViewHolder) holder).tv_detail.setVisibility(View.GONE);
                ((MyOrderViewHolder) holder).tv_order_remark.setText("查看评价");
            } else if (dataBean.getStatus().equalsIgnoreCase("NEW")) {
                ((MyOrderViewHolder) holder).tv_state.setText("代付款");
                ((MyOrderViewHolder) holder).tv_detail.setText("去支付");
                ((MyOrderViewHolder) holder).tv_order_remark.setText("取消订单");
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
                ((MyOrderViewHolder) holder).tv_state.setText("已上课");
                ((MyOrderViewHolder) holder).tv_detail.setVisibility(View.GONE);
                ((MyOrderViewHolder) holder).tv_order_remark.setText("评价");
                ((MyOrderViewHolder) holder).tv_order_remark.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mContext.startActivity(new Intent(mContext, RemarkCourseActivity.class).putExtra("orderId", dataBean.getId()));
                    }
                });
                ((MyOrderViewHolder) holder).tv_order_remark.setBackgroundResource(R.mipmap.iv_no_remark);
            }
            else if (dataBean.getStatus().equalsIgnoreCase("CANCALLED")) {
                ((MyOrderViewHolder) holder).tv_state.setText("已取消");
                ((MyOrderViewHolder) holder).tv_order_remark.setVisibility(View.GONE);
                ((MyOrderViewHolder) holder).tv_detail.setVisibility(View.GONE);
            }
            else if (dataBean.getStatus().equalsIgnoreCase("PAYED")) {
                ((MyOrderViewHolder) holder).tv_state.setText("已支付");
                ((MyOrderViewHolder) holder).tv_order_remark.setVisibility(View.INVISIBLE);
                ((MyOrderViewHolder) holder).tv_detail.setVisibility(View.VISIBLE);
            }
        } else if (holder instanceof MyAttentionViewHolder) {//我的关注
            if (bean.getStoreDataBean()!=null){
                Picasso.with(context).load(bean.getStoreDataBean().getCoverUrl()).transform(transformation).into(((MyAttentionViewHolder) holder).iv_attention_img);
                ((MyAttentionViewHolder) holder).tv_my_attentoin_name.setText(bean.getStoreDataBean().getName());
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
                    Picasso.with(context).load(dataBean.getCoverUrl()).transform(transformation).into(((MyCollectionViewHolder) holder).iv_collection_img);
                }
                ((MyCollectionViewHolder) holder).tv_collection_name.setText(dataBean.getName());
                ((MyCollectionViewHolder) holder).tv_price.setText(dataBean.getPrice()+"$/hour");
                ((MyCollectionViewHolder) holder).tv_collection_count.setText(dataBean.getCollectCount()+"  people");
                ((MyCollectionViewHolder) holder).rl_collect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EventBus.getDefault().post(new BusEntity(001,position+"",dataBean));
                    }
                });
            }
        }
    }
}