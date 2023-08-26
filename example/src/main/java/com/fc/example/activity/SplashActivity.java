package com.fc.example.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.fc.ads.callback.OnResultListener;
import com.fc.ads.core.splash.FCAdSplash;
import com.fc.ads.core.splash.FCSplashListener;
import com.fc.ads.model.FCAdError;
import com.fc.example.R;
import com.fc.example.base.BaseActivity;
import com.fc.example.global.GlobalConst;
import com.fc.example.utils.ToastUtils;

/**
 * Copyright: 风船科技
 * Author: JonXhnChn
 * Description:
 * History: 2023/7/18
 */
public class SplashActivity extends BaseActivity {
    LinearLayout logo;
    FrameLayout adContainer;

    int type;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash_custom_logo;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        type = getIntent().getIntExtra("type", GlobalConst.ERROR_NUM);
        String potId = getIntent().getStringExtra("potId");
        adContainer = findViewById(R.id.splash_container);
        Log.e("splash", "initView -> " + adContainer.hashCode());
        logo = findViewById(R.id.ll_logo);
        if (!TextUtils.isEmpty(potId)) {
            loadSplash(adContainer, logo, true, this::goToMainActivity, potId);
        }
        logo.setOnClickListener(v -> finish());
    }

    /* *
     * 加载开屏广告，开屏推荐使用加载并展示开屏广告方式，所有的广告均支持请求和展示分离，如有必要，可分别调用加载广告和展示广告，可参考"插屏广告"中的处理示例。
     *
     * @param adContainer    广告承载布局，不可为空
     * @param logoContainer  底部logo布局，可以为空
     * @param singleActivity 是否为单独activity中展示开屏广告
     * @param callBack       跳转回调，在回调中进行跳转主页或其他操作
     */
    private void loadSplash(final ViewGroup adContainer, final ViewGroup logoContainer, boolean singleActivity,
                            final SplashCallBack callBack, String adId) {
        //必须：设置开屏核心回调事件的监听器。
        FCSplashListener listener = new FCSplashListener() {

            @Override
            public void onAdSuccess(boolean isCache) {
                logAndToast("广告加载成功");
                // 设置当前布局为不可见，但是需要位置绘制出来
                if (logoContainer != null) {
                    logoContainer.setVisibility(isCache ? View.VISIBLE : View.INVISIBLE);
                }

            }

            @Override
            public void onAdExposure() {
                //设置开屏父布局背景色为白色
                if (adContainer != null) {
                    adContainer.setBackgroundColor(Color.WHITE);
                }
                if (logoContainer != null) {
                    logoContainer.setVisibility(View.VISIBLE);
                }
                logAndToast("广告展示成功");
            }

            @Override
            public void onAdClicked() {
                logAndToast("广告点击");
            }

            @Override
            public void onAdClosed() {
                if (callBack != null)
                    callBack.jumpMain();

                logAndToast("广告关闭");
            }

            @Override
            public void onAdFailed(FCAdError fcAdError) {
                logAndToast("广告加载失败 code=" + fcAdError.code + " msg=" + fcAdError.msg);
            }

        };
        FCAdSplash fcAdSplash = new FCAdSplash(this, adContainer, listener);
        fcAdSplash.toGetData(adId, new OnResultListener() {
            @Override
            public void onSuccess(String jsonString) {
                //注意：如果开屏页是fragment或者dialog实现，这里需要置为false。默认为true，代表开屏和首页为两个不同的activity
                fcAdSplash.setShowInSingleActivity(singleActivity);
                //必须：设置策略信息
                fcAdSplash.setData(jsonString);
                //必须：请求并展示广告
                fcAdSplash.loadAndShow();
            }

            @Override
            public void onFailed() {
                logAndToast("广告请求失败");
            }
        });
        logAndToast("广告请求中");
    }

    /**
     * 跳转到主页面
     */
    private void goToMainActivity() {
        ToastUtils.showShort("跳转首页");
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TEST", "SplashActivity onDestroy ");
    }

    /**
     * 开屏跳转回调
     */
    public interface SplashCallBack {
        void jumpMain();
    }
}
