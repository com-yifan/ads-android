package com.fc.example.activity.old;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.fc.ads.callback.OnResultListener;
import com.fc.ads.core.banner.YFAdBanner;
import com.fc.ads.core.banner.YFBannerListener;
import com.fc.ads.model.FCAdError;
import com.fc.ads.utils.ScreenUtil;
import com.fc.example.R;
import com.fc.example.base.BaseActivity;
import com.fc.example.global.GlobalConst;
import com.fc.example.utils.AppUtils;

/**
 * banner的展示页面.
 *
 * @author JamesQian
 * @version 1.0
 * @copyright 亿帆
 * @date 2023/9/9 11:12
 **/
public class BannerActivity extends BaseActivity {
    private RelativeLayout rl;

    @Override
    public int getLayoutId() {
        return R.layout.activity_banner;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        rl = findViewById(R.id.banner_layout);
    }

    @Override
    public void loadCsjAD(View view) {
        rl.removeAllViews();
        loadBanner(rl, GlobalConst.AD_ID);
    }

    @Override
    public void loadYlhAD(View view) {
        rl.removeAllViews();
        loadBanner(rl, GlobalConst.YLH_AD_ID);
    }

    @Override
    public void loadBdAD(View view) {
        rl.removeAllViews();
        loadBanner(rl, GlobalConst.BD_AD_ID);
    }

    /**
     * 加载并展示banner广告.
     *
     * @param adContainer banner广告的承载布局
     */
    private void loadBanner(/*String jsonFileName, */ViewGroup adContainer, String adId) {
        //必须：核心事件监听回调
        YFBannerListener listener = new YFBannerListener() {
            @Override
            public void loadImage(String url, ImageView view) {
                Glide.with(view.getContext()).load(url).into(view);
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
        YFAdBanner easyAdBanner = new YFAdBanner(this, adContainer, listener);
        logAndToast("广告请求中");
        easyAdBanner.toGetData(adId, new OnResultListener() {
            @Override
            public void onSuccess(String jsonString) {
                //如果集成穿山甲，这里必须配置，建议尺寸要和穿山甲后台中的"代码位尺寸"宽高比例一致，值单位为dp，这里示例使用的广告位宽高比为640：100。
                int adWidth = ScreenUtil.px2dip(AppUtils.getContext(),
                        ScreenUtil.getScreenWidth(AppUtils.getContext()));
                int adHeight = (int) (((double) adWidth / (double) 640) * 100);
                //如果高度传入0代表自适应高度
                easyAdBanner.setViewAcceptedSize(adWidth, adHeight);
                Log.d("TEST", "设置数据");
                easyAdBanner.setData(jsonString);
                Log.d("TEST", "开始加载");
                easyAdBanner.loadAndShow();
            }

            @Override
            public void onFailed() {

            }
        });
    }
}
