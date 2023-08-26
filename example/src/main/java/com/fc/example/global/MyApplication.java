package com.fc.example.global;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.WebView;

import com.fc.ads.FCAdsManager;
import com.fc.example.utils.AppUtils;
import com.hjq.toast.ToastUtils;

/**
 * Copyright: 风船科技
 * Author: JonXhnChn
 * Description:
 * History: 2023/7/17
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppUtils.init(this);
        ToastUtils.init(this);

        // 一般是在主进程中初始化SDK，避免在其它不展示广告的进程中初始化SDK，浪费资源
        if (TextUtils.equals(this.getPackageName(), getProcessName(this))) {
            initSDK();
        }
    }

    private void initSDK() {
        FCAdsManager.getInstance().init(this, GlobalConst.APP_ID);
        // 测试用的
        FCAdsManager.getInstance().openDebug();
    }

    private String getProcessName(Context context) {
        if (context == null) return null;
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo processInfo : manager.getRunningAppProcesses()) {
            if (processInfo.pid == android.os.Process.myPid()) {
                return processInfo.processName;
            }
        }
        return null;
    }

    /**
     * 在Android 9开始不允许同一个应用的多个进程中使用WebView，否则会出现Crash。
     * 通过改方法规避。
     */
    private void fixWebView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            String processName = getProcessName(this);
            String packageName = this.getPackageName();
            String suffix = packageName + android.os.Process.myPid();
            if (!TextUtils.isEmpty(processName)) {
                suffix = processName;
            }
            if (!packageName.equals(processName)) {
                try {
                    WebView.setDataDirectorySuffix(suffix);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    WebView.setDataDirectorySuffix(packageName);
                } catch (Exception unused) {
                }
            }
        }
    }

}
