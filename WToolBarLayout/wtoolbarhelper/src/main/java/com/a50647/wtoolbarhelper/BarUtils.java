package com.a50647.wtoolbarhelper;

import android.content.Context;

/**
 * 顶部状态栏的工具类
 *
 * @author wm
 * @date 2019/1/4
 */
class BarUtils {
    /**
     * 获取顶部状态栏的的高度
     *
     * @param context context
     * @return 顶部状态栏的高度
     */
    static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height"
                , "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        if (result == 0) {
            final float scale = context.getResources().getDisplayMetrics().density;
            result = (int) (26 * scale + 0.5f);
        }
        return result;
    }
}
