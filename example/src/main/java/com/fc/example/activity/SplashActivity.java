package com.fc.example.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.fc.ads.callback.OnResultListener;
import com.fc.ads.core.splash.YFAdSplashAds;
import com.fc.ads.core.splash.YFSplashListener;
import com.fc.ads.model.FCAdError;
import com.fc.example.R;
import com.fc.example.base.BaseActivity;
import com.fc.example.global.GlobalConst;
import com.fc.example.utils.StatusBar;

import java.lang.reflect.Method;

/**
 * Copyright: 亿帆
 * Author: JonXhnChn
 * Description:
 * History: 2023/7/18
 */
public class SplashActivity extends BaseActivity {
    FrameLayout adContainer;

    LinearLayout adLogoLly;

    int type;

    private YFSplashListener listener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {        //设置颜色为半透明
        StatusBar.setColor(this, android.R.color.transparent);
        //隐藏状态栏
        StatusBar.hide(this);
        return R.layout.activity_splash_custom_logo;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setFullScreen();
        type = getIntent().getIntExtra("type", GlobalConst.ERROR_NUM);
        String potId = getIntent().getStringExtra("potId");

        adContainer = findViewById(R.id.splash_container);
        adLogoLly = findViewById(R.id.ll_logo);

        if (!TextUtils.isEmpty(potId)) {
            loadSplash(adContainer, potId);
        }
    }

    /* *
     * 加载开屏广告，开屏推荐使用加载并展示开屏广告方式，所有的广告均支持请求和展示分离，如有必要，可分别调用加载广告和展示广告，可参考"插屏广告"中的处理示例。
     *
     * @param adContainer    广告承载布局，不可为空
     * @param logoContainer  底部logo布局，可以为空
     * @param singleActivity 是否为单独activity中展示开屏广告
     * @param callBack       跳转回调，在回调中进行跳转主页或其他操作
     */
    private void loadSplash(ViewGroup adContainer, String adId) {
        releaseListener();
        createListener();
        YFAdSplashAds fcAdSplash = new YFAdSplashAds(this, adContainer, listener);
        fcAdSplash.toGetData(adId, new OnResultListener() {
            @Override
            public void onSuccess(String jsonString) {
                //必须：设置策略信息
                fcAdSplash.setData(jsonString);
                //必须：请求并展示广告
                fcAdSplash.loadAndShow();
            }

            @Override
            public void onFailed() {
                logAndToast("广告请求失败");
                finish();
            }
        });
        logAndToast("广告请求中");
    }

    private void createListener() {
        listener = new YFSplashListener() {
            @Override
            public void onAdSuccess() {
                logAndToast("广告加载成功 ");
                if (adLogoLly.getVisibility() == View.GONE) {
                    adLogoLly.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAdExposure() {
                //设置开屏父布局背景色为白色
                logAndToast("广告展示成功");
            }

            @Override
            public void onAdClicked() {
                logAndToast("广告点击");
            }

            @Override
            public void onAdClosed() {

                logAndToast("广告关闭");

                goToMainActivity();
            }

            @Override
            public void onAdFailed(FCAdError fcAdError) {
                logAndToast("广告加载失败 code=" + fcAdError.code + " msg=" + fcAdError.msg);
                finish();
            }

        };
    }

    private void releaseListener() {
        if (listener != null) {
            listener = null;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 跳转到主页面
     */
    private void goToMainActivity() {
//        ToastUtils.showShort("跳转首页");
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TEST", "SplashActivity onDestroy ");
        releaseListener();
    }

    private void setFullScreen() {
        if (Build.VERSION.SDK_INT >= 30) {
            //Android11 适配
            /**
             * 如果编译的sdk是30以上，直接使用如下代码。
             */
//            getWindow().getDecorView().getWindowInsetsController().hide(
//                    WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars());

            /**
             * 如果编译的sdk小于30，使用如下反射的方式。
             *
             * 由于Android版本限制，可能会遇到错误(建议还是使用大于30的sdk编译)：
             * Error: Reflective access to getWindowInsetsController is forbidden when targeting API 29 and above [BlockedPrivateApi]
             *  Explanation for issues of type "BlockedPrivateApi":
             *    Usage of restricted non-SDK interface is forbidden for this targetSDK.
             *    Accessing non-SDK methods or fields through reflection has a high
             *    likelihood to break your app between versions, and is being restricted to
             *    facilitate future app compatibility.
             */
            try {
                View decorView = getWindow().getDecorView();
                Method getWindowInsetsController = View.class.getDeclaredMethod("getWindowInsetsController");
                getWindowInsetsController.setAccessible(true);
                Object insetsController = getWindowInsetsController.invoke(decorView);
                Class<?> WindowInsetsController = Class.forName("android.view.WindowInsetsController");
                Method hide = WindowInsetsController.getMethod("hide", int.class);
                hide.setAccessible(true);
                hide.invoke(insetsController, 3);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        } else {
            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    }
}
