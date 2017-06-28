package com.android.baseline.framework.ui.adapter.extend.page;

/**
 * 分页策略1: pageIndex, pageSize
 *
 * @author liuteng
 */
public abstract class Page1 extends IPage {
    @Override
    public int handlePageIndex(int currPageIndex, int pageSize) {
        return ++currPageIndex;
    }

    @Override
    protected int handlePage(int currPageIndex, int pageSize) {
        return pageSize;
    }
}
