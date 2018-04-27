package com.simpleryo.leyotang.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.simpleryo.leyotang.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by 77429 on 2018/3/25.
 */

public class CoachItemViewHolder extends SuperViewHolder {
    @ViewInject(R.id.tv_introduce)
    public TextView tv_introduce;
    @ViewInject(R.id.tv_name)
    public  TextView tv_name;
    @ViewInject(R.id.iv_coach_img)
    public ImageView iv_coach_img;

    public CoachItemViewHolder(View itemView) {
        super(itemView);
        x.view().inject(this,itemView);
    }
}
