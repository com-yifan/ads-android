package com.fc.example.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import java.lang.reflect.Field;

/**
 * App的工具类.
 *
 * @author JamesQian
 * @copyright 亿帆
 * @date 2023/9/11 9:14
 * @version 1.0
 **/
public final class AppUtils {
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
     * 初始化工具类.
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        AppUtils.context = context;
    }

    /**
     * 获取ApplicationContext.
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) {
            return context;
        }
        throw new NullPointerException("u should init first");
    }

    /**
     * 防止按钮过快点击.
     *
     * @author JamesQian
     * @date 2023/9/11 9:15
     * @return boolean
     **/
    public static boolean isFastClick() {
        boolean flag = true;
        long currentClickTime = System.currentTimeMillis();
        if ((currentClickTime - lastClickTime) >= MIN_DELAY_TIME) {
            flag = false;
        }
        lastClickTime = currentClickTime;
        return flag;
    }

    /**
     * 获取配置信息.
     *
     * @author JamesQian
     * @date 2023/9/11 9:15
     * @param fieldName fieldName
     * @return java.lang.Object
     **/
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

    /**
     * 获取版本号.
     *
     * @author JamesQian
     * @date 2023/9/11 9:15
     * @return int
     **/
    public static int getVersionCode() {
        int versionCode = 0;
        Object vc = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            vc = getBuildConfigValue("VERSION_CODE");
        }
        if (vc != null) {
            versionCode = (int) vc;
        }
        return versionCode;
    }

    /**
     * 获取版本名称.
     *
     * @author JamesQian
     * @date 2023/9/11 9:15
     * @return java.lang.String
     **/
    public static String getVersionName() {
        Object vn = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            vn = getBuildConfigValue("VERSION_NAME");
        }
        if (vn != null) {
            return vn.toString();
        } else {
            return "";
        }
    }

    /**
     * 一句话描述.
     *
     * @author JamesQian
     * @date 2023/9/11 9:15
     * @return java.lang.Boolean
     **/
    public static Boolean getLogDebug() {
        Object logDebug = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            logDebug = getBuildConfigValue("LOG_DEBUG");
        }
        if (logDebug != null) {
            return Boolean.parseBoolean(logDebug.toString());
        } else {
            return false;
        }
    }

    /**
     * 获取context中对应类型的资源id.
     *
     * @param type    资源类型，"layout","string","drawable","color"等
     * @param resName 资源名称
     * @return 资源ID
     */
    public static int getResId(String type, String resName) {
        return context.getResources().getIdentifier(resName, type, context.getPackageName());
    }

    /**
     * 根据context获取Activity.
     *
     * @author JamesQian
     * @date 2023/9/11 9:16
     * @param context context
     * @return android.support.v7.app.AppCompatActivity
     **/
    public static AppCompatActivity findActivityByContext(Context context) {
        while (context instanceof ContextWrapper) {
            if (context instanceof AppCompatActivity) {
                return (AppCompatActivity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    /**
     * 是否debug模式.
     *
     * @author JamesQian
     * @date 2023/9/11 9:16
     * @return boolean
     **/
    public static boolean isDebug() {
        return isDebug;
    }

    /**
     * 设置debug模式.
     *
     * @author JamesQian
     * @date 2023/9/11 9:16
     * @param isDebug isDebug
     **/
    public static void setDebug(boolean isDebug) {
        AppUtils.isDebug = isDebug;
    }
}
