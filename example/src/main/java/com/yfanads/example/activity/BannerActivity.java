package com.yfanads.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yfanads.android.callback.OnResultListener;
import com.yfanads.android.core.banner.YFAdBanner;
import com.yfanads.android.core.banner.YFBannerListener;
import com.yfanads.android.model.YFAdError;
import com.yfanads.android.utils.ScreenUtil;
import com.yfanads.example.R;
import com.yfanads.example.base.BaseActivity;

/**
 * Banner实例.
 *
 * @author JamesQian
 * @version 1.0
 * @copyright 亿帆
 * @date 2023/9/9 15:45
 **/
public class BannerActivity extends BaseActivity {

    String potId;
    TextView textView;
    private RelativeLayout adView;
    private YFBannerListener listener;

    private YFAdBanner showADBanner;

    private boolean isOnlyLoad = false;

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

        adView = findViewById(R.id.banner_layout);

        initListener();
    }


    void initListener() {
        listener = new YFBannerListener() {
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
                isOnlyLoad = false;
            }

            @Override
            public void onAdFailed(YFAdError yfAdError) {
                logAndToast("广告加载失败 code=" + yfAdError.code + " msg=" + yfAdError.msg);
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
    public void loadOnlyAd(View view) {
        this.isOnlyLoad = true;
        startBanner();
    }

    /**
     * 展示.
     *
     * @param view view
     * @author JamesQian
     * @date 2023/9/9 15:47
     **/
    public void showAd(View view) {
        showAds();
    }

    /**
     * 展示.
     *
     * @param view view
     * @author JamesQian
     * @date 2023/9/9 15:47
     **/
    public void loadAndShowAd(View view) {
        this.isOnlyLoad = false;
        startBanner();
    }

    private void startBanner() {
        showADBanner  = new YFAdBanner(this, adView, listener);
        logAndToast("广告请求中");
        showADBanner.toGetData(potId, new OnResultListener() {
            @Override
            public void onSuccess(String jsonString) {
                // 这里示例使用的广告位宽高比为640：100。
                int width = ScreenUtil.px2dip(getApplicationContext(),
                        (float) (ScreenUtil.getScreenWidth(getApplicationContext())));
                // 52是底部高
                int height = width * 9 / 20 - 52;
                Log.d("TEST", "width = " + width + " , screen width =" + height);
                //如果高度传入0代表自适应高度
                showADBanner.setViewAcceptedSize(width, height);
                Log.d("TEST", "开始加载");
                startLoaderAds(jsonString);
            }

            @Override
            public void onFailed(int errorCode, String message) {

            }
        });
    }

    private void startLoaderAds(String jsonString) {
        if (isOnlyLoad) {
            showADBanner.loadOnly(jsonString);
        } else {
            showADBanner.loadAndShow(jsonString);
        }
    }

    private void showAds() {
        if (isOnlyLoad && showADBanner != null) {
            showADBanner.showAds();
        }
    }
}
