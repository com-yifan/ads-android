package com.fc.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.fc.ads.utils.FCAdsConst;
import com.fc.example.R;
import com.fc.example.base.BaseActivity;
import com.fc.example.global.GlobalConst;
import com.fc.example.utils.AppUtils;

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
     * @author JamesQian
     * @date 2023/9/9 17:24
     * @param view view
     **/
    public void clickBanner(View view) {
        startActivity(new Intent(this, FCBannerActivity.class).putExtra("potId",
                GlobalConst.AD_ID));
    }

    /**
     * 点击debug.
     *
     * @author JamesQian
     * @date 2023/9/9 17:24
     * @param view view
     **/
    public void clickDebug(View view) {
        AppUtils.setDebug(!AppUtils.isDebug());
        Log.d("main", "clickDebug " + AppUtils.isDebug());
        if (AppUtils.isDebug()) {
            debugBtn.setText(R.string.close_debug);
            FCAdsConst.IS_REQ = true;
        } else {
            debugBtn.setText(R.string.open_debug);
            FCAdsConst.IS_REQ = false;
        }
    }

    /**
     * 点击开屏页面.
     *
     * @author JamesQian
     * @date 2023/9/9 17:24
     * @param view view
     **/
    public void clickSplash(View view) {
        startActivity(new Intent(this, SplashActivity.class).putExtra("potId",
                GlobalConst.SPLASH_AD_ID));
    }

    /**
     * 点击插屏广告.
     *
     * @author JamesQian
     * @date 2023/9/9 17:24
     * @param view view
     **/
    public void clickInti(View view) {
        startActivity(new Intent(this, InterstitialActivity.class).putExtra("potId",
                GlobalConst.INSERT_AD_ID));
    }

    /**
     * 点击信息流.
     *
     * @author JamesQian
     * @date 2023/9/9 17:25
     * @param view view
     **/
    public void clickDraw(View view) {
        startActivity(
                new Intent(this, DrawActivity.class).putExtra("potId", GlobalConst.DRAW_AD_ID));
    }

    /**
     * 点击全屏视频.
     *
     * @author JamesQian
     * @date 2023/9/9 17:25
     * @param view view
     **/
    public void clickFullVideo(View view) {
        startActivity(new Intent(this, FullScreenVideoActivity.class).putExtra("potId",
                GlobalConst.FULL_SCREEN_VIDEO_AD_ID));
    }

    /**
     * 点击激励视频.
     *
     * @author JamesQian
     * @date 2023/9/9 17:25
     * @param view view
     **/
    public void clickReward(View view) {
        startActivity(new Intent(this, RewardVideoActivity.class).putExtra("potId",
                GlobalConst.REWARD_AD_ID));
    }

    /**
     * 点击信息流.
     *
     * @author JamesQian
     * @date 2023/9/9 17:25
     * @param view view
     **/
    public void onNativeExpressRecyclerView(View view) {
        startActivity(
                new Intent(this, NativeExpressRecyclerViewActivity.class).putExtra("potId",
                        GlobalConst.NATIVE_PRESS_ID));
    }

}
