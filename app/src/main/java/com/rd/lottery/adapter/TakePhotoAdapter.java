package com.rd.lottery.adapter;

import android.content.Context;
import android.view.View;

import com.android.baseline.framework.ui.adapter.ViewHolder;
import com.android.baseline.framework.ui.adapter.recycler.CommonAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.rd.lottery.R;
import com.rd.lottery.view.OnItemCliclkListener;

import java.util.List;

/**
 * @author liuteng
 * @version [2017/6/27 16:50]
 */

public class TakePhotoAdapter extends CommonAdapter<String> {
    private OnItemCliclkListener onItemCliclkListener;

    public TakePhotoAdapter(Context context, List<String> data, int itemLayoutId) {
        super(context, data, itemLayoutId);
    }

    public void setOnItemCliclkListener(OnItemCliclkListener onItemCliclkListener) {
        this.onItemCliclkListener = onItemCliclkListener;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        SimpleDraweeView simpleDraweeView = holder.getView(R.id.select_pic);
        if (position == 0) {
            holder.setVisible(R.id.delect_pic, false);
            simpleDraweeView.setImageURI(null);
            simpleDraweeView.setBackgroundResource(R.mipmap.re_upload_img);
        } else {
            holder.setVisible(R.id.delect_pic, true);
            simpleDraweeView.setBackgroundResource(0);
            holder.setSimpleDraweeView(R.id.select_pic, "file:" + getItem(position));
        }
        simpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemCliclkListener.onItemClick(view, position);
            }
        });
        holder.setOnClickListener(R.id.delect_pic, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemCliclkListener.onItemClick(view, position);
            }
        });
    }


}
