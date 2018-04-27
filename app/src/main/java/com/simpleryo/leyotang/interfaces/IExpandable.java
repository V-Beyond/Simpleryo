package com.simpleryo.leyotang.interfaces;

import java.util.List;

/**
 * Created by 77429 on 2018/3/25.
 */

public interface IExpandable<T> {
    boolean isExpanded();
    void setExpanded(boolean expanded);
    List<T> getSubItems();

    /**
     * Get the level of this item. The level start from 0.
     * If you don't care about the level, just return a negative.
     */
    int getLevel();
}
