package com.a50647.wtoolbarhelper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * 自定义ToolBar
 *
 * @author wm
 * @date 2019/1/4
 */
public class ToolBarLayout extends LinearLayout {
    private boolean mIsHalfTrans;
    private Context mContext;
    private FrameLayout mStatusBar;

    public ToolBarLayout(Context context) {
        this(context, null);
    }

    public ToolBarLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @SuppressLint("CustomViewStyleable")
    public ToolBarLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        if (attrs != null) {
            TypedArray arr = mContext.obtainStyledAttributes(attrs, R.styleable.WidgetToolBarLayout);
            mIsHalfTrans = arr.getBoolean(R.styleable.WidgetToolBarLayout_isHalfTrans, false);
            arr.recycle();
        }
        initView();
    }

    private void initView() {
        setOrientation(VERTICAL);
        //创建status
        mStatusBar = new FrameLayout(mContext);
        int statusBarHeight = BarUtils.getStatusBarHeight(mContext);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                statusBarHeight);
        initStatueBar();
        addView(mStatusBar, lp);
    }

    private void initStatueBar() {
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        if (mIsHalfTrans) {
            View halfStatueBar = new View(mContext);
            halfStatueBar.setBackgroundColor(mContext.getResources().getColor(R.color.widget_trans_half));
            mStatusBar.addView(halfStatueBar, lp);
        }
    }

    public FrameLayout getStatusBar() {
        return mStatusBar;
    }
}
