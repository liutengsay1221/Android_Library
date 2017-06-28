package com.android.baseline.framework.ui.activity.base;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.android.baseline.R;
import com.android.baseline.framework.logic.EventLogic;
import com.android.baseline.framework.task.Task;
import com.android.baseline.framework.ui.activity.annotations.ViewUtils;
import com.android.baseline.framework.ui.activity.base.helper.LogicHelper;
import com.android.baseline.framework.ui.activity.base.helper.TaskHelper;
import com.android.baseline.framework.ui.activity.base.helper.ToolBarHelper;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 基类Activity, 提供业务逻辑的处理和深层的UI处理
 *
 * @author liuteng
 */
public abstract class BaseActivity extends AppCompatActivity {
    public Toolbar toolbar;
    private boolean isDestroyed; // Activity是否已销毁
    private ToolBarHelper mToolBarHelper;
    private int isVisible;

    @Override
    public void setContentView(int layoutResID) {
        mToolBarHelper = new ToolBarHelper(this, layoutResID, isToolBarVisible(), getToolBarColor(), isFitsSystemWindows());
        toolbar = mToolBarHelper.getToolBar();
        super.setContentView(mToolBarHelper.getContentView());
        /*把 toolbar 设置到Activity 中*/
        setSupportActionBar(toolbar);
        /*自定义的一些操作*/
        onCreateCustomToolBar(toolbar);
        /*View注解*/
        ViewUtils.inject(this);
        afterSetContentView();
    }


    /**
     * ToolBar颜色
     *
     * @return
     */
    protected int getToolBarColor() {
        return getResources().getColor(R.color.primary);
    }

    /**
     * ToolBar隐藏与显示
     *
     * @return
     */
    protected boolean isToolBarVisible() {
        return true;
    }

    /**
     * ToolBar隐藏与显示
     *
     * @return
     */
    protected boolean isFitsSystemWindows() {
        return true;
    }

    /**
     * ToolBar自定义
     *
     * @param toolbar
     */
    protected void onCreateCustomToolBar(Toolbar toolbar) {
        toolbar.setContentInsetsRelative(0, 0);
    }

    /**
     * 初始化一些View
     */
    protected void afterSetContentView() {

    }

    LogicHelper logicHelper = new LogicHelper();
    TaskHelper taskHelper = new TaskHelper();

    /**
     * 注册BaseLogic, Activity销毁时是自动取消解绑
     *
     * @param logic
     * @param <T>
     * @return
     */
    protected <T extends EventLogic> T registLogic(EventLogic logic) {
        return logicHelper.registLogic(logic);
    }

    /**
     * 注册Task, Activity销毁时是自动取消解绑
     *
     * @param task
     * @param <T>
     * @return
     */
    public <T extends Task> T registTask(Task task) {
        return taskHelper.registTask(task);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        isDestroyed = true;
        logicHelper.unregistAll();
        taskHelper.unregistAll();
    }

    /**
     * EventBus订阅者事件通知的函数, UI线程
     *
     * @param msg
     */
    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onEventMainThread(Message msg) {
        if (!isDestroyed && !isFinishing()) {
            onResponse(msg);
        }
    }

    public abstract void onResponse(Message msg);
}
