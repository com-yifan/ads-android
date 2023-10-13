package com.yfanads.example.draw;


/**
 * viewPage的回调监听.
 *
 * @author JamesQian
 * @copyright 亿帆
 * @date 2023/9/11 10:30
 * @version 1.0
 **/
public interface OnViewPagerListener {

    /**
     * 初始化完成.
     *
     * @author JamesQian
     * @date 2023/9/11 10:30
     **/
    void onInitComplete();

    /**
     * 释放的监听.
     *
     * @author JamesQian
     * @date 2023/9/11 10:30
     * @param isNext isNext
     * @param position position
     **/
    void onPageRelease(boolean isNext, int position);

    /**
     * 选中的监听以及判断是否滑动到底部.
     *
     * @author JamesQian
     * @date 2023/9/11 10:30
     * @param position position
     * @param isBottom isBottom
     **/
    void onPageSelected(int position, boolean isBottom);

}
