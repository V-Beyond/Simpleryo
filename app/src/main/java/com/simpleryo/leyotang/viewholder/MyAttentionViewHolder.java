package com.simpleryo.leyotang.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.simpleryo.leyotang.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by 77429 on 2018/3/25.
 */

public class MyAttentionViewHolder extends SuperViewHolder {
    @ViewInject(R.id.iv_attention_img)
  public  ImageView iv_attention_img;
    @ViewInject(R.id.tv_my_attentoin_name)
    public  TextView tv_my_attentoin_name;
    @ViewInject(R.id.tv_to_store_detail)
    public  TextView tv_to_store_detail;
    @ViewInject(R.id.tv_store_follow_count)
    public  TextView tv_store_follow_count;
    @ViewInject(R.id.horizontal_progressbar)
    public ProgressBar horizontal_progressbar;
    public MyAttentionViewHolder(View itemView) {
        super(itemView);
        x.view().inject(this,itemView);
    }
}
