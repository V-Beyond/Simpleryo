package com.simpleryo.leyotang.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.activity.CourseFilterActivity;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.bean.MyCouponListBean;
import com.simpleryo.leyotang.utils.XStringPars;
import com.simpleryo.leyotang.viewholder.CouponsItemViewHolder;
import com.simpleryo.leyotang.viewholder.SuperViewHolder;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.simpleryo.leyotang.utils.EventBusType.MYCOUPONSELECT;


/**
 * @author huanglei
 * @version V1.0
 * @Title: JingXuanAdapter
 * @Package com.hpkj.kexue.adapter
 * @Description: 精选推荐item适配器
 * @date 2017/11/10 18:55
 */

public class MyCouponsListAdapter extends BaseAdapter<MyCouponListBean.DataBean> {
    String category;//判断是否为下单页面跳转过来
    public MyCouponsListAdapter(Context context,String category) {
        super(context);
        this.category=category;

    }

    @Override
    public SuperViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        return new CouponsItemViewHolder(layoutInflater.inflate(R.layout.layout_coupon_item, parent, false));
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
        if (holder instanceof CouponsItemViewHolder) {
            ((CouponsItemViewHolder) holder).tv_to_use.setText("去使用");
            final MyCouponListBean.DataBean.TypeBean typeBean = listData.get(position).getType();
            if (typeBean.getImageUri() != null) {
                Picasso.with(mContext).load(typeBean.getImageUri()).transform(raduisTransformation).into(((CouponsItemViewHolder) holder).iv_coupons_img);
            }
            if (typeBean.getStore() != null) {
                ((CouponsItemViewHolder) holder).rl_item.setBackgroundResource(R.mipmap.iv_coupon_blue_bg);
                ((CouponsItemViewHolder) holder).tv_store_name.setText(typeBean.getStore().getName());
            } else {
                ((CouponsItemViewHolder) holder).rl_item.setBackgroundResource(R.mipmap.iv_coupon_red_bg);
                ((CouponsItemViewHolder) holder).tv_store_name.setText("乐友堂(LeYoTown)");
            }
            if (typeBean.getCategory().equalsIgnoreCase("DISCOUNT")) {
                ((CouponsItemViewHolder) holder).tv_content.setText(Double.valueOf(typeBean.getDiscount())*0.1+ "折");
            } else if (typeBean.getCategory().equalsIgnoreCase("CASH")) {
                ((CouponsItemViewHolder) holder).tv_content.setText(typeBean.getSubtractAmount()*0.01 + "");
            }
            if (typeBean.getLimitAmount() > 0) {
                ((CouponsItemViewHolder) holder).tv_limit.setText("[满" + typeBean.getLimitAmount() + "元可用]");
            } else {
                ((CouponsItemViewHolder) holder).tv_limit.setText("[无门槛]");
            }
            ((CouponsItemViewHolder) holder).tv_count.setText(typeBean.getRetrieveCounter() + "/" + typeBean.getTotalCounter() + "人已领");
            String date = XStringPars.getCouponTime(typeBean.getStartTime()+"") + " - " + XStringPars.getCouponTime(typeBean.getEndTime()+"");
            ((CouponsItemViewHolder) holder).tv_time.setText("有效期至:" + date);
            ((CouponsItemViewHolder) holder).tv_coupon_name.setText(typeBean.getName());
            ((CouponsItemViewHolder) holder).tv_to_use.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (category.equalsIgnoreCase("my")){
                        mContext.startActivity(new Intent(mContext, CourseFilterActivity.class).putExtra("tagId1", "").putExtra("tagId3",""));
                    }else  if(category.equalsIgnoreCase("order")){
                        if (typeBean.getCategory().equalsIgnoreCase("CASH")) {//现金券
                            EventBus.getDefault().post(new BusEntity(MYCOUPONSELECT, "CASH", typeBean.getSubtractAmount() + "-"+typeBean.getId()));
                        } else if (typeBean.getCategory().equalsIgnoreCase("DISCOUNT")) {//折扣券
                            EventBus.getDefault().post(new BusEntity(MYCOUPONSELECT, "DISCOUNT", typeBean.getDiscount() + "-"+typeBean.getId()));
                        }
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }
}
