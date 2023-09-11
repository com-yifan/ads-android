package com.fc.example.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fc.ads.callback.OnResultListener;
import com.fc.ads.core.inter.YFAdInterstitialAds;
import com.fc.ads.core.inter.YFInterstitialListener;
import com.fc.ads.model.FCAdError;
import com.fc.example.R;
import com.fc.example.base.BaseActivity;

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
//    private FCAdInterstitialAds easyInterstitial;

    @Override
    public int getLayoutId() {
        return R.layout.activity_interstitial;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseListener();
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        String potId = getIntent().getStringExtra("potId");

        findViewById(R.id.loadAndShow).setOnClickListener(view -> {
            startInterstitial(potId);
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
        YFAdInterstitialAds easyInterstitial = new YFAdInterstitialAds(this, listener);
        easyInterstitial.setKeyBackCloseAdOfSelfRender(true);
        easyInterstitial.setClickCloseAdOfSelfRender(true);
        easyInterstitial.setViewAcceptedSize(300, 533);
        //必须：设置策略信息
        easyInterstitial.toGetData(adId, new OnResultListener() {
            @Override
            public void onSuccess(String jsonString) {
                easyInterstitial.setData(jsonString);
                easyInterstitial.loadAndShow();
            }

            @Override
            public void onFailed() {
            }
        });
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
            public void loadImage(String url, ImageView view) {
                // 使用宿主自身的图片框架，如果没有则推荐使用Glide
                Glide.with(view.getContext()).load(url).into(view);
            }

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
            }

            @Override
            public void onAdFailed(FCAdError fcAdError) {
                logAndToast("广告加载失败 code=" + fcAdError.code + " msg=" + fcAdError.msg);
            }
        };
    }

    private void releaseListener() {
        if (listener != null) {
            listener = null;
        }
    }
}
