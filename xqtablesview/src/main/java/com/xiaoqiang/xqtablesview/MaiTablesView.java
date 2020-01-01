package com.xiaoqiang.xqtablesview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * @author Android-小强
 * @email: 15075818555@163.com
 * @data: on 2019/12/31 17:41
 */
public class MaiTablesView extends FrameLayout implements IMaiTablesView {
    /**
     * 进度值的参数
     */
    public static final String PROGRESSVALUE = "progressValue";
    /**
     * 表 画笔
     */
    private Paint mPaint;
    /**
     * 表 画笔  进度
     */
    private Paint mPaintProgress;
    /**
     * 几组数据
     */
    private int mGroups;
    /**
     * 一组几个
     */
    private int mGroupCount;
    /**
     * 起始
     */
    private float startX;
    /**
     * 起始
     */
    private float startY;
    /**
     * 停止
     */
    private float stopX;
    /**
     * 停止 长
     */
    private float stopY;
    /**
     * 开始 短
     */
    private float startYShort;
    /**
     * 起始位置的刻度 长度占用 宽度的百分比
     */
    private float mPercentageLong;
    /**
     * 普通位置的刻度 长度占用 宽度的百分比
     */
    private float mPercentageShort;
    /**
     * 刻度的宽度
     */
    private float mStrokeWidth;
    /**
     * 刻度的宽度  进度
     */
    private float mStrokeWidthProgress;
    /**
     * 进度值
     */
    private int mProgressValue;
    /**
     * 刻度颜色
     */
    private int colorTables;
    /**
     * 进度 刻度的颜色
     */
    private int colorProgress;

    public int getGroups() {
        return mGroups;
    }

    public void setGroups(int groups) {
        mGroups = Math.abs(groups);
        postInvalidate();
    }

    public int getGroupCount() {
        return mGroupCount;
    }

    public void setGroupCount(int groupCount) {
        mGroupCount = Math.abs(groupCount);
        postInvalidate();
    }

    public float getPercentageLong() {
        return mPercentageLong;
    }

    public void setPercentageLong(float percentageLong) {
        mPercentageLong = Math.abs(percentageLong);
        postInvalidate();
    }

    public float getPercentageShort() {
        return mPercentageShort;
    }

    public void setPercentageShort(float percentageShort) {
        mPercentageShort = Math.abs(percentageShort);
        postInvalidate();
    }

    public float getStrokeWidth() {
        return mStrokeWidth;
    }

    public void setStrokeWidth(float strokeWidth) {
        mStrokeWidth = Math.abs(strokeWidth);
        mPaint.setStrokeWidth(mStrokeWidth);
        postInvalidate();
    }

    public MaiTablesView(Context context) {
        this(context, null);
    }


    public MaiTablesView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MaiTablesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    public void setStrokeWidthProgress(float strokeWidthProgress) {
        mStrokeWidthProgress = Math.abs(strokeWidthProgress);
        mPaintProgress.setStrokeWidth(mStrokeWidthProgress);
        postInvalidate();
    }

    public void setColorTables(int colorTables) {
        this.colorTables = colorTables;
        mPaint.setColor(colorTables);
        postInvalidate();
    }

    public void setColorProgress(int colorProgress) {
        this.colorProgress = colorProgress;
        mPaintProgress.setColor(colorProgress);
        postInvalidate();
    }

