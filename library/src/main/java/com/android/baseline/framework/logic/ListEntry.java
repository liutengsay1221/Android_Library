package com.android.baseline.framework.logic;

import java.io.Serializable;
import java.util.List;

/**
 * 类型为集合
 * @author liuteng
 */
public class ListEntry<T> implements Serializable{
    private int count;
    private List<T> rows;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "ListEntry{" +
                "count=" + count +
                ", rows=" + rows +
                '}';
    }
}
