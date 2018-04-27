package com.simpleryo.leyotang.viewholder;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.simpleryo.leyotang.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by 77429 on 2017/11/10.
 */

public class HotCourseViewHolder extends SuperViewHolder {
    @ViewInject(R.id.lrecyclerview)
    public LRecyclerView lrecyclerview;
    @ViewInject(R.id.item_more_rihgt_more)
    public  RelativeLayout item_more_rihgt_more;
    @ViewInject(R.id.empty_view)
    public  View empty_view;
    @ViewInject(R.id.textView)
    public   TextView textView;
    public HotCourseViewHolder(View itemView) {
        super(itemView);
        x.view().inject(this, itemView);
    }
}
