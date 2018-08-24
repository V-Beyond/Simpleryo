package com.simpleryo.leyotang.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.simpleryo.leyotang.R;
import com.zhy.autolayout.utils.AutoUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by 77429 on 2018/3/25.
 */

public class CouponsItemViewHolder extends SuperViewHolder {
    @ViewInject(R.id.iv_coupons_img)
    public ImageView iv_coupons_img;
    @ViewInject(R.id.tv_store_name)
    public TextView tv_store_name;
    @ViewInject(R.id.tv_coupon_name)
    public TextView tv_coupon_name;
    @ViewInject(R.id.tv_limit)
    public TextView tv_limit;
    @ViewInject(R.id.tv_count)
    public TextView tv_count;
    @ViewInject(R.id.tv_time)
    public TextView tv_time;
    @ViewInject(R.id.tv_to_use)
    public TextView tv_to_use;
    @ViewInject(R.id.tv_content)
    public  TextView tv_content;
    @ViewInject(R.id.rl_item)
    public RelativeLayout rl_item;
    @ViewInject(R.id.tv_dolloar)
    public  TextView tv_dolloar;
    public CouponsItemViewHolder(View itemView) {
        super(itemView);
        x.view().inject(this, itemView);
        AutoUtils.auto(itemView);
    }
}
