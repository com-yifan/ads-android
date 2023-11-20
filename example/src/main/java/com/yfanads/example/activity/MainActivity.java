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
        startActivity(new Intent(this, BannerActivity.class).putExtra("potId",
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

}
