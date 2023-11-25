package com.yfanads.example.activity;

import android.os.Bundle;

import com.yfanads.android.callback.OnResultListener;
import com.yfanads.android.core.inter.YFAdInterstitialAds;
import com.yfanads.android.core.inter.YFInterstitialListener;
import com.yfanads.android.model.YFAdError;
import com.yfanads.example.R;
import com.yfanads.example.base.BaseActivity;

/**
 * 插屏广告.
 *
 * @author JamesQian
 * @copyright 亿帆
 * @date 2023/9/11 10:30
 * @version 1.0
 **/
public class InterstitialActivity extends BaseActivity {

    private YFInterstitialListener listener;

    private YFAdInterstitialAds easyInterstitial;

    private boolean isOnlyLoad = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_interstitial;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseListener();
        if (easyInterstitial != null) {
            easyInterstitial.destroy();
            easyInterstitial = null;
        }
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        String potId = getIntent().getStringExtra("potId");

        findViewById(R.id.loadAndShow).setOnClickListener(view -> {
            isOnlyLoad = false;
            startInterstitial(potId);
        });

        findViewById(R.id.loadOnlyAd).setOnClickListener(view -> {
            isOnlyLoad = true;
            startInterstitial(potId);
        });

        findViewById(R.id.showAd).setOnClickListener(view -> {
            showAds();
        });
    }

    /**
     * 初始话插屏广告.
     * 可以选择性先提前加载，然后在合适的时机再调用展示方法.
     * 或者直接调用加载并展示广告.
     * <p>
     * 注意！！！：穿山甲默认为"新插屏广告".
     */
    private void startInterstitial(String adId) {
        releaseListener();
        createListener();
        //初始化
        easyInterstitial = new YFAdInterstitialAds(this, listener);
        //必须：设置策略信息
        easyInterstitial.toGetData(adId, new OnResultListener() {
            @Override
            public void onSuccess(String jsonString) {
                startLoaderAds(jsonString);
            }

            @Override
            public void onFailed(int errorCode, String message) {
            }
        });
    }

    private void startLoaderAds(String jsonString) {
        if (isOnlyLoad) {
            easyInterstitial.loadOnly(jsonString);
        } else {
            easyInterstitial.loadAndShow(jsonString);
        }
    }

    private void showAds() {
        if (isOnlyLoad && easyInterstitial != null) {
            easyInterstitial.showAds();
        }
    }

    /**
     * 创建监听.
     *
     * @author JamesQian
     * @date 2023/9/11 10:31
     **/
    public void createListener() {
        listener = new YFInterstitialListener() {

            @Override
            public void onAdSuccess() {
                logAndToast("广告就绪");
            }

            @Override
            public void onAdExposure() {
                logAndToast("广告展示");
            }

            @Override
            public void onAdClicked() {
                logAndToast("广告点击");
            }

            @Override
            public void onAdClosed() {
                logAndToast("广告关闭");
                if (easyInterstitial != null) {
                    easyInterstitial.destroy();
                    easyInterstitial = null;
                }
                isOnlyLoad = false;
            }

            @Override
            public void onAdFailed(YFAdError yfAdError) {
                logAndToast("广告加载失败 code=" + yfAdError.code + " msg=" + yfAdError.msg);

                isOnlyLoad = false;
            }
        };
    }

    private void releaseListener() {
        if (listener != null) {
            listener = null;
        }
    }
}
