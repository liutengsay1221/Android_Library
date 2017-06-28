package com.android.baseline.framework.ui.adapter.recycler;

import com.android.baseline.framework.ui.adapter.MultiTypeSupport;

/**
 * RecycleView多样式支持(getViewTypeCount()重写一下而已)
 *
 * @author liuteng
 */
public abstract class RMultiTypeSupport<T> extends MultiTypeSupport<T> {
    @Override
    public int getViewTypeCount() {
        return 0;
    }
}
