package com.fc.example.activity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fc.ads.callback.OnResultListener;
import com.fc.ads.core.inter.FCAdInterstitialAds;
import com.fc.ads.core.inter.FCInterstitialListener;
import com.fc.ads.model.FCAdError;
import com.fc.example.R;
import com.fc.example.base.BaseActivity;

/**
 * Copyright: 风船科技
 * Author: JonXhnChn
 * Description:
 * History: 2023/7/18
 */
public class InterstitialActivity extends BaseActivity {

    private FCInterstitialListener listener;
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
            startInterstitial(potId, findViewById(R.id.show_area), false);
        });

//        findViewById(R.id.loadOnlyAd).setOnClickListener(view -> {
//            startInterstitial(potId, findViewById(R.id.show_area), true);
//        });
//
//        findViewById(R.id.showAd).setOnClickListener(view -> {
//            if (easyInterstitial != null) {
//                easyInterstitial.show();
//            }
//        });
    }

    /**
     * 初始话插屏广告。
     * 可以选择性先提前加载，然后在合适的时机再调用展示方法
     * 或者直接调用加载并展示广告
     * <p>
     * 注意！！！：穿山甲默认为"新插屏广告"
     */
    private void startInterstitial(String adId, ViewGroup viewGroup, boolean isLoadOnly) {
        releaseListener();
        createListener();
        //初始化
        FCAdInterstitialAds easyInterstitial = new FCAdInterstitialAds(this, listener);
        easyInterstitial.setViewGroup(viewGroup);
        easyInterstitial.setKeyBackCloseAdOfSelfRender(true);
        easyInterstitial.setClickCloseAdOfSelfRender(false);
        easyInterstitial.setViewAcceptedSize(300, 533);
        //必须：设置策略信息
        easyInterstitial.toGetData(adId, new OnResultListener() {
            @Override
            public void onSuccess(String jsonString) {
                easyInterstitial.setData(jsonString);
//                if (isLoadOnly)
//                    easyInterstitial.loadOnly();
//                else
                easyInterstitial.loadAndShow();
            }

            @Override
            public void onFailed() {
            }
        });
    }

    public void createListener() {
        listener = new FCInterstitialListener() {

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
