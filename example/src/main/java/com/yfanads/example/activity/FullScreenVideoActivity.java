package com.yfanads.example.activity;

import android.os.Bundle;

import com.yfanads.android.callback.OnResultListener;
import com.yfanads.android.core.full.YFAdFullScreenVideoAds;
import com.yfanads.android.core.full.YFFullScreenVideoListener;
import com.yfanads.android.model.YFAdError;
import com.yfanads.example.R;
import com.yfanads.example.base.BaseActivity;

/**
 * 全屏广告页面.
 *
 * @author JamesQian
 * @date 2023/9/11 10:28
 **/
public class FullScreenVideoActivity extends BaseActivity {

    YFFullScreenVideoListener listener;

    @Override
    public int getLayoutId() {
        return R.layout.activity_full_screen_video;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        String potId = getIntent().getStringExtra("potId");
        startFullVideo(potId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (listener != null) {
            listener = null;
        }
    }

    /**
     * 初始化获取展示全屏视频的广告对象.
     * 也可以选择先提前加载，然后在合适的时机再调用展示方法.
     */
    private void startFullVideo(String adId) {

        //推荐：核心事件监听回调
        listener = new YFFullScreenVideoListener() {

            @Override
            public void onVideoComplete() {
                logAndToast("视频播放结束");
            }

            @Override
            public void onVideoSkipped() {
                logAndToast("跳过视频");
            }

            @Override
            public void onVideoCached() {
                //广告缓存成功，可以在此记录状态，但要注意：不一定所有的广告会返回该回调
                logAndToast("广告缓存成功");
            }

            @Override
            public void onAdSuccess() {
                logAndToast("广告加载成功");
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
                FullScreenVideoActivity.this.finish();
            }

            @Override
            public void onAdFailed(YFAdError yfAdError) {
                logAndToast("广告加载失败 code=" + yfAdError.code + " msg=" + yfAdError.msg);
                FullScreenVideoActivity.this.finish();
            }
        };
        //初始化
        YFAdFullScreenVideoAds easyFullScreenVideo = new YFAdFullScreenVideoAds(this, listener);
        easyFullScreenVideo.toGetData(adId, new OnResultListener() {
            @Override
            public void onSuccess(String jsonString) {
                easyFullScreenVideo.loadAndShow(jsonString);
            }

            @Override
            public void onFailed(int errorCode, String message) {
            }
        });
    }
}
