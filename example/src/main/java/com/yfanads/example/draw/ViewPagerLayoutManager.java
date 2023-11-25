package com.yfanads.example.draw;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * ViewPagerLayoutManager.
 *
 * @author JamesQian
 * @copyright 亿帆
 * @date 2023/9/11 9:45
 * @version 1.0
 **/
public class ViewPagerLayoutManager extends LinearLayoutManager
        implements RecyclerView.OnChildAttachStateChangeListener {

    private PagerSnapHelper mPagerSnapHelper;
    private OnViewPagerListener mOnViewPagerListener;

    // 位移，用来判断移动方向
    private int mDrift;

    {
        mPagerSnapHelper = new PagerSnapHelper();
    }

    public ViewPagerLayoutManager(Context context, int orientation) {
        this(context, orientation, false);
    }

    public ViewPagerLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    @Override
    public void onAttachedToWindow(RecyclerView recyclerView) {
        super.onAttachedToWindow(recyclerView);
        mPagerSnapHelper.attachToRecyclerView(recyclerView);
        recyclerView.addOnChildAttachStateChangeListener(this);
    }

    @Override
    public void onScrollStateChanged(int state) {
        if (state == RecyclerView.SCROLL_STATE_IDLE) {
            View viewIdle = mPagerSnapHelper.findSnapView(ViewPagerLayoutManager.this);
            if (viewIdle == null) {
                return;
            }
            int positionIdle = getPosition(viewIdle);
            if (mOnViewPagerListener != null && getChildCount() == 1) {
                mOnViewPagerListener.onPageSelected(positionIdle,
                        positionIdle == getItemCount() - 1);
            }
        }
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler,
                                  RecyclerView.State state) {
        this.mDrift = dy;
        return super.scrollVerticallyBy(dy, recycler, state);
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler,
                                    RecyclerView.State state) {
        this.mDrift = dx;
        return super.scrollHorizontallyBy(dx, recycler, state);
    }

    /**
     * 设置监听.
     *
     * @author JamesQian
     * @date 2023/9/11 9:46
     * @param listener listener
     **/
    public void setOnViewPagerListener(OnViewPagerListener listener) {
        this.mOnViewPagerListener = listener;
    }

    @Override
    public void onChildViewAttachedToWindow(@NonNull View view) {
        if (mOnViewPagerListener != null && getChildCount() == 1) {
            mOnViewPagerListener.onInitComplete();
        }
    }

    @Override
    public void onChildViewDetachedFromWindow(@NonNull View view) {
        if (mDrift >= 0) {
            if (mOnViewPagerListener != null) {
                mOnViewPagerListener.onPageRelease(true, getPosition(view));
            }
        } else {
            if (mOnViewPagerListener != null) {
                mOnViewPagerListener.onPageRelease(false, getPosition(view));
            }
        }
    }
}
