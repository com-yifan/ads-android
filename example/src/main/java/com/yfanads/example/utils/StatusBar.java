package com.yfanads.example.utils;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.Window;
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(color);
            }
        }
    }

    public static int getStatusBarHeight(Activity activity) {
        int result = 0;
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");

        if (resourceId > 0) {
            result = activity.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
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
