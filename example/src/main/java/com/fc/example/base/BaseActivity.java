package com.fc.example.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.fc.example.utils.ToastUtils;

/**
 * 基础Activity
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView(savedInstanceState);
    }

    public abstract int getLayoutId();

    public abstract void initView(Bundle savedInstanceState);

    public void loadKsAD(View view) {
        ToastUtils.showShort("开发中，敬请期待");
    }


    public void loadCsjAD(View view) {
        ToastUtils.showShort("开发中，敬请期待");
    }


    public void loadYlhAD(View view) {
        ToastUtils.showShort("开发中，敬请期待");
    }

    public void loadBdAD(View view) {
        ToastUtils.showShort("开发中，敬请期待");
    }

    /**
     * 统一处理打印日志，并且toast提示。
     *
     * @param msg 需要显示的内容
     */
    public void logAndToast(String msg) {
        Log.d("[DemoUtil][logAndToast]", msg);
        com.hjq.toast.ToastUtils.debugShow(msg);
    }

}
