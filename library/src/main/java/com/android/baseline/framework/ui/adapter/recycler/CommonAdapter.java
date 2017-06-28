package com.android.baseline.framework.ui.adapter.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.baseline.framework.ui.adapter.IAdapter;
import com.android.baseline.framework.ui.adapter.ViewHolder;

import java.util.List;

/**
 * 通用RecyclerView适配器
 *
 * @author liuteng
 */
public class CommonAdapter<T> extends RecyclerView.Adapter<ViewHolder> implements IAdapter<T> {
    protected Context mContext;
    int mItemLayoutId;
    protected List<T> mData;

    public CommonAdapter(Context context, List<T> data, int itemLayoutId) {
        mContext = context;
        mData = data;
        mItemLayoutId = itemLayoutId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder commonViewHolder = new ViewHolder(mContext,
                LayoutInflater.from(mContext).inflate(mItemLayoutId, parent, false));
        onBindViewHolder(commonViewHolder,viewType);
        return commonViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public T getItem(int position) {
        if (position < 0 || position > getItemCount() - 1) {
            return null;
        }
        return mData.get(position);
    }

    @Override
    public void setDataSource(List<T> data) {
        mData = data;
    }

    @Override
    public List<T> getDataSource() {
        return mData;
    }
}
