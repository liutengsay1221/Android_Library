package com.android.baseline.framework.task;

/**
 * 任务接口定义
 *
 * @author liuteng
 */
public interface ITask {
    /**
     * 执行耗时任务
     *
     * @return
     */
    Object doInBackground();
}
