package com.yfanads.example.activity;

import android.os.Bundle;
import android.util.Log;

import com.yfanads.android.callback.OnResultListener;
import com.yfanads.android.core.reward.YFAdRewardAds;
import com.yfanads.android.core.reward.YFRewardServerCallBackInf;
import com.yfanads.android.core.reward.YFRewardVideoListener;
import com.yfanads.android.model.YFAdError;
import com.yfanads.example.R;
import com.yfanads.example.base.BaseActivity;

/**
 * 激励视频页面.
 *
 * @author JamesQian
 * @version 1.0
 * @copyright 亿帆
 * @date 2023/9/11 10:31
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
            public void onAdFailed(YFAdError yfAdError) {
                logAndToast("广告加载失败 code=" + yfAdError.code + " msg=" + yfAdError.msg);
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
            public void onRewardServerInf(YFRewardServerCallBackInf inf) {
                logAndToast("奖励发放成功");
                if (inf != null) {
                    onRewardServer(inf.rewardInf);
                }
            }
        };
        //初始化，注意需要时再初始化，不要复用。
        final YFAdRewardAds easyRewardVideo = new YFAdRewardAds(this, listener, "123456");
        // 自由业务拓展的字段
        easyRewardVideo.setAppExtra("packageName", "com.fc.example");
        easyRewardVideo.toGetData(adId, new OnResultListener() {
            @Override
            public void onSuccess(String jsonString) {
                easyRewardVideo.loadAndShow(jsonString);
            }

            @Override
            public void onFailed(int errorCode, String message) {
            }
        });
        //必须：设置策略信息
    }

    private void onRewardServer(YFRewardServerCallBackInf.RewardInf rewardInf) {

        Log.d("reward", "type = " + rewardInf.type + " , " + rewardInf.appExtra);

        if (rewardInf instanceof YFRewardServerCallBackInf.BDRewardInf) {
            Log.d("reward", "type = BDRewardInf ");
        } else if (rewardInf instanceof YFRewardServerCallBackInf.YlhRewardInf) {
            Log.d("reward", "type = YlhRewardInf");
        } else if (rewardInf instanceof YFRewardServerCallBackInf.KsRewardInf) {
            Log.d("reward", "type = KsRewardInf");
        } else if (rewardInf instanceof YFRewardServerCallBackInf.CsjRewardInf) {
            Log.d("reward", "type = CsjRewardInf");
        } else {
            Log.d("reward", "type = Other");
        }
    }

}
