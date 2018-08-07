package com.simpleryo.leyotang.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.view.MultiImageView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by 77429 on 2018/3/25.
 */

public class RemarkItemViewHolder extends SuperViewHolder {
    @ViewInject(R.id.tv_name)
    public  TextView tv_name;
    @ViewInject(R.id.iv_coach_img)
    public ImageView iv_coach_img;
    @ViewInject(R.id.rating_bar)
    public RatingBar rating_bar;
    @ViewInject(R.id.tv_time)
    public TextView tv_time;
    @ViewInject(R.id.tv_content)
    public  TextView tv_content;
    @ViewInject(R.id.multi_image)
    public  MultiImageView multi_image;
    public RemarkItemViewHolder(View itemView) {
        super(itemView);
        x.view().inject(this,itemView);
    }
}
