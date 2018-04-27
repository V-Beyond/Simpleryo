package com.simpleryo.leyotang.viewholder;

import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.simpleryo.leyotang.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by 77429 on 2017/11/10.
 */

public class MessageViewHolder extends SuperViewHolder {
    @ViewInject(R.id.tv_title)
    public TextView tv_title;
    @ViewInject(R.id.tv_content)
    public WebView tv_content;
    public MessageViewHolder(View itemView) {
        super(itemView);
        x.view().inject(this, itemView);
    }
}
