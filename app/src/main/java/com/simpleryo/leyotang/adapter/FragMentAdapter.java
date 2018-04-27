package com.simpleryo.leyotang.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @ClassNname：FragMentAdapter.java
 * @Describe Fragment 添加到 Viewpage中使用的
 * @author huanglei
 * @time 2018/3/19 11:02
 */
public class FragMentAdapter<T extends Fragment> extends FragmentPagerAdapter {
    private List<T> fragments;

    public FragMentAdapter(FragmentManager fm) {
        super(fm);
    }

    public FragMentAdapter(FragmentManager fm, List<T> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}