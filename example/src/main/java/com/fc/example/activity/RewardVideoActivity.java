package com.fc.example.activity;

import android.os.Bundle;

import com.fc.ads.callback.OnResultListener;
import com.fc.ads.core.reward.YFAdRewardAds;
import com.fc.ads.core.reward.YFRewardServerCallBackInf;
import com.fc.ads.core.reward.YFRewardVideoListener;
import com.fc.ads.model.FCAdError;
import com.fc.example.R;
import com.fc.example.base.BaseActivity;

/**
 * 激励视频页面.
 *
 * @author JamesQian
 * @copyright 亿帆
 * @date 2023/9/11 10:31
 * @version 1.0
 **/
public class RewardVideoActivity extends BaseActivity {

    YFRewardVideoListener listener;

    @Override
    public int getLayoutId() {
        return R.layout.activity_reward_video;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        String potId = getIntent().getStringExtra("potId");
        startReward(potId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (listener != null) {
            listener = null;
        }
    }

    /* *
     * 加载并展示激励视频广告。
     * 也可以选择性先提前加载，然后在合适的时机再调用展示方法
     */
    private void startReward(String adId) {
        //必须：核心事件监听回调
        listener = new YFRewardVideoListener() {

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
                RewardVideoActivity.this.finish();
            }

            @Override
            public void onAdFailed(FCAdError fcAdError) {
                logAndToast("广告加载失败 code=" + fcAdError.code + " msg=" + fcAdError.msg);
                RewardVideoActivity.this.finish();
            }


            @Override
            public void onVideoCached() {
                logAndToast("广告缓存成功");
            }

            @Override
            public void onVideoComplete() {
                logAndToast("视频播放完毕");
            }

            @Override
            public void onVideoSkip() {
                logAndToast("跳过视频");
            }

            @Override
            public void onAdReward() {
                logAndToast("激励发放");
            }

            @Override
            public void onRewardServerInf(YFRewardServerCallBackInf inf) {
                logAndToast("onRewardServerInf" + inf);
            }
        };
        //初始化，注意需要时再初始化，不要复用。
        final YFAdRewardAds easyRewardVideo = new YFAdRewardAds(this, listener);
        easyRewardVideo.toGetData(adId, new OnResultListener() {
            @Override
            public void onSuccess(String jsonString) {
                easyRewardVideo.setData(jsonString);
                easyRewardVideo.loadAndShow();
            }

            @Override
            public void onFailed(int errorCode, String message) {
            }
        });
        //必须：设置策略信息
    }

}
