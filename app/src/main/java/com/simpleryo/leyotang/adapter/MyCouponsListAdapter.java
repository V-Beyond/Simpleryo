package com.simpleryo.leyotang.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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

    public MyCouponsListAdapter(Context context, String category) {
        super(context);
        this.category = category;

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
            final MyCouponListBean.DataBean dataBean = listData.get(position);
            if (dataBean.getStatus().equalsIgnoreCase("UNUSED")) {
                ((CouponsItemViewHolder) holder).tv_to_use.setText("去使用");
                ((CouponsItemViewHolder) holder).tv_to_use.setTextColor(Color.parseColor("#FF80740E"));
                ((CouponsItemViewHolder) holder).tv_to_use.setBackgroundResource(R.drawable.shape_use_coupon);
                if (dataBean.getType().getStore() != null) {
                    if(dataBean.getType().getCourses()!=null&&dataBean.getType().getCourses().size()>0){
                        ((CouponsItemViewHolder) holder).rl_item.setBackgroundResource(R.mipmap.iv_coupon_orange_bg);
                        ((CouponsItemViewHolder) holder).tv_store_name.setText(dataBean.getType().getStore().getName());
                    }else{
                        ((CouponsItemViewHolder) holder).rl_item.setBackgroundResource(R.mipmap.iv_coupon_blue_bg);
                        ((CouponsItemViewHolder) holder).tv_store_name.setText(dataBean.getType().getStore().getName());
                    }
                } else {
                    ((CouponsItemViewHolder) holder).rl_item.setBackgroundResource(R.mipmap.iv_coupon_red_bg);
                    ((CouponsItemViewHolder) holder).tv_store_name.setText("乐友堂(LeYoTown)");
                }
                ((CouponsItemViewHolder) holder).tv_to_use.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (category.equalsIgnoreCase("my")) {
                            mContext.startActivity(new Intent(mContext, CourseFilterActivity.class).putExtra("tagId1", "").putExtra("tagId3", ""));
                        } else if (category.equalsIgnoreCase("order")) {
                            if (dataBean.getType().getCategory().equalsIgnoreCase("CASH")) {//现金券
                                EventBus.getDefault().post(new BusEntity(MYCOUPONSELECT, "CASH", dataBean.getType().getSubtractAmount() + "-" + dataBean.getId()));
                            } else if (dataBean.getType().getCategory().equalsIgnoreCase("DISCOUNT")) {//折扣券
                                EventBus.getDefault().post(new BusEntity(MYCOUPONSELECT, "DISCOUNT", dataBean.getType().getDiscount() + "-" + dataBean.getId()));
                            }
                        }
                    }
                });
            } else if (dataBean.getStatus().equalsIgnoreCase("USED")) {
                ((CouponsItemViewHolder) holder).tv_to_use.setBackgroundResource(R.drawable.shape_used_coupon);
                ((CouponsItemViewHolder) holder).rl_item.setBackgroundResource(R.mipmap.iv_coupon_gray_bg);
                ((CouponsItemViewHolder) holder).tv_to_use.setText("已使用");
                ((CouponsItemViewHolder) holder).tv_to_use.setTextColor(Color.parseColor("#FFD4D4D4"));
                ((CouponsItemViewHolder) holder).tv_to_use.setFocusable(false);
                ((CouponsItemViewHolder) holder).tv_to_use.setClickable(false);
            } else if (dataBean.getStatus().equalsIgnoreCase("EXPIRED")) {
                ((CouponsItemViewHolder) holder).tv_to_use.setBackgroundResource(R.drawable.shape_used_coupon);
                ((CouponsItemViewHolder) holder).rl_item.setBackgroundResource(R.mipmap.iv_coupon_gray_bg);
                ((CouponsItemViewHolder) holder).tv_to_use.setText("已失效");
            }
            if (dataBean.getType().getImageUri() != null) {
                Picasso.with(mContext).load(dataBean.getType().getImageUri()).transform(raduisTransformation).error(R.mipmap.iv_app_logo).into(((CouponsItemViewHolder) holder).iv_coupons_img);
            }
            if (dataBean.getType().getCategory().equalsIgnoreCase("DISCOUNT")) {
                ((CouponsItemViewHolder) holder).tv_content.setText(Double.valueOf(dataBean.getType().getDiscount()) * 0.1 + "折");
            } else if (dataBean.getType().getCategory().equalsIgnoreCase("CASH")) {
                ((CouponsItemViewHolder) holder).tv_content.setText(dataBean.getType().getSubtractAmount() * 0.01 + "");
            }
            if (dataBean.getType().getLimitAmount() > 0) {
                ((CouponsItemViewHolder) holder).tv_limit.setText("[满" + dataBean.getType().getLimitAmount() + "元可用]");
            } else {
                ((CouponsItemViewHolder) holder).tv_limit.setText("[无门槛]");
            }
            ((CouponsItemViewHolder) holder).tv_count.setText(dataBean.getType().getRetrieveCounter() + "/" + dataBean.getType().getTotalCounter() + "人已领");
            String date = XStringPars.getCouponTime(dataBean.getType().getStartTime() + "") + " - " + XStringPars.getCouponTime(dataBean.getType().getEndTime() + "");
            ((CouponsItemViewHolder) holder).tv_time.setText("有效期至:" + date);
            ((CouponsItemViewHolder) holder).tv_coupon_name.setText(dataBean.getType().getName());
        }
    }

    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }
}
