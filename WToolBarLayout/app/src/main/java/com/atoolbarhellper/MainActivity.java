package com.atoolbarhellper;

/**
 * 演示页
 *
 * @author wm
 * @date 2019/1/25
 */
public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initToolBar("toolbarLayout", false);
    }

    @Override
    protected boolean isSupportMenu() {
        return true;
    }
}
