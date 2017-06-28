package com.android.baseline.framework.ui.adapter;

import java.util.List;

/**
 * Adapter Interface
 *
 * @author liuteng
 */
public interface IAdapter<T> {

    void setDataSource(List<T> data);

    List<T> getDataSource();

    T getItem(int position);
}
