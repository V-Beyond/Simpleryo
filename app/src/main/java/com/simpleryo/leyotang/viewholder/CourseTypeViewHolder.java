package com.simpleryo.leyotang.viewholder;

import android.view.View;

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

    public CourseTypeViewHolder(View itemView) {
        super(itemView);
        x.view().inject(this, itemView);
    }
}
