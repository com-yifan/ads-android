package com.fc.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fc.ads.callback.OnResultListener;
import com.fc.ads.core.banner.YFAdBanner;
import com.fc.ads.core.banner.YFBannerListener;
import com.fc.ads.model.FCAdError;
import com.fc.ads.utils.ScreenUtil;
import com.fc.example.R;
import com.fc.example.base.BaseActivity;
import com.fc.example.utils.AppUtils;

/**
 * Banner实例.
 *
 * @author JamesQian
 * @version 1.0
 * @copyright 亿帆
 * @date 2023/9/9 15:45
 **/
public class FCBannerActivity extends BaseActivity {

    String potId;
    TextView textView;
    TextView potIdView;
    //    FCADBanner easyAdBanner;
    private RelativeLayout adView;
    private YFBannerListener listener;

    @Override
    public int getLayoutId() {
        return R.layout.activity_ad_model;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        Intent intent = getIntent();
        potId = intent.getStringExtra("potId");
        textView = findViewById(R.id.title);
        textView.setText(getATitle());
        potIdView = findViewById(R.id.potId);
        potIdView.setText(potId);

        adView = findViewById(R.id.banner_layout);

        initListener();
    }


    void initListener() {
        listener = new YFBannerListener() {
            @Override
            public void loadImage(String url, ImageView view) {
                Glide.with(FCBannerActivity.this).load(url).into(view);
            }

            @Override
            public void onAdSuccess() {
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
    protected void onDestroy() {
        super.onDestroy();
        if (listener != null) {
            listener = null;
        }
    }

    String getATitle() {
        return getString(R.string.banner);
    }

    /**
     * 展示.
     *
     * @param view view
     * @author JamesQian
     * @date 2023/9/9 15:47
     **/
    public void loadAndShowAd(View view) {
        final YFAdBanner showADBanner = new YFAdBanner(this, adView, listener);
        logAndToast("广告请求中");
        showADBanner.toGetData(potId, new OnResultListener() {
            @Override
            public void onSuccess(String jsonString) {
                //如果集成穿山甲，这里必须配置，建议尺寸要和穿山甲后台中的"代码位尺寸"宽高比例一致，值单位为dp，这里示例使用的广告位宽高比为640：100。
                int adWidth = ScreenUtil.px2dip(AppUtils.getContext(),
                        ScreenUtil.getScreenWidth(AppUtils.getContext()));
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
}
