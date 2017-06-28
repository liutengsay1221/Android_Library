package com.android.baseline.framework.logic.net;

/**
 * 文件下载进度
 *
 * @author liuteng
 */
public interface IProgress {
    /**
     * 进度
     *
     * @param current 已下载
     * @param total   总共大小
     */
    void onProgress(long current, long total);
}
