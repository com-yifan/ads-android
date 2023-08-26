package com.fc.example.activity.atype;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.fc.ads.callback.OnResultListener;
import com.fc.ads.core.banner.FCADBanner;
import com.fc.ads.core.banner.FCBannerListener;
import com.fc.ads.model.FCAdError;
import com.fc.ads.utils.ScreenUtil;
import com.fc.example.R;
import com.fc.example.utils.AppUtils;

public class FCBannerActivity extends ShowActivity {

    FCADBanner easyAdBanner;
    private RelativeLayout adView;
    private FCBannerListener listener;

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        adView = findViewById(R.id.banner_layout);
    }

    @Override
    void initListener() {
        listener = new FCBannerListener() {
            @Override
            public void loadImage(String url, ImageView view) {
                Glide.with(FCBannerActivity.this).load(url).into(view);
            }

            @Override
            public void onAdSuccess(boolean isCache) {
                logAndToast("广告加载成功");
            }

            @Override
            public void onAdExposure() {
                logAndToast("广告展现");
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

    @Override
    String getATitle() {
        return getString(R.string.banner);
    }

    @Override
    public void loadOnlyAd(View view) {
        super.loadOnlyAd(view);
        easyAdBanner = new FCADBanner(this, adView, listener);
        logAndToast("广告请求中");
        easyAdBanner.toGetData(potId, new OnResultListener() {
            @Override
            public void onSuccess(String jsonString) {
                //如果集成穿山甲，这里必须配置，建议尺寸要和穿山甲后台中的"代码位尺寸"宽高比例一致，值单位为dp，这里示例使用的广告位宽高比为640：100。
                int adWidth = ScreenUtil.px2dip(AppUtils.getContext(), ScreenUtil.getScreenWidth(AppUtils.getContext()));
                int adHeight = (int) (((double) adWidth / (double) 640) * 100);
                //如果高度传入0代表自适应高度
                easyAdBanner.setViewAcceptedSize(adWidth, adHeight);
                Log.d("TEST", "设置数据");
                easyAdBanner.setData(jsonString);
                Log.d("TEST", "开始加载");
                easyAdBanner.loadOnly();
            }

            @Override
            public void onFailed() {
            }
        });
    }

    @Override
    public void loadAndShowAd(View view) {
        super.loadAndShowAd(view);
        final FCADBanner showADBanner = new FCADBanner(this, adView, listener);
        logAndToast("广告请求中");
        showADBanner.toGetData(potId, new OnResultListener() {
            @Override
            public void onSuccess(String jsonString) {
                //如果集成穿山甲，这里必须配置，建议尺寸要和穿山甲后台中的"代码位尺寸"宽高比例一致，值单位为dp，这里示例使用的广告位宽高比为640：100。
                int adWidth = ScreenUtil.px2dip(AppUtils.getContext(), ScreenUtil.getScreenWidth(AppUtils.getContext()));
                int adHeight = (int) (((double) adWidth / (double) 640) * 100);
                //如果高度传入0代表自适应高度
                showADBanner.setViewAcceptedSize(adWidth, adHeight);
                Log.d("TEST", "设置数据");
                showADBanner.setData(jsonString);
                Log.d("TEST", "开始加载");
                showADBanner.loadAndShow();
            }

            @Override
            public void onFailed() {

            }
        });
    }

    @Override
    public void showAd(View view) {
        super.showAd(view);
        if (easyAdBanner != null) {
            easyAdBanner.show();
        }
    }
}
