package com.android.baseline.framework.ui.adapter.extend;

import android.content.Context;

import com.android.baseline.framework.ui.adapter.BasicAdapter;
import com.android.baseline.framework.ui.adapter.MultiTypeSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * 单选适配器
 * 注意：选中与取消选中的泛型T必须是同一个对象或者重写equals方法
 *
 * @author liuteng
 */
public abstract class SingleChoiceAdapter<T> extends BasicAdapter<T> {
    final protected List<T> selectedItems = new ArrayList<>();

    public SingleChoiceAdapter(Context context, List<T> data, int resourceId) {
        super(context, data, resourceId);
    }

    public SingleChoiceAdapter(Context context, List<T> data, MultiTypeSupport<T> multiTypeSupport) {
        super(context, data, multiTypeSupport);
    }

    /**
     * 选中某一项
     *
     * @param position
     */
    public void selectItem(int position) {
        selectItem(getItem(position));
    }

    /**
     * 选中某一项
     *
     * @param choice
     */
    public void selectItem(T choice) {
        // 保证集合里面只有一个元素
        selectedItems.clear();
        if (choice != null) {
            selectedItems.add(choice);
            notifyDataSetChanged();
        }
    }

    /**
     * 取消选中某一项
     *
     * @param position
     */
    public void disselectItem(int position) {
        disselectItem(getItem(position));
    }

    /**
     * 取消选中某一项
     *
     * @param choice
     */
    public void disselectItem(T choice) {
        if (choice != null && selectedItems.contains(choice)) {
            selectedItems.remove(choice);
            notifyDataSetChanged();
        }
    }

    /**
     * 是否选中某一项
     *
     * @param position
     * @return
     */
    public boolean isItemSelected(int position) {
        return isItemSelected(getItem(position));
    }

    /**
     * 是否选中某一项
     *
     * @param choice
     * @return
     */
    public boolean isItemSelected(T choice) {
        return selectedItems.contains(choice);
    }

    /**
     * 返回所有选中的项目
     *
     * @return
     */
    public T getSelectedItem() {
        return selectedItems.size() > 0 ? selectedItems.get(0) : null;
    }
}
