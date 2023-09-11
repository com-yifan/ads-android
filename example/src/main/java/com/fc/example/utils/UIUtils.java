package com.fc.example.utils;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by bytedance on 2019/9/5.
 */

public final class UIUtils {

    private UIUtils() {
    }

    /**
     * 获取全面屏宽高.
     *
     * @author JamesQian
     * @date 2023/9/11 9:40
     * @param context context
     * @return int[]
     **/
    public static int[] getScreenSize(Context context) {
        int[] size = new int[]{0, 0};
        if (context == null) {
            return size;
        }
        WindowManager windowManager =
                (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            display.getRealMetrics(dm);
        } else {
            display.getMetrics(dm);
        }
        size[0] = dm.widthPixels;
        size[1] = dm.heightPixels;
        return size;
    }
}
