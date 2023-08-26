package com.fc.example.activity.atype;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fc.example.R;
import com.fc.example.base.BaseActivity;

/**
 * Copyright: 风船科技
 * Author: JonXhnChn
 * Description:
 * History: 2023/7/20
 */
public abstract class ShowActivity extends BaseActivity {

    protected String potId;

    protected TextView titleTV;

    protected TextView potIdTV;

    @Override
    public int getLayoutId() {
        return R.layout.activity_ad_model;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        Intent intent = getIntent();
        potId = intent.getStringExtra("potId");
        titleTV = findViewById(R.id.title);
        titleTV.setText(getATitle());
        potIdTV = findViewById(R.id.potId);
        potIdTV.setText(potId);
        initListener();
    }

    abstract void initListener();

    abstract String getATitle();

    public void loadOnlyAd(View view) {
    }

    public void showAd(View view) {

    }

    public void loadAndShowAd(View view) {

    }
}
