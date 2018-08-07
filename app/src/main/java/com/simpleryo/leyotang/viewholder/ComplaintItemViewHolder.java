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

public class ComplaintItemViewHolder extends SuperViewHolder {
    @ViewInject(R.id.iv_coach_img)
    public ImageView iv_coach_img;
    @ViewInject(R.id.tv_time)
    public TextView tv_time;
    @ViewInject(R.id.tv_content)
    public  TextView tv_content;
    public ComplaintItemViewHolder(View itemView) {
        super(itemView);
        x.view().inject(this,itemView);
    }
}
