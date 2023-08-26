package com.fc.example.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import java.lang.reflect.Field;

/**
 * Copyright：  四维智联
 *
 * @author ：JonXhnCn
 * Description：
 * History: 2021/3/25
 */
public class AppUtils {
    // 两次点击间隔不能少于1000ms
    private static final int MIN_DELAY_TIME = 600;
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private static long lastClickTime;

    private static boolean isDebug;

    private AppUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        AppUtils.context = context;
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) {
            return context;
        }
        throw new NullPointerException("u should init first");
    }

    //防止按钮过快点击
    public static boolean isFastClick() {
        boolean flag = true;
        long currentClickTime = System.currentTimeMillis();
        if ((currentClickTime - lastClickTime) >= MIN_DELAY_TIME) {
            flag = false;
        }
        lastClickTime = currentClickTime;
        return flag;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static Object getBuildConfigValue(String fieldName) {
        try {
            Class<?> clazz = Class.forName(context.getPackageName() + ".BuildConfig");
            Field field = clazz.getField(fieldName);
            return field.get(null);
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getVersionCode() {
        int versionCode = 0;
        Object vc = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            vc = getBuildConfigValue("VERSION_CODE");
        }
        if (vc != null) {
            versionCode = (int) vc;
        }
        return versionCode;
    }

    public static String getVersionName() {
        Object vn = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            vn = getBuildConfigValue("VERSION_NAME");
        }
        if (vn != null) {
            return vn.toString();
        } else {
            return "";
        }
    }

    public static Boolean getLogDebug() {
        Object logDebug = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            logDebug = getBuildConfigValue("LOG_DEBUG");
        }
        if (logDebug != null) {
            return Boolean.parseBoolean(logDebug.toString());
        } else {
            return false;
        }
    }

    /**
     * 获取context中对应类型的资源id
     *
     * @param type    资源类型，"layout","string","drawable","color"等
     * @param resName 资源名称
     * @return 资源ID
     */
    public static int getResId(String type, String resName) {
        return context.getResources().getIdentifier(resName, type, context.getPackageName());
    }

    /**
     * 根据context获取Activity
     *
     * @param context
     * @return
     */
    public static AppCompatActivity findActivityByContext(Context context) {
        while (context instanceof ContextWrapper) {
            if (context instanceof AppCompatActivity) {
                return (AppCompatActivity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    public static boolean isDebug() {
        return isDebug;
    }

    public static void setDebug(boolean isDebug) {
        AppUtils.isDebug = isDebug;
    }
}
