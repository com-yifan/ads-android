package com.yfanads.example.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yfanads.android.callback.OnResultListener;
import com.yfanads.android.core.nat.YFAdNativeExpressAds;
import com.yfanads.android.core.nat.YFNativeExpressListener;
import com.yfanads.android.model.YFAdError;
import com.yfanads.example.R;
import com.yfanads.example.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 信息流展示的页面.
 *
 * @author JamesQian
 * @version 1.0
 * @copyright 亿帆
 * @date 2023/9/9 17:27
 **/
public class NativeExpressRecyclerViewActivity extends BaseActivity {
    public static final int MAX_ITEMS = 50;
    public static final int ITEMS_PER_AD = 4;     // 每间隔10个条目插入一条广告

    private static final int maxAd = 10;

    private RecyclerView mRecyclerView;
    private List<RycItem> recyList = new ArrayList<>();
    private String potId = "";

    private int realAdNumber;

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
            recyList.add(new RycItem("No." + i + " Normal Data"));
        }
        //添加广告的数目
        for (int i = 0; i < maxAd; i++) {
            int position = ITEMS_PER_AD * i;
            if (position <= recyList.size()) {
                //将广告的NormalItem定义为默认的空标题item。
                //也可以不同item使用不同的广告位id作为广告标识，这样方便区分不同item的广告数据表现。
                RycItem adItem = new RycItem("");
                //核心步骤1：初始化广告处理类。
                recyList.add(position, adItem);
                realAdNumber++;
            }
        }

        //列表adapter创建，传入要渲染的数据信息
        CustomAdapter mAdapter = new CustomAdapter(this, recyList);
        mRecyclerView.setAdapter(mAdapter);
        //一定要加上该配置，防止item复用导致广告重复
        mRecyclerView.setItemViewCacheSize(500);
    }


   static class AdView {
        public ViewGroup viewGroup;
        public int index;

        public AdView(ViewGroup viewGroup, int index) {
            this.viewGroup = viewGroup;
            this.index = index;
        }

       @Override
       public String toString() {
           return "AdView{" +
                   "viewGroup=" + viewGroup.hashCode() +
                   ", index=" + index +
                   '}';
       }
   }



    /**
     * CustomAdapter.
     *
     * @author JamesQian
     * @version 1.0
     * @copyright 亿帆
     * @date 2023/9/9 17:28
     **/
    class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

        static final int TYPE_DATA = 0;
        static final int TYPE_AD = 1;
        Activity mActivity;
        int adsStatus = 0;
        private List<RycItem> mData;

        private List<AdView> adViewTemp = new ArrayList<>();
        private List<View> adViewList = new ArrayList<>();

        CustomAdapter(Activity activity, List<RycItem> list) {
            mActivity = activity;
            mData = list;
        }

        public int getItemCount() {
            if (mData != null) {
                return mData.size();
            } else {
                return 0;
            }
        }

        public int getItemViewType(int position) {
            //核心步骤2：根据是否包含标题内容，来判断是否为广告item
            return TextUtils.isEmpty(mData.get(position).title) ? TYPE_AD : TYPE_DATA;
        }

        public void onBindViewHolder(final CustomViewHolder customViewHolder, final int position) {
            int type = getItemViewType(position);
            if (TYPE_AD == type) {
                // 动态计算需要渲染的位置
                loadNativeExpress(potId, customViewHolder.container, position);
            } else {
                customViewHolder.title.setText(mData.get(position).getTitle());
            }
        }

        public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            int layoutId = (viewType == TYPE_AD) ? R.layout.item_express_ad : R.layout.item_data;
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, null);
            return new CustomViewHolder(view);
        }

        /**
         * 加载并展示原生模板信息流广告.
         *
         * @param adId        adId
         * @param adContainer adContainer
         * @author JamesQian
         * @date 2023/9/9 17:29
         **/
        private void loadNativeExpress(String adId, ViewGroup adContainer, int pos) {
            // 需要计算当前的index
            int index = (pos) / ITEMS_PER_AD;
            Log.d("TEST", "loadNativeExpress isNativeLoading " + index);
            // 请求下一页广告数据
            if (index >= adViewList.size()) {
                startRequestAds(adId, adContainer, index);
                return;
            }
            if (adsStatus == 2) {
                //同一位置广告，请求完成，直接渲染
                addView(adContainer, index);
                return;
            }

            if (adsStatus == 1) {
                //同一位置广告，正在请求中，不再重复请求, 缓存对应得view和pos，请求成功后加入到视图中
                Log.d("TEST", "loadNativeExpress isNativeLoading");
                adViewTemp.add(new AdView(adContainer, index));
                return;
            }

            //广告请求
            startRequestAds(adId, adContainer, index);
        }

        private void startRequestAds(String adId, ViewGroup adContainer, int index) {
            adsStatus = 1;
            adViewTemp.add(new AdView(adContainer, index));
            final YFAdNativeExpressAds easyNativeExpress =
                    new YFAdNativeExpressAds(mActivity, new YFNativeExpressListener() {

                        @Override
                        public void onAdRenderSuccess(List<View> viewList) {
                            logAndToast("广告渲染成功 " + viewList.size());
                            Log.e("ad", viewList.size() + " loadNativeExpress " + adViewTemp);
                            adViewList.addAll(viewList);
                            addView();
                            adsStatus = 2;
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
                        public void onAdRenderFailed() {
                            adsStatus = 0;
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
                        public void onAdFailed(YFAdError fcAdError) {
                            adsStatus = 0;
                            logAndToast("广告加载失败 code=" + fcAdError.code + " msg=" + fcAdError.msg);
                        }

                    });
            easyNativeExpress.setAdsNumbers(realAdNumber);
            //必须：设置策略信息
            easyNativeExpress.toGetData(adId, new OnResultListener() {
                @Override
                public void onSuccess(String jsonString) {
                    easyNativeExpress.loadAndShow(jsonString);
                }

                @Override
                public void onFailed(int errorCode, String message) {

                }
            });
            logAndToast("广告请求中");
        }

        private void addView(ViewGroup adContainer, int index) {
            if (index < adViewList.size()) {
                if (adContainer.getChildCount() > 0) {
                    adContainer.removeAllViews();
                }
                adContainer.addView(adViewList.get(index));
            }
        }

        private void addView() {
            if (adViewTemp == null || adViewTemp.isEmpty()) {
                return;
            }
            for (AdView adView: adViewTemp) {
                if (adView.index < adViewList.size()) {
                    adView.viewGroup.addView(adViewList.get(adView.index));
                }
            }
        }

        class CustomViewHolder extends RecyclerView.ViewHolder {
            TextView title;
            ViewGroup container; // 广告承载布局

            CustomViewHolder(View view) {
                super(view);
                title = view.findViewById(R.id.title);
                container = view.findViewById(R.id.express_ad_container);
            }
        }


    }


    /**
     * RycItem.
     *
     * @author JamesQian
     * @version 1.0
     * @copyright 亿帆
     * @date 2023/9/9 17:28
     **/
    public class RycItem {
        private String title;

        RycItem(String title) {
            this.title = title;
        }

        /**
         * 获取标题.
         *
         * @return java.lang.String
         * @author JamesQian
         * @date 2023/9/9 17:30
         **/
        public String getTitle() {
            return title;
        }
    }
}
