package com.rd.lottery;

import com.android.baseline.AppDroid;

import cn.jpush.android.api.JPushInterface;

/**
 * @author liuteng
 * @version [2017/6/15 14:39]
 */

public class MyDroid extends AppDroid {
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
