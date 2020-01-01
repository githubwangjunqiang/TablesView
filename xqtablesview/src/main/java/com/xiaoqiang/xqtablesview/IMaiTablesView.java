package com.xiaoqiang.xqtablesview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

/**
 * @author Android-小强
 * @email: 15075818555@163.com
 * @data: on 2019/12/31 17:48
 */
public interface IMaiTablesView {
    /**
     * 初始化
     *
     * @param context
     * @param attrs
     */
    void init(Context context, AttributeSet attrs);

    /**
     * 画表盘
     *
     * @param canvas
     */
    void drawTables(Canvas canvas);

    /**
     * 获取总数  刻度 总数
     * @return
     */
    int getAllCount();

    /**
     * 开始随机旋转
     */
    void startRandomRoate();

    /**
     * 停止旋转动画
     */
    void closeRandomRoate();

}
