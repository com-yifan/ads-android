package com.yfanads.example.global;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.WebView;

import com.hjq.toast.ToastUtils;
import com.yfanads.android.YFAdsConfig;
import com.yfanads.android.YFAdsManager;
import com.yfanads.android.libs.net.UrlConst;
import com.yfanads.android.model.YFLogLevel;
import com.yfanads.example.utils.AppUtils;
import com.yfanads.example.utils.CrashHandler;

/**
 * 全局Application.
 *
 * @author JamesQian
 * @version 1.0
 * @copyright 亿帆
 * @date 2023/9/9 17:25
 **/
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.init(this);
        AppUtils.init(this);
        ToastUtils.init(this);

        // 一般是在主进程中初始化SDK，避免在其它不展示广告的进程中初始化SDK，浪费资源
        if (TextUtils.equals(this.getPackageName(), getProcessName(this))) {
            initSDK();
        }
    }

    private void initSDK() {
        YFAdsConfig fcAdsConfig =
                new YFAdsConfig.YFAdsConfigBuilder(GlobalConst.APP_ID, "v1.0.1", "岁友社区",
                        "com.fc.example")
                        .setDebug(true).builder();
        fcAdsConfig.setYFLogLevel(YFLogLevel.HIGH);
        YFAdsManager.getInstance().init(this, fcAdsConfig);
    }

    private String getProcessName(Context context) {
        if (context == null) {
            return null;
        }
        ActivityManager manager =
                (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo processInfo : manager.getRunningAppProcesses()) {
            if (processInfo.pid == android.os.Process.myPid()) {
                return processInfo.processName;
            }
        }
        return null;
    }

    /**
     * 在Android 9开始不允许同一个应用的多个进程中使用WebView，否则会出现Crash.
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
