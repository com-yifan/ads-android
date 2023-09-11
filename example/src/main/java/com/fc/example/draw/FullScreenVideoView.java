package com.fc.example.draw;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * 全屏视频.
 *
 * @author JamesQian
 * @copyright 亿帆
 * @date 2023/9/11 10:29
 * @version 1.0
 **/
public class FullScreenVideoView extends VideoView {
    public FullScreenVideoView(Context context) {
        super(context);
    }

    public FullScreenVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FullScreenVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }
}
