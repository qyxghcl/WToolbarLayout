package com.atoolbarhellper;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.a50647.wtoolbarhelper.ToolBarLayout;

/**
 * 演示页
 *
 * @author wm
 * @date 2019/1/25
 */
public class MainActivity extends AppCompatActivity {

    private ToolBarLayout mToolBarLayout;
    private Toolbar mToolbar;
    private TextView mToolBarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        removeStatueBar();
        setContentView(R.layout.activity_main);
        initToolBar("toolbarLayout", false);
    }

    /**
     * 移除掉状态栏
     */
    private void removeStatueBar() {
        Window window = getWindow();
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 初始化状态栏和标题栏
     *
     * @param title   标题文字
     * @param isTrans 是否选择透明
     */
    private void initToolBar(CharSequence title, boolean isTrans) {
        mToolBarLayout = findViewById(R.id.common_toolbar_layout);
        mToolbar = findViewById(R.id.common_toolbar);
        mToolBarTitle = findViewById(R.id.common_title);
        if (isTrans) {
            mToolBarLayout.setBackgroundColor(getResources().getColor(R.color.transparent));
        } else {
            mToolBarLayout.setBackground(getResources().getDrawable(R.drawable.common_bg_title));
        }
        if (mToolbar == null) {
            return;
        }
        //一定要在setSupportToolBar之前调用
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(isSupportDisplayHomeAsUpEnabled());
        }
        if (mToolBarTitle == null) {
            return;
        }
        mToolBarTitle.setText(title);
    }

    /**
     * 是否支持toolBar的返回键,默认支持
     *
     * @return true:支持 false:不支持
     */
    protected boolean isSupportDisplayHomeAsUpEnabled() {
        return true;
    }
}
