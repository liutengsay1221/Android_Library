package com.android.baseline.framework.ui.activity.base;

/**
 * UI接口定义
 *
 * @author liuteng
 */
public interface UIInterface {
    /**
     * 显示Toast
     *
     * @param message
     */
    void showToast(String message);

    /**
     * 显示进度条, 可取消
     *
     * @param message
     */
    void showProgress(String message);

    /**
     * 显示并设置进度条是否可取消
     *
     * @param message
     * @param cancelable
     */
    void showProgress(String message, boolean cancelable);

    /**
     * 隐藏进度条
     */
    void hideProgress();
}
