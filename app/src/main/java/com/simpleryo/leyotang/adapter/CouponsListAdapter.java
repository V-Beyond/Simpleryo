package com.simpleryo.leyotang.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.bean.CouponsListBean;
import com.simpleryo.leyotang.utils.XStringPars;
import com.simpleryo.leyotang.viewholder.CouponsItemViewHolder;
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

public class CouponsListAdapter extends BaseAdapter<CouponsListBean.DataBean> {
    public CouponsListAdapter(Context context) {
        super(context);
    }

    @Override
    public SuperViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        return new CouponsItemViewHolder(layoutInflater.inflate(R.layout.layout_coupon_item, parent, false));
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        if (holder instanceof  CouponsItemViewHolder){
                ((CouponsItemViewHolder) holder).tv_to_use.setText("立即领取");
            CouponsListBean.DataBean typeBean=listData.get(position);
            if (typeBean.getImageUri()!=null){
                Picasso.with(mContext).load(typeBean.getImageUri()).transform(raduisTransformation).into(((CouponsItemViewHolder) holder).iv_coupons_img);
            }
            if (typeBean.getStore()!=null){
                ((CouponsItemViewHolder) holder).rl_item.setBackgroundResource(R.mipmap.iv_coupon_blue_bg);
                ((CouponsItemViewHolder) holder).tv_store_name.setText(typeBean.getStore().getName());
            }else{
                ((CouponsItemViewHolder) holder).rl_item.setBackgroundResource(R.mipmap.iv_coupon_red_bg);
                ((CouponsItemViewHolder) holder).tv_store_name.setText("乐友堂(LeYoTown)");
            }
            if (typeBean.getCategory().equalsIgnoreCase("DISCOUNT")){
                ((CouponsItemViewHolder) holder).tv_content.setText(typeBean.getLimitAmount()/10+"折");
            }else if (typeBean.getCategory().equalsIgnoreCase("CASH")){
                ((CouponsItemViewHolder) holder).tv_content.setText(typeBean.getLimitAmount()+"");
            }
            if (typeBean.getLimitAmount()>0){
                ((CouponsItemViewHolder) holder).tv_limit.setText("[满"+typeBean.getLimitAmount()+"元可用]");
            }else{
                ((CouponsItemViewHolder) holder).tv_limit.setText("[无门槛]");
            }
            ((CouponsItemViewHolder) holder).tv_count.setText(typeBean.getRetrieveCounter()+"/"+typeBean.getTotalCounter()+"人已领");
            String date= XStringPars.getCouponTime(typeBean.getStartTime())+" - "+XStringPars.getCouponTime(typeBean.getEndTime());
            ((CouponsItemViewHolder) holder).tv_time.setText("有效期至:"+date);
            ((CouponsItemViewHolder) holder).tv_coupon_name.setText(typeBean.getName());
        }
    }
    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }
}
