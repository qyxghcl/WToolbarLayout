package com.atoolbarhellper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.a50647.wtoolbarhelper.ToolBarLayout;

import java.lang.reflect.Method;

/**
 * 基本activity封装
 *
 * @author wm
 * @date 2019/1/25
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected Context mContext;
    protected ToolBarLayout mToolBarLayout;
    protected Toolbar mToolbar;
    protected TextView mToolBarTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (!isSupportScreenShot()) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        }
        translateStatueBar();
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(getLayoutId());
        initView();
        fitScreen();
    }

    /**
     * 沉浸式
     */
    protected void translateStatueBar() {
        Window window = getWindow();
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }

    /**
     * 是否支持截屏 默认支持
     *
     * @return true 支持 false 不支持
     */
    protected boolean isSupportScreenShot() {
        return true;
    }

    /**
     * 获取布局id
     *
     * @return 布局id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 一些需要代码动态适配的地方
     */
    protected void fitScreen() {

    }

    /**
     * 设置默认的title
     *
     * @param title   标题
     * @param isTrans 是否透明
     */
    protected void initToolBar(CharSequence title, boolean isTrans) {
        mToolBarLayout = findViewById(R.id.common_toolbar_layout);
        mToolbar = findViewById(R.id.common_toolbar);
        mToolBarTitle = findViewById(R.id.common_title);
        if (isTrans) {
            mToolBarLayout.setBackgroundColor(mContext.getResources().getColor(R.color.transparent));
        } else {
            mToolBarLayout.setBackground(mContext.getResources().getDrawable(R.drawable.common_bg_title));
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

    /**
     * toolbar菜单
     *
     * @param menu 菜单
     * @return true 表示支持
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (isSupportMenu()) {
            getMenuInflater().inflate(getMenuLayout(), menu);
        }
        return true;
    }

    /**
     * 是否支持菜单栏 默认不支持
     *
     * @return true:支持 false:不支持,默认false
     */
    protected boolean isSupportMenu() {
        return false;
    }

    /**
     * 支持菜单栏的情况下才会调用,返回菜单布局
     *
     * @return 菜单布局
     */
    protected int getMenuLayout() {
        return R.menu.menu_main;
    }

    /**
     * toolbar回退键
     *
     * @param item menu中的按钮
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.R.id.home == item.getItemId()) {
            onClickToolBarHome();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 当点击toolBar的home键,默认是backPress,不同地方可重写
     */
    protected void onClickToolBarHome(){
        onBackPressed();
    }

    /**
     * 在使用toolBar的menu的时候,添加此方法则可以在menu中显示图标
     */
    @SuppressLint("RestrictedApi")
    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        if (!isSupportMenuIcon()) {
            return super.onPrepareOptionsPanel(view, menu);
        }
        if (menu != null) {
            if (menu.getClass() == MenuBuilder.class) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onPrepareOptionsPanel(view, menu);
    }

    /**
     * 在使用menu的时候,是否支持menu列表中图标的显示,默认支持
     *
     * @return true:支持 false:不支持
     */
    protected boolean isSupportMenuIcon() {
        return true;
    }
}
