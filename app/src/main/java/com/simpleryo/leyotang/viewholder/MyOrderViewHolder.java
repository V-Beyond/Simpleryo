package com.simpleryo.leyotang.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.simpleryo.leyotang.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by 77429 on 2018/3/25.
 */

public class MyOrderViewHolder extends SuperViewHolder {
    @ViewInject(R.id.iv_order_img)
  public  ImageView iv_order_img;
    @ViewInject(R.id.tv_order_name)
    public    TextView tv_order_name;
    @ViewInject(R.id.tv_order_remark)
    public TextView tv_order_remark;
    @ViewInject(R.id.tv_detail)
    public TextView tv_detail;
    @ViewInject(R.id.tv_count)
    public  TextView tv_count;
    @ViewInject(R.id.tv_price)
    public  TextView tv_price;
    @ViewInject(R.id.tv_state)
    public  TextView tv_state;
    @ViewInject(R.id.tv_time)
    public TextView tv_time;
    @ViewInject(R.id.iv_delete)
    public ImageView iv_delete;
    @ViewInject(R.id.ll_order_item)
    public LinearLayout ll_order_item;
    public MyOrderViewHolder(View itemView) {
        super(itemView);
        x.view().inject(this,itemView);
    }
}
