package com.yfanads.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yfanads.android.utils.YFAdsConst;
import com.yfanads.example.R;
import com.yfanads.example.base.BaseActivity;
import com.yfanads.example.global.GlobalConst;
import com.yfanads.example.utils.AppUtils;

/**
 * 主入口.
 *
 * @author JamesQian
 * @date 2023/9/9 17:24
 **/
public class MainActivity extends BaseActivity {

    private Button debugBtn;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        debugBtn = findViewById(R.id.debug);
        debugBtn.setText(AppUtils.isDebug() ? R.string.close_debug : R.string.open_debug);
    }

    /**
     * 点击Banner.
     *
     * @param view view
     * @author JamesQian
     * @date 2023/9/9 17:24
     **/
    public void clickBanner(View view) {
        startActivity(new Intent(this, YFBannerActivity.class).putExtra("potId",
                GlobalConst.AD_ID));
    }

    /**
     * 点击debug.
     *
     * @param view view
     * @author JamesQian
     * @date 2023/9/9 17:24
     **/
    public void clickDebug(View view) {
        AppUtils.setDebug(!AppUtils.isDebug());
        YFAdsConst.isUseCache = AppUtils.isDebug();
        debugBtn.setText(AppUtils.isDebug() ? R.string.close_debug : R.string.open_debug);
    }

    /**
     * 点击开屏页面.
     *
     * @param view view
     * @author JamesQian
     * @date 2023/9/9 17:24
     **/
    public void clickSplash(View view) {
        startActivity(new Intent(this, SplashActivity.class).putExtra("potId",
                GlobalConst.SPLASH_AD_ID));
    }

    /**
     * 点击插屏广告.
     *
     * @param view view
     * @author JamesQian
     * @date 2023/9/9 17:24
     **/
    public void clickInti(View view) {
        startActivity(new Intent(this, InterstitialActivity.class).putExtra("potId",
                GlobalConst.INSERT_AD_ID));
    }

    /**
     * 点击信息流.
     *
     * @param view view
     * @author JamesQian
     * @date 2023/9/9 17:25
     **/
    public void clickDraw(View view) {
        startActivity(
                new Intent(this, DrawActivity.class).putExtra("potId", GlobalConst.DRAW_AD_ID));
    }

    /**
     * 点击全屏视频.
     *
     * @param view view
     * @author JamesQian
     * @date 2023/9/9 17:25
     **/
    public void clickFullVideo(View view) {
        startActivity(new Intent(this, FullScreenVideoActivity.class).putExtra("potId",
                GlobalConst.FULL_SCREEN_VIDEO_AD_ID));
    }

    /**
     * 点击激励视频.
     *
     * @param view view
     * @author JamesQian
     * @date 2023/9/9 17:25
     **/
    public void clickReward(View view) {
        startActivity(new Intent(this, RewardVideoActivity.class).putExtra("potId",
                GlobalConst.REWARD_AD_ID));
    }

    /**
     * 点击信息流.
     *
     * @param view view
     * @author JamesQian
     * @date 2023/9/9 17:25
     **/
    public void onNativeExpressRecyclerView(View view) {
        startActivity(
                new Intent(this, NativeExpressRecyclerViewActivity.class).putExtra("potId",
                        GlobalConst.NATIVE_PRESS_ID));
    }

//    private void startAndroidINFO() {
//        String text = "获取设备手机制造商：" + getManufacturer() + "\r\n";
//        text += "获取设备AndroidID：" + getAndroidID() + "\r\n";
//        text += "获取设备型号：" + getModel() + "\r\n";
//        text += "获取唯一设备 ID：" + getUdid() + "\r\n";
//        text += "获取设备 AppVersionCode：" + getVersionCode() + "\r\n";
//        text += "获取唯一设备 AppVersionName：" + getVerName() + "\r\n";
//        text += "获取设备系统版本号：" + getSDKVersionName() + "\r\n";
//        text += "获取设备系统版本码：" + getSDKVersionCode() + "\r\n";
//
//        YFLog.debug(text);
//
//    }
//
//    public String getManufacturer() {
//        return Build.MANUFACTURER;
//    }
//
//    public String getAndroidID() {
//        String id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
//        return id == null ? "" : id;
//    }
//
//    public String getModel() {
//        String model = Build.MODEL;
//        if (model != null) {
//            model = model.trim().replaceAll("\\s*", "");
//        } else {
//            model = "";
//        }
//        return model;
//    }
//
//    public String getUdid() {
//        String androidID = getAndroidID();
//        return "2" + UUID.nameUUIDFromBytes(androidID.getBytes()).toString().replace("-", "");
//    }
//
//    /**
//     * 获取软件版本号
//     */
//    public int getVersionCode() {
//        int versionCode = 0;
//        //获取软件版本号，对应AndroidManifest.xml下android：versionCode
//        try {
//            versionCode = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//        return versionCode;
//    }
//
//    /**
//     * 获取版本号名称
//     */
//    public String getVerName() {
//        String verName = "";
//        try {
//            verName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//        return verName;
//    }
//
//    public String getSDKVersionName() {
//        return Build.VERSION.RELEASE;
//    }
//
//    public int getSDKVersionCode() {
//        return Build.VERSION.SDK_INT;
//    }

}
