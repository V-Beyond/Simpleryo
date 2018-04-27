package com.simpleryo.leyotang.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simpleryo.leyotang.utils.SharedPreferencesUtils;

import org.xutils.common.Callback;

/**
 * @ClassNname：XLibraryLazyFragment.java
 * @Describe Fragement 懒加载
 * @author huanglei
 * @time 2018/3/19 11:01
 */
public abstract class XLibraryLazyFragment extends Fragment {
    /**
     * 得到根Fragment
     *
     * @return
     */
    public Fragment getRootFragment() {
        Fragment fragment = getParentFragment();
        while (fragment != null & fragment.getParentFragment() != null) {
            fragment = fragment.getParentFragment();
        }
        return fragment;

    }

    public Callback.Cancelable cancelable;

    /**
     * Fragment当前状态是否可见
     */
    protected boolean isVisible;

    // /** 标志位，标志已经初始化完成 */
     protected boolean isPrepared;
    // /** 是否已被加载过一次，第二次就不再去请求数据了 */
     protected boolean mHasLoadedOnce;

    public View mMainView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    public boolean isLogin;
    /**
     * 可见
     */
    protected void onVisible() {
        isLogin = SharedPreferencesUtils.getKeyBoolean("isLogin");//获取用户登录状态
        lazyLoad();
    }

    /**
     * 不可见
     */
    protected void onInvisible() {
        if (cancelable != null) {// 当不可见时，取消网路的访问
            cancelable.cancel();
        }
    }

    /**
     * 延迟加载 子类必须重写此方法
     */
    protected abstract void lazyLoad();

}