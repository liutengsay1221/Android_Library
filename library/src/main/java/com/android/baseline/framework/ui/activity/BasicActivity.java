package com.android.baseline.framework.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.baseline.AppDroid;
import com.android.baseline.R;
import com.android.baseline.framework.logic.InfoResult;
import com.android.baseline.framework.ui.activity.base.BaseActivity;
import com.android.baseline.framework.ui.activity.base.UIInterface;
import com.android.baseline.framework.ui.view.LoadingView;
import com.android.baseline.framework.ui.view.ToastCommom;
import com.android.baseline.util.APKUtil;
import com.android.baseline.framework.ui.view.CustomDialog;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * 基类Activity [主要提供对话框、进度条和其他有关UI才做相关的功能]
 *
 * @author liuteng
 */
public class BasicActivity extends BaseActivity implements UIInterface, TakePhoto.TakeResultListener, InvokeListener {
    private final String TAG = "BasicActivity";
    /**
     * 基类Toast
     */
    private static ToastCommom mToast;
    protected boolean isPaused;
    protected boolean mIsNeedRefresh;

    /**
     * 加载进度
     */
    private LoadingView mLoadingView;
    /**
     * 视图加载器
     */
    protected LayoutInflater mInflater;

    /**
     * 标题栏
     */
    protected View llLeft;
    protected Button leftBtn;
    protected TextView titleTxt;
    protected TextView subTitleTxt;
    protected View llRight;
    protected Button rightBtn;
    private TakePhoto takePhoto;
    private InvokeParam invokeParam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        AppDroid.getInstance().uiStateHelper.addActivity(this);
    }

    @Override
    protected void onCreateCustomToolBar(Toolbar toolbar) {
        super.onCreateCustomToolBar(toolbar);
        toolbar.removeAllViews();
        // add custom title bar here
        mInflater = LayoutInflater.from(this);

        // 通用标题栏
        View commonTitle = mInflater.inflate(R.layout.layout_common_title, toolbar);
        llLeft = commonTitle.findViewById(R.id.ll_left);
        leftBtn = (Button) commonTitle.findViewById(R.id.title_left_btn);
        titleTxt = (TextView) commonTitle.findViewById(R.id.title_txt);
        subTitleTxt = (TextView) commonTitle.findViewById(R.id.sub_title_txt);
        llRight = commonTitle.findViewById(R.id.ll_right);
        rightBtn = (Button) commonTitle.findViewById(R.id.title_right_btn);
        leftBtn.setClickable(false);
        rightBtn.setClickable(false);
        llLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected void setLeftText(@StringRes int left) {
        leftBtn.setVisibility(View.VISIBLE);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) leftBtn.getLayoutParams();
        layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT;
        layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        leftBtn.setLayoutParams(layoutParams);
        leftBtn.setText(left);
        leftBtn.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
    }

    protected void setLeftDrawable(@DrawableRes int left) {
        leftBtn.setVisibility(View.VISIBLE);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) leftBtn.getLayoutParams();
        layoutParams.width = APKUtil.dip2px(this, 20);
        layoutParams.height = APKUtil.dip2px(this, 20);
        leftBtn.setLayoutParams(layoutParams);
        leftBtn.setText(null);
        leftBtn.setBackgroundResource(left);
    }

    protected void setLeftDrawable(@DrawableRes int left, int wDp, int hDp) {
        leftBtn.setVisibility(View.VISIBLE);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) leftBtn.getLayoutParams();
        layoutParams.width = wDp;
        layoutParams.height = hDp;
        leftBtn.setLayoutParams(layoutParams);
        leftBtn.setText(null);
        leftBtn.setBackgroundResource(left);
    }

    protected void hideLeft() {
        leftBtn.setVisibility(View.INVISIBLE);
    }

    protected void setLeftListener(View.OnClickListener listener) {
        if (llLeft != null) {
            llLeft.setOnClickListener(listener);
        }
        if (leftBtn != null) {
            leftBtn.setOnClickListener(listener);
        }
    }

    protected void setLeftFinish(final View.OnClickListener listener) {
        setLeftListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(v);
                }
                finish();
            }
        });
    }

    protected void setRightText(@StringRes int right) {
        rightBtn.setVisibility(View.VISIBLE);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rightBtn.getLayoutParams();
        layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT;
        layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        rightBtn.setLayoutParams(layoutParams);
        rightBtn.setText(right);
        rightBtn.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
    }

    protected void setRightDrawable(@DrawableRes int right) {
        rightBtn.setVisibility(View.VISIBLE);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rightBtn.getLayoutParams();
        layoutParams.width = APKUtil.dip2px(this, 20);
        layoutParams.height = APKUtil.dip2px(this, 20);
        rightBtn.setLayoutParams(layoutParams);
        rightBtn.setText(null);
        rightBtn.setBackgroundResource(right);
    }

    protected void setRightDrawable(@DrawableRes int right, int wDp, int hDp) {
        rightBtn.setVisibility(View.VISIBLE);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rightBtn.getLayoutParams();
        layoutParams.width = wDp;
        layoutParams.height = hDp;
        rightBtn.setLayoutParams(layoutParams);
        rightBtn.setText(null);
        rightBtn.setBackgroundResource(right);
    }

    protected void hideRight() {
        rightBtn.setVisibility(View.INVISIBLE);
    }

    protected void setRightListener(View.OnClickListener listener) {
        if (llRight != null) {
            llRight.setOnClickListener(listener);
        }
        if (rightBtn != null) {
            rightBtn.setOnClickListener(listener);
        }
    }

    protected void setTitleText(@StringRes int title) {
        titleTxt.setText(title);
    }

    protected void setSubTitleText(@StringRes int subTitle) {
        subTitleTxt.setVisibility(View.VISIBLE);
        subTitleTxt.setText(subTitle);
    }

    protected void hideSubTitle() {
        subTitleTxt.setVisibility(View.GONE);
    }

    /**
     * 设置标题栏属性
     *
     * @param leftVisible  左侧按钮是否可见
     * @param resId        标题资源id
     * @param rightVisible 右侧按钮是否可见
     */
    protected void setTitleBar(boolean leftVisible, @StringRes int resId, boolean rightVisible) {
        leftBtn.setVisibility(leftVisible ? View.VISIBLE : View.INVISIBLE);
        titleTxt.setText(resId);
        rightBtn.setVisibility(rightVisible ? View.VISIBLE : View.INVISIBLE);
    }

    /**
     * 设置标题栏属性
     *
     * @param leftVisible  左侧按钮是否可见
     * @param title        标题
     * @param rightVisible 右侧按钮是否可见
     */
    protected void setTitleBar(boolean leftVisible, String title, boolean rightVisible) {
        leftBtn.setVisibility(leftVisible ? View.VISIBLE : View.INVISIBLE);
        titleTxt.setText(title);
        rightBtn.setVisibility(rightVisible ? View.VISIBLE : View.INVISIBLE);
    }

    protected void afterSetContentView() {
        mLoadingView = (LoadingView) findViewById(R.id.loading_view);
        if (mLoadingView != null) {
            mLoadingView.register(this);
        }
    }

    /**
     * 正在加载
     */
    protected void onLoading() {
        onLoading(R.string.app_name);
    }

    /**
     * 正在加载
     *
     * @param obj
     */
    protected void onLoading(Object obj) {
        onLoading(R.string.app_name, obj);
    }

    /**
     * 正在加载
     *
     * @param stringId 描述信息
     */
    protected void onLoading(int stringId) {
        onLoading(getResources().getString(stringId));
    }

    /**
     * 正在加载
     *
     * @param stringId 描述信息
     * @param obj
     */
    public void onLoading(int stringId, Object obj) {
        onLoading(getResources().getString(stringId), obj);
    }

    /**
     * 正在加载
     *
     * @param loadDesc 描述信息
     */
    protected void onLoading(String loadDesc) {
        mLoadingView.onLoading(loadDesc, null);
    }

    /**
     * 正在加载
     *
     * @param loadDesc 描述信息
     * @param obj      传递的参数
     */
    public void onLoading(String loadDesc, Object obj) {
        mLoadingView.onLoading(loadDesc, obj);
    }

    /**
     * 失败
     */
    protected void onFailure() {
        onFailure(R.string.loading_failure);
    }

    /**
     * 失败
     *
     * @param stringId 描述信息
     */
    protected void onFailure(int stringId) {
        onFailure(getResources().getString(stringId));
    }

    /**
     * 失败
     *
     * @param errorDesc 描述信息
     */
    protected void onFailure(String errorDesc) {
        mLoadingView.onFailure(errorDesc);
    }

    /**
     * 成功
     */
    protected void onSuccess() {
        mLoadingView.onSuccess();
    }

    /**
     * 根据字符串 show toast<BR>
     *
     * @param message 字符串
     */
    public void showToast(String message) {
        if (isPaused) {
            return;
        }
        if (mToast == null) {
            mToast = ToastCommom.createToastConfig();
        }
        mToast.ToastShow(this, message);
    }


    public void showProgress(String message) {
        showProgress(message, true);
    }

    CustomDialog customDialog;
    TextView tipTextView;
    AVLoadingIndicatorView loadingIndicatorView;

    public void showProgress(String message, boolean cancelable) {
        if (customDialog == null) {
            customDialog = new CustomDialog(this).setContentView(R.layout.dialog_loading)
                    .setCancelable(cancelable)
                    .setCanceledOnTouchOutside(false)
                    .create();
        } else {
            customDialog.dismiss();
        }
        customDialog.getDialog().setCancelable(cancelable);
        customDialog.show();
        loadingIndicatorView = (AVLoadingIndicatorView) customDialog.findViewById(R.id.avi);
        loadingIndicatorView.show();
        tipTextView = (TextView) customDialog.findViewById(R.id.tipTextView);
        if (!TextUtils.isEmpty(message)) {
            tipTextView.setText(message);
        } else {
            tipTextView.setText("数据加载中...");
        }
    }

    public void hideProgress() {
        if (customDialog != null) {
            customDialog.dismiss();
            loadingIndicatorView.hide();
        }
    }

    protected boolean checkResponse(Message msg) {
        return checkResponse(msg, null, null, true);
    }

    protected boolean checkResponse(Message msg, boolean tipError) {
        return checkResponse(msg, null, null, tipError);
    }

    protected boolean checkResponse(Message msg, String errorTip) {
        return checkResponse(msg, null, errorTip, true);
    }

    protected boolean checkResponse(Message msg, String successTip, String errorTip) {
        return checkResponse(msg, successTip, errorTip, true);
    }

    /**
     * 校验服务器响应结果
     *
     * @param msg
     * @param successTip 成功提示
     * @param errorTip   失败提示    为空使用服务器信息或本地固定信息
     * @param tipError   是否提示错误信息
     * @return true 业务成功, false业务失败
     */
    protected boolean checkResponse(Message msg, String successTip, String errorTip, boolean tipError) {
        if (msg.obj instanceof InfoResult) {
            InfoResult result = (InfoResult) msg.obj;
            if (result.isSuccess()) {
                if (!TextUtils.isEmpty(successTip)) {
                    showToast(successTip);
                }
                if (result.getCode().equals("201")) {
                    showToast("暂无数据");
                }
                if (result.getCode().equals("202")) {
                    showToast("没有更多数据");
                }
                return true;
            } else {
                if (tipError) {
                    if (!TextUtils.isEmpty(errorTip)) {
                        showToast(errorTip);
                    } else if (!TextUtils.isEmpty(result.getDesc())) {
                        showToast(result.getDesc());
                    } else {
                        showToast(getString(R.string.requesting_failure));
                    }
                }
                return false;
            }
        } else {
            if (tipError) {
                if (!TextUtils.isEmpty(errorTip)) {
                    showToast(errorTip);
                } else {
                    showToast(getString(R.string.requesting_failure));
                }
            }
            return false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isPaused = false;
        if (mIsNeedRefresh) {
            mIsNeedRefresh = false;
        }
    }

    /**
     * 设置重新启动后需要刷新
     *
     * @param isNeedRefresh
     */
    protected void setNeedRefresh(boolean isNeedRefresh) {
        mIsNeedRefresh = isNeedRefresh;
    }

    /**
     * 此处重写默认情况下关闭loading dialog [子类希望改变此行为, 可以调用defaultHideDialog(false), 详见
     * {@link #defaultDialogHidden(boolean)}]
     *
     * @param msg
     * @see BaseActivity#onResponse(android.os.Message)
     */
    public void onResponse(Message msg) {
        if (dialogHidden) {
            hideProgress();
        }
    }

    boolean dialogHidden = true;

    /**
     * 设置网络请求结束是否关闭对话框, 默认是关闭
     *
     * @param hidden true关闭 false不关闭
     */
    protected void defaultDialogHidden(boolean hidden) {
        dialogHidden = hidden;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isPaused = true;
        /**
         * 这里进行一些输入法的隐藏操作
         */
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (null != imm && imm.isActive()) {
            if (null != this.getCurrentFocus() && null != this.getCurrentFocus().getWindowToken()) {
                imm.hideSoftInputFromWindow(this.getCurrentFocus().getApplicationWindowToken(),
                        0);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideProgress();
        AppDroid.getInstance().uiStateHelper.removeActivity(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }

    /**
     * 获取TakePhoto实例
     *
     * @return
     */
    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }

    @Override
    public void takeSuccess(TResult result) {
        Log.i(TAG, "takeSuccess：" + result.getImage().getCompressPath());
    }

    @Override
    public void takeFail(TResult result, String msg) {
        Log.i(TAG, "takeFail:" + msg);
    }

    @Override
    public void takeCancel() {
        Log.i(TAG, getResources().getString(com.jph.takephoto.R.string.msg_operation_canceled));
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
