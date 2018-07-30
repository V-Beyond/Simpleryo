package com.simpleryo.leyotang.viewholder;

import android.view.View;
import android.widget.ImageView;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.simpleryo.leyotang.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by 77429 on 2017/11/10.
 */

public class CourseTypeViewHolder extends SuperViewHolder {
    @ViewInject(R.id.lrecyclerview)
    public LRecyclerView lrecyclerview;
    @ViewInject(R.id.iv_coupons_img)
    public ImageView iv_coupons_img;

    public CourseTypeViewHolder(View itemView) {
        super(itemView);
        x.view().inject(this, itemView);
    }
}
