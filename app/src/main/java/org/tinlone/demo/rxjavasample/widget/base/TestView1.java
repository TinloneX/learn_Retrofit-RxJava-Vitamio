package org.tinlone.demo.rxjavasample.widget.base;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * @author tinlone
 * @date 2017/10/24 0024.
 */

public class TestView1 extends View {
    private Paint mPaint;
    private Random mRandom;
    private float[] points;
    private RectF rect;
    private Rect rect2;
    private RectF rectx;
    private RectF rect1;
    private RectF rectx2;

    public TestView1(Context context) {
        this(context, null);
    }

    public TestView1(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mRandom = new Random();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(20);
        mPaint.setShadowLayer(10, 15, 15, Color.GRAY);
        points = new float[2000];
        rect = new RectF(820, 80, 810, 800);
        rect2 = new Rect(830, 80, 920, 800);
        rectx = new RectF(200, 20, 600, 200);
        rect1 = new RectF(500, 110, 100, 1100);
        rectx2 = new RectF(600, 110, 900, 1100);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRGB(255, 255, 255);

        canvas.drawCircle(400, 100, 80, mPaint);
        float[] pts = {100, 150, 150, 200, 150, 200, 200, 100};
        canvas.drawLines(pts, mPaint);

        canvas.drawPoint(400, 100, mPaint);
        int width = getWidth();
        int height = getHeight();

        for (int i = 0; i < points.length; i++) {
            if (i % 2 == 0) {
                points[i] = mRandom.nextInt(width);
            } else {
                points[i] = mRandom.nextInt(height);
            }
        }
        mPaint.setStrokeWidth(8);

        canvas.drawPoints(points, 250, 750, mPaint);

        canvas.drawRect(210, 210, 800, 800, mPaint);

        canvas.drawRect(rect, mPaint);

        canvas.drawRect(rect2, mPaint);

        mPaint.setColor(Color.GREEN);
        //画矩形
        canvas.drawRect(rectx, mPaint);
        //更改画笔颜色
        mPaint.setColor(Color.GREEN);
        //同一个矩形画椭圆
        canvas.drawOval(rectx, mPaint);

        canvas.drawArc(rect1, 0, 190, true, mPaint);

        canvas.drawArc(rectx2, 0, 190, false, mPaint);
    }

}
