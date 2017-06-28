package com.rd.lottery;

import android.content.Context;

import com.android.baseline.framework.ui.adapter.MultiTypeSupport;
import com.android.baseline.framework.ui.adapter.ViewHolder;
import com.android.baseline.framework.ui.adapter.recycler.MultiTypeAdapter;

import java.util.List;

/**
 * @author liuteng
 * @version [2017/6/20 15:09]
 */

public class TextAdapter extends MultiTypeAdapter<TextDemo> {

    protected TextAdapter(Context context, List<TextDemo> data, MultiTypeSupport<TextDemo> itemSupport) {
        super(context, data, itemSupport);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (getItemViewType(position) == 1) {
            holder.setText(R.id.text1, getItem(position).getTitle());
        } else if (getItemViewType(position) == 2) {
            holder.setText(R.id.text2, getItem(position).getTitle());
        } else if (getItemViewType(position) == 3) {
            holder.setText(R.id.text3, getItem(position).getTitle());
        }
    }
}
