package com.android.baseline.framework.ui.adapter;

import android.support.annotation.LayoutRes;

/**
 * 多样式支持
 *
 * @author liuteng
 */
public abstract class MultiTypeSupport<T> {
    /**
     * 根据itemType返回不同布局
     *
     * @param itemType
     * @return
     */
    public abstract int getLayoutId(int itemType);

    /**
     * 返回不同itemType
     *
     * @param item
     * @param position
     * @return
     */
    public abstract int getItemViewType(T item, int position);

    /**
     * 一共有多少种样式(ListView BaseAdapter需要使用)
     *
     * @return
     */
    public abstract int getViewTypeCount();
}
