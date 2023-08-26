package com.fc.example.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fc.ads.callback.OnResultListener;
import com.fc.ads.core.nat.FCAdNativeExpress;
import com.fc.ads.core.nat.FCNativeExpressListener;
import com.fc.ads.model.FCAdError;
import com.fc.example.R;
import com.fc.example.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright: 风船科技
 * Author: JonXhnChn
 * Description:
 * History: 2023/7/19
 */
public class NativeExpressRecyclerViewActivity extends BaseActivity {
    public static final int MAX_ITEMS = 50;
    public static int FIRST_AD_POSITION = 1; // 第一条广告的位置
    public static int ITEMS_PER_AD = 10;     // 每间隔10个条目插入一条广告

    private RecyclerView mRecyclerView;
    private List<RycItem> mNormalDataList = new ArrayList<>();
    private String potId = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_native_express_recycler_view;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        potId = getIntent().getStringExtra("potId");
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        initData();
    }

    private void initData() {
        for (int i = 0; i < MAX_ITEMS; ++i) {
            mNormalDataList.add(new RycItem("No." + i + " Normal Data"));
        }
        //添加广告的数目，这里定义了3条广告。
        int maxAD = 4;

        for (int i = 0; i < maxAD; i++) {
            int position = FIRST_AD_POSITION + ITEMS_PER_AD * i;
            if (position <= mNormalDataList.size()) {
                //将广告的NormalItem定义为默认的空标题item。
                //也可以不同item使用不同的广告位id作为广告标识，这样方便区分不同item的广告数据表现。
                RycItem adItem = new RycItem("");
                //核心步骤1：初始化广告处理类。
                mNormalDataList.add(position, adItem);
            }
        }

        //列表adapter创建，传入要渲染的数据信息
        CustomAdapter mAdapter = new CustomAdapter(this, mNormalDataList);
        mRecyclerView.setAdapter(mAdapter);
        //一定要加上该配置，防止item复用导致广告重复
        mRecyclerView.setItemViewCacheSize(500);
    }


    /**
     * RecyclerView的Adapter
     */
    class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

        static final int TYPE_DATA = 0;
        static final int TYPE_AD = 1;
        public boolean hasNativeShow = false;
        Activity mActivity;
        boolean isNativeLoading = false;
        private List<RycItem> mData;

        public CustomAdapter(Activity activity, List<RycItem> list) {
            mActivity = activity;
            mData = list;
        }

        @Override
        public int getItemCount() {
            if (mData != null) {
                return mData.size();
            } else {
                return 0;
            }
        }

        @Override
        public int getItemViewType(int position) {
            //核心步骤2：根据是否包含标题内容，来判断是否为广告item
            return TextUtils.isEmpty(mData.get(position).title) ? TYPE_AD : TYPE_DATA;
        }

        @Override
        public void onBindViewHolder(final CustomViewHolder customViewHolder, final int position) {
            int type = getItemViewType(position);
            if (TYPE_AD == type) {
                loadNativeExpress(potId, customViewHolder.container);
            } else {
                customViewHolder.title.setText(mData.get(position).getTitle());
            }
        }

        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            int layoutId = (viewType == TYPE_AD) ? R.layout.item_express_ad : R.layout.item_data;
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, null);
            CustomViewHolder viewHolder = new CustomViewHolder(view);
            return viewHolder;
        }

        /**
         * 加载并展示原生模板信息流广告
         *
         * @param adContainer 广告的承载布局
         */
        private void loadNativeExpress(String adId, ViewGroup adContainer) {

            if (hasNativeShow) {//同一位置广告，已展示过不再重复发起请求
                Log.d("TEST", "loadNativeExpress hasNativeShow");
                return;
            }

            if (isNativeLoading) {//同一位置广告，正在请求中，不再重复请求
                Log.d("TEST", "loadNativeExpress isNativeLoading");
                return;
            }
            isNativeLoading = true;

            if (adContainer.getChildCount() > 0) {
                adContainer.removeAllViews();
            }


            //推荐：核心事件监听回调
            FCNativeExpressListener listener = new FCNativeExpressListener() {

                @Override
                public void onAdRenderSuccess() {
                    logAndToast("广告渲染成功");

                }

                @Override
                public void onAdSuccess(boolean isCache) {
                    logAndToast("广告加载成功");
                }

                @Override
                public void onAdExposure() {
                    hasNativeShow = true;
                    isNativeLoading = false;
                    logAndToast("广告展示");
                }

                @Override
                public void onAdRenderFailed() {
                    isNativeLoading = false;
                    logAndToast("广告渲染失败");
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
                    isNativeLoading = false;
                    logAndToast("广告加载失败 code=" + fcAdError.code + " msg=" + fcAdError.msg);
                }

            };
            //初始化
            final FCAdNativeExpress easyNativeExpress = new FCAdNativeExpress(mActivity, listener);
            easyNativeExpress.setAdContainer(adContainer);
            logAndToast("广告请求中");
            //必须：设置策略信息
            easyNativeExpress.toGetData(adId, new OnResultListener() {
                @Override
                public void onSuccess(String jsonString) {
                    easyNativeExpress.setData(jsonString);
                    easyNativeExpress.loadAndShow();
                }

                @Override
                public void onFailed() {

                }
            });
        }

        class CustomViewHolder extends RecyclerView.ViewHolder {
            public TextView title;
            public ViewGroup container; // 广告承载布局

            public CustomViewHolder(View view) {
                super(view);
                title = view.findViewById(R.id.title);
                container = view.findViewById(R.id.express_ad_container);
            }
        }


    }


    //列表子类
    public class RycItem {
        private String title;

        public RycItem(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