    @Override
    public void init(Context context, AttributeSet attrs) {
        mStrokeWidth = 8;
        mStrokeWidthProgress = 10;
        mGroups = 1;
        mGroupCount = 10;
        mPercentageLong = 0.1F;
        mPercentageShort = 0.05F;
        colorTables = Color.WHITE;
        colorProgress = Color.RED;

        TypedArray typedArray = null;
        if (attrs != null) {
            typedArray =
                    context.getResources().obtainAttributes(attrs, R.styleable.MaiTablesView);
        }
        if (typedArray != null) {
            mStrokeWidth =
                    typedArray.getDimension(R.styleable.MaiTablesView_tables_strokewidth, mStrokeWidth);
            mStrokeWidthProgress =
                    typedArray.getDimension(R.styleable.MaiTablesView_tables_strokewidthprogress, mStrokeWidth);
            mGroups = typedArray.getInteger(R.styleable.MaiTablesView_tables_groups, mGroups);
            mGroups = Math.abs(mGroups);
            mGroupCount = typedArray.getInteger(R.styleable.MaiTablesView_tables_groupscount, mGroupCount);
            mGroupCount = Math.abs(mGroupCount);
            mPercentageLong = typedArray.getFloat(R.styleable.MaiTablesView_tables_percentagelong, mPercentageLong);
            mPercentageLong = Math.abs(mPercentageLong);
            mPercentageShort = typedArray.getFloat(R.styleable.MaiTablesView_tables_percentageshort, mPercentageShort);
            mPercentageShort = Math.abs(mPercentageShort);


            colorTables = typedArray.getColor(R.styleable.MaiTablesView_tables_color, colorTables);
            colorProgress = typedArray.getColor(R.styleable.MaiTablesView_tables_colorprogress, colorProgress);

            mProgressValue = typedArray.getInteger(R.styleable.MaiTablesView_tables_progressvalue, mProgressValue);

            mProgressValue = Math.min(mGroups * mGroupCount, Math.abs(mProgressValue));

            typedArray.recycle();
        }


        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(colorTables);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        mPaintProgress = new Paint();
        mPaintProgress.setAntiAlias(true);
        mPaintProgress.setColor(colorProgress);
        mPaintProgress.setStyle(Paint.Style.FILL);
        mPaintProgress.setStrokeWidth(mStrokeWidthProgress);
        mPaintProgress.setStrokeCap(Paint.Cap.ROUND);


    }

    @Override
    public void drawTables(Canvas canvas) {
        canvas.save();
        canvas.rotate(180, getWidth() * 0.5F, getHeight() * 0.5F);
        int allCount = mGroups * mGroupCount;
        float v = 360F / (allCount * 1F);
        for (int i = 0; i < allCount; i++) {
            Paint paint = i < mProgressValue ? mPaintProgress : mPaint;

            if (i == 0 || (i >= mGroupCount && (i + 1) % mGroupCount == 1)) {
                canvas.drawLine(startX, startY, stopX, stopY, paint);
            } else {
                canvas.drawLine(startX, startYShort, stopX, stopY, paint);
            }
            canvas.rotate(v, getWidth() * 0.5F, getHeight() * 0.5F);
        }
        canvas.restore();
    }

    @Override
    public int getAllCount() {
        return mGroupCount * mGroups;
    }

    /**
     * 随机旋转动画
     */
    private ObjectAnimator mObjectAnimator;

    @Override
    public void startRandomRoate() {
        if (mObjectAnimator == null) {
            mObjectAnimator = ObjectAnimator.ofInt(this, MaiTablesView.PROGRESSVALUE,
                    mProgressValue, getAllCount(), mProgressValue);
            mObjectAnimator.setDuration(3000);
        } else {
            mObjectAnimator.end();
        }
        mObjectAnimator.start();
    }

    @Override
    public void closeRandomRoate() {
        if (mObjectAnimator != null) {
            mObjectAnimator.cancel();
        }
        mObjectAnimator = null;
    }

    @Override
    protected void onDetachedFromWindow() {
        closeRandomRoate();
        super.onDetachedFromWindow();
    }

    public void setProgressValue(int progressValue) {
        mProgressValue = Math.min(mGroupCount * mGroups, Math.abs(progressValue));
        postInvalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        startX = w / 2 - mPaint.getStrokeWidth() / 2;
        startY = Math.max(getPaddingTop(), mStrokeWidth * 0.5F);
        stopX = startX;
        if (mPercentageLong < mPercentageShort) {
            stopY = startY + w * mPercentageShort;
            startYShort = w * mPercentageShort - w * mPercentageLong + startY;
        } else {
            stopY = startY + w * mPercentageLong;
            startYShort = w * mPercentageLong - w * mPercentageShort + startY;
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        drawTables(canvas);
    }
}
