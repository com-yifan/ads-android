package com.fc.example.activity.old;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fc.example.R;
import com.fc.example.activity.DrawActivity;
import com.fc.example.activity.FullScreenVideoActivity;
import com.fc.example.activity.InterstitialActivity;
import com.fc.example.activity.NativeExpressRecyclerViewActivity;
import com.fc.example.activity.RewardVideoActivity;
import com.fc.example.activity.SplashActivity;
import com.fc.example.base.BaseActivity;
import com.fc.example.global.GlobalConst;
import com.fc.example.utils.ToastUtils;

/**
 * Copyright: 风船科技
 * Author: JonXhnChn
 * Description:
 * History: 2023/7/20
 */
public class ADSelectActivity extends BaseActivity {
    private int type;


    @Override
    public int getLayoutId() {
        return R.layout.activity_ad_select;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        type = getIntent().getIntExtra("type", GlobalConst.ERROR_NUM);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        type = getIntent().getIntExtra("type", GlobalConst.ERROR_NUM);
    }

    @Override
    public void loadCsjAD(View view) {
        switch (type) {
            case GlobalConst.TYPE_SPLASH:
                startActivity(new Intent(ADSelectActivity.this, SplashActivity.class).putExtra("potId", GlobalConst.SPLASH_AD_ID));
                break;
            case GlobalConst.TYPE_DRAW:
                startActivity(new Intent(ADSelectActivity.this, DrawActivity.class).putExtra("potId", GlobalConst.DRAW_AD_ID));
                break;
            case GlobalConst.TYPE_REWARD:
                startActivity(new Intent(ADSelectActivity.this, RewardVideoActivity.class).putExtra("potId", GlobalConst.REWARD_AD_ID));
                break;
            case GlobalConst.TYPE_INTR:
                startActivity(new Intent(ADSelectActivity.this, InterstitialActivity.class).putExtra("potId", GlobalConst.INSERT_AD_ID));
                break;
            case GlobalConst.TYPE_NATI:
                startActivity(new Intent(ADSelectActivity.this, NativeExpressRecyclerViewActivity.class).putExtra("potId", GlobalConst.NATIVE_PRESS_ID));
                break;
            case GlobalConst.TYPE_FULL_SCREEN:
                startActivity(new Intent(ADSelectActivity.this, FullScreenVideoActivity.class).putExtra("potId", GlobalConst.FULL_SCREEN_VIDEO_AD_ID));
                break;
            default:
                break;
        }
    }

    @Override
    public void loadBdAD(View view) {
        switch (type) {
            case GlobalConst.TYPE_SPLASH:
                startActivity(new Intent(ADSelectActivity.this, SplashActivity.class).putExtra("potId", GlobalConst.BD_SPLASH_AD_ID));
                break;
            case GlobalConst.TYPE_DRAW:
                ToastUtils.showShort("百度无此类型");
                break;
            case GlobalConst.TYPE_REWARD:
                startActivity(new Intent(ADSelectActivity.this, RewardVideoActivity.class).putExtra("potId", GlobalConst.BD_REWARD_AD_ID));
                break;
            case GlobalConst.TYPE_INTR:
                startActivity(new Intent(ADSelectActivity.this, InterstitialActivity.class).putExtra("potId", GlobalConst.BD_INSERT_AD_ID));
                break;
            case GlobalConst.TYPE_NATI:
                startActivity(new Intent(ADSelectActivity.this, NativeExpressRecyclerViewActivity.class).putExtra("potId", GlobalConst.BD_NATIVE_PRESS_ID));
                break;
            case GlobalConst.TYPE_FULL_SCREEN:
                startActivity(new Intent(ADSelectActivity.this, FullScreenVideoActivity.class).putExtra("potId", GlobalConst.BD_FULL_SCREEN_VIDEO_AD_ID));
                break;
            default:
                break;
        }
    }

    @Override
    public void loadYlhAD(View view) {
        switch (type) {
            case GlobalConst.TYPE_SPLASH:
                startActivity(new Intent(ADSelectActivity.this, SplashActivity.class).putExtra("potId", GlobalConst.YLH_SPLASH_AD_ID));
                break;
            case GlobalConst.TYPE_DRAW:
                ToastUtils.showShort("优量汇无此类型");
                break;
            case GlobalConst.TYPE_REWARD:
                startActivity(new Intent(ADSelectActivity.this, RewardVideoActivity.class).putExtra("potId", GlobalConst.YLH_REWARD_AD_ID));
                break;
            case GlobalConst.TYPE_INTR:
                startActivity(new Intent(ADSelectActivity.this, InterstitialActivity.class).putExtra("potId", GlobalConst.YLH_INSERT_AD_ID));
                break;
            case GlobalConst.TYPE_NATI:
                startActivity(new Intent(ADSelectActivity.this, NativeExpressRecyclerViewActivity.class).putExtra("potId", GlobalConst.YLH_NATIVE_PRESS_ID));
                break;
            case GlobalConst.TYPE_FULL_SCREEN:
                startActivity(new Intent(ADSelectActivity.this, FullScreenVideoActivity.class).putExtra("potId", GlobalConst.YLH_FULL_SCREEN_VIDEO_AD_ID));
                break;
            default:
                break;
        }
    }

    @Override
    public void loadKsAD(View view) {
        switch (type) {
            case GlobalConst.TYPE_SPLASH:
                startActivity(new Intent(ADSelectActivity.this, SplashActivity.class).putExtra("potId", GlobalConst.KS_SPLASH_AD_ID));
                break;
            case GlobalConst.TYPE_DRAW:
                startActivity(new Intent(ADSelectActivity.this, DrawActivity.class).putExtra("potId", GlobalConst.KS_DRAW_AD_ID));
                break;
            case GlobalConst.TYPE_REWARD:
                startActivity(new Intent(ADSelectActivity.this, RewardVideoActivity.class).putExtra("potId", GlobalConst.KS_REWARD_AD_ID));
                break;
            case GlobalConst.TYPE_INTR:
                startActivity(new Intent(ADSelectActivity.this, InterstitialActivity.class).putExtra("potId", GlobalConst.KS_INSERT_AD_ID));
                break;
            case GlobalConst.TYPE_NATI:
                startActivity(new Intent(ADSelectActivity.this, NativeExpressRecyclerViewActivity.class).putExtra("potId", GlobalConst.KS_NATIVE_PRESS_ID));
                break;
            case GlobalConst.TYPE_FULL_SCREEN:
                startActivity(new Intent(ADSelectActivity.this, FullScreenVideoActivity.class).putExtra("potId", GlobalConst.KS_FULL_SCREEN_VIDEO_AD_ID));
                break;
            default:
                break;
        }
    }
}
