package com.fc.example.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.fc.example.utils.ToastUtils;

/**
 * 基础Activity.
 *
 * @author JamesQian
 * @date 2023/9/11 10:27
 **/
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView(savedInstanceState);
    }

    /**
     * 获取布局ID.
     *
     * @author JamesQian
     * @date 2023/9/11 10:27
     * @return int
     **/
    public abstract int getLayoutId();

    /**
     * 初始化布局.
     *
     * @author JamesQian
     * @date 2023/9/11 10:27
     * @param savedInstanceState savedInstanceState
     **/
    public abstract void initView(Bundle savedInstanceState);

    /**
     * 点击快手.
     *
     * @author JamesQian
     * @date 2023/9/11 10:27
     * @param view view
     **/
    public void loadKsAD(View view) {
        ToastUtils.showShort("开发中，敬请期待");
    }

    /**
     * 点击穿山甲.
     *
     * @author JamesQian
     * @date 2023/9/11 10:27
     * @param view view
     **/

    public void loadCsjAD(View view) {
        ToastUtils.showShort("开发中，敬请期待");
    }

    /**
     * 点击优量汇.
     *
     * @author JamesQian
     * @date 2023/9/11 10:27
     * @param view view
     **/
    public void loadYlhAD(View view) {
        ToastUtils.showShort("开发中，敬请期待");
    }

    /**
     * 点击百度.
     *
     * @author JamesQian
     * @date 2023/9/11 10:27
     * @param view view
     **/
    public void loadBdAD(View view) {
        ToastUtils.showShort("开发中，敬请期待");
    }

    /**
     * 统一处理打印日志，并且toast提示.
     *
     * @param msg 需要显示的内容
     */
    public void logAndToast(String msg) {
        Log.d("[DemoUtil][logAndToast]", msg);
        com.hjq.toast.ToastUtils.debugShow(msg);
    }

}
