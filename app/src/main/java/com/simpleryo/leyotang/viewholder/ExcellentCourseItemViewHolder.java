package com.simpleryo.leyotang.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.simpleryo.leyotang.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by 77429 on 2018/3/25.
 */

public class ExcellentCourseItemViewHolder extends SuperViewHolder {
    @ViewInject(R.id.iv_collection_img)
  public  ImageView iv_collection_img;
    @ViewInject(R.id.tv_collection_name)
    public  TextView tv_collection_name;
    @ViewInject(R.id.tv_price)
    public  TextView tv_price;
    @ViewInject(R.id.tv_popular)
    public  TextView tv_popular;
    @ViewInject(R.id.rl_collect)
    public RelativeLayout rl_collect;
    @ViewInject(R.id.iv_collection_star)
    public ImageView iv_collection_star;
    public ExcellentCourseItemViewHolder(View itemView) {
        super(itemView);
        x.view().inject(this,itemView);
    }
}
