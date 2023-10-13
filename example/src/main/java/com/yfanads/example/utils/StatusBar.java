package com.yfanads.example.utils;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;

/**
 * 状态栏工具.
 *
 * @author JamesQian
 * @copyright 亿帆
 * @date 2023/9/11 9:22
 * @version 1.0
 **/
public final class StatusBar {

    private StatusBar() {
    }

    /**
     * 将状态栏设置为传入的color.
     *
     * @author JamesQian
     * @date 2023/9/11 9:22
     * @param activity activity
     * @param color color
     **/
    public static void setColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= 21) {
            View view = activity.getWindow().getDecorView();
            view.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            activity.getWindow().setStatusBarColor(activity.getResources().getColor(color));
        }
    }

    /**
     * 隐藏状态栏.
     *
     * @author JamesQian
     * @date 2023/9/11 9:22
     * @param activity activity
     **/
    public static void hide(Activity activity) {
        if (Build.VERSION.SDK_INT >= 21) {
            activity.getWindow()
                    .setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    /**
     * 设置状态栏字体颜色.
     *
     * @author JamesQian
     * @date 2023/9/11 9:22
     * @param activity activity
     * @param isDarkBackground isDarkBackground
     **/
    public static void setTextColor(Activity activity, boolean isDarkBackground) {
        View decor = activity.getWindow().getDecorView();
        if (isDarkBackground) {
            //黑暗背景字体浅色
            decor.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        } else {
            //高亮背景字体深色
            decor.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
}
