package com.simpleryo.leyotang.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.activity.BusinessHomeActivty;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.bean.StoreListBean;
import com.simpleryo.leyotang.viewholder.MyAttentionViewHolder;
import com.simpleryo.leyotang.viewholder.SuperViewHolder;
import com.squareup.picasso.Picasso;

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

public class StoreListAdapter extends BaseAdapter<StoreListBean.DataBean> {
    public StoreListAdapter(Context context) {
        super(context);
        EventBus.getDefault().register(this);
    }

    @Override
    public SuperViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        return new MyAttentionViewHolder(layoutInflater.inflate(R.layout.layout_my_attention_item, parent, false));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCollect(BusEntity bus) {

    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, final int position) {
        final StoreListBean.DataBean bean = listData.get(position);
        if (bean.getStoreInfo().getCoverUrl()!=null){
            Picasso.with(mContext).load(bean.getStoreInfo().getCoverUrl()).transform(transformation).into(((MyAttentionViewHolder) holder).iv_attention_img);
        }else{
            Picasso.with(mContext).load("http://p3.so.qhimgs1.com/bdr/_240_/t01144f848052b04663.").transform(transformation).into(((MyAttentionViewHolder) holder).iv_attention_img);
        }

        ((MyAttentionViewHolder) holder).tv_my_attentoin_name.setText(bean.getStoreInfo().getName());
        ((MyAttentionViewHolder) holder).tv_to_store_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, BusinessHomeActivty.class).putExtra("storeId", bean.getStoreInfo().getStoreId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }
}
