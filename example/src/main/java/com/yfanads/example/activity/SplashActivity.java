package com.yfanads.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.yfanads.android.callback.OnResultListener;
import com.yfanads.android.core.splash.YFAdSplashAds;
import com.yfanads.android.core.splash.YFSplashListener;
import com.yfanads.android.model.YFAdError;
import com.yfanads.example.R;
import com.yfanads.example.base.BaseActivity;
import com.yfanads.example.global.GlobalConst;
import com.yfanads.example.utils.AppUtils;
import com.yfanads.example.utils.StatusBar;

/**
 * 开屏页面.
 *
 * @author JamesQian
 * @version 1.0
 * @copyright 亿帆
 * @date 2023/9/11 9:19
 **/
public class SplashActivity extends BaseActivity {
    FrameLayout adContainer;

    LinearLayout adLogoLly;

    int type;

    private YFSplashListener listener;

    private boolean isFromInit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash_custom_logo;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        type = getIntent().getIntExtra("type", GlobalConst.ERROR_NUM);
        String potId = getIntent().getStringExtra("potId");

        if (TextUtils.isEmpty(potId)) {
            isFromInit = true;
            potId = GlobalConst.SPLASH_AD_ID;
        }

        adContainer = findViewById(R.id.splash_container);
        adLogoLly = findViewById(R.id.ll_logo);

        if (!TextUtils.isEmpty(potId)) {
            loadSplash(adContainer, potId);
        }
    }

    /* *
     * 加载开屏广告，开屏推荐使用加载并展示开屏广告方式
     *
     * @param adContainer    广告承载布局，不可为空
     * @param logoContainer  底部logo布局，可以为空
     * @param singleActivity 是否为单独activity中展示开屏广告
     * @param callBack       跳转回调，在回调中进行跳转主页或其他操作
     */
    private void loadSplash(ViewGroup adContainer, String adId) {
        releaseListener();
        createListener();
        YFAdSplashAds fcAdSplash = new YFAdSplashAds(this, adContainer, listener);
        // 实际展示的高度，但不低于屏幕高度的75% （屏幕高度-LOGO高度-底部状态栏区域）
        int expressViewHeight = AppUtils.px2dip(this, AppUtils.getScreenHeight(this));
        // 如果底部需要展示logo，此为logo的大小，单位是dp
        expressViewHeight -= 63;
        // 如果是沉浸式状态栏，需要加上状态栏的高度
        expressViewHeight += AppUtils.px2dip(this, StatusBar.getStatusBarHeight(this));
        // 设置实际请求的高度
        fcAdSplash.setHeight(expressViewHeight);

        fcAdSplash.toGetData(adId, new OnResultListener() {
            @Override
            public void onSuccess(String jsonString) {
                //必须：请求并展示广告
                fcAdSplash.loadAndShow(jsonString);
            }

            @Override
            public void onFailed(int errorCode, String message) {
                logAndToast("广告请求失败");
                goToMainActivity();
                finish();
            }
        });
        logAndToast("广告请求中");
    }

    private void createListener() {
        listener = new YFSplashListener() {
            @Override
            public void onAdSuccess() {
                logAndToast("广告加载成功 ");
                if (adLogoLly.getVisibility() == View.GONE) {
                    adLogoLly.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAdExposure() {
                //设置开屏父布局背景色为白色
                logAndToast("广告展示成功");
            }

            @Override
            public void onAdClicked() {
                logAndToast("广告点击");
            }

            @Override
            public void onAdClosed() {

                logAndToast("广告关闭");

                goToMainActivity();
            }

            @Override
            public void onAdFailed(YFAdError yfAdError) {
                logAndToast("广告加载失败 code=" + yfAdError.code + " msg=" + yfAdError.msg);
                goToMainActivity();
            }

        };
    }

    private void releaseListener() {
        if (listener != null) {
            listener = null;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void goToMainActivity() {
        if (isFromInit) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TEST", "SplashActivity onDestroy ");
        releaseListener();
    }
}
