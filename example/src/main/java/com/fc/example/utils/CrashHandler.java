package com.fc.example.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;
    private static Context mContext;

    private CrashHandler() {
    }

    public static void init(Context applicationContext) {
        mContext = applicationContext;
        defaultUncaughtExceptionHandler =
                Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new CrashHandler());
    }

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        File dir = new File(mContext.getExternalCacheDir(), "crash_info");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        long l = System.currentTimeMillis();
        File file = new File(dir, l + ".txt");

        try {
            PrintWriter pw = new PrintWriter(new FileWriter(file));
            pw.println("time: " + System.currentTimeMillis());
            pw.println("thread: " + t.getName());
            pw.println(getPhoneInfo());
            e.printStackTrace(pw);
            pw.flush();
            pw.close();
        } catch (IOException | PackageManager.NameNotFoundException ioException) {
            ioException.printStackTrace();
        } finally {
            if (defaultUncaughtExceptionHandler != null) {
                defaultUncaughtExceptionHandler.uncaughtException(t, e);
            }
        }
    }

    private String getPhoneInfo() throws PackageManager.NameNotFoundException {
        PackageManager pm = mContext.getPackageManager();
        PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
        StringBuilder sb = new StringBuilder();
        //App版本
        sb.append("App Version: ");
        sb.append(pi.versionName);
        sb.append("_");
        sb.append(pi.versionCode + "\n");
        //Android版本号
        sb.append("OS Version: ");
        sb.append(Build.VERSION.RELEASE);
        sb.append("_");
        sb.append(Build.VERSION.SDK_INT + "\n");
        //手机制造商
        sb.append("Vendor: ");
        sb.append(Build.MANUFACTURER + "\n");
        //手机型号
        sb.append("Model: ");
        sb.append(Build.MODEL + "\n");
        return sb.toString();
    }

}