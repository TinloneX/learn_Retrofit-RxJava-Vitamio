package org.tinlone.demo.rxjavasample.widget.paint;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class WhatsPaintA extends View {

    private Paint paint;
    private ColorDrawable mBackground;
    private Path path;
    private Paint textPaint;
    private Path path1;
    private CornerPathEffect cp100;
    private CornerPathEffect cp200;
    private Path path2;
    private DashPathEffect dp1;
    private DashPathEffect dp2;
    private int df;
    private ValueAnimator anim1;

    public WhatsPaintA(Context context) {
        this(context, null);
    }

    public WhatsPaintA(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WhatsPaintA(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();

        path = new Path();
        path1 = new Path();
        path2 = new Path();
        cp100 = new CornerPathEffect(100);
        cp200 = new CornerPathEffect(200);
        dp2 = new DashPathEffect(new float[]{20, 10, 50, 100}, 15);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStrokeWidth(40);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.MITER);
        paint.setColor(Color.GREEN);
//        Paint.Cap
        paint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawLine(50, 50, 100, 50, paint);
        paint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawLine(150, 50, 200, 50, paint);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(250, 50, 300, 50, paint);
//      Paint.Join
        path.moveTo(200, 100);
        path.lineTo(450, 100);
        path.lineTo(450, 200);
        paint.setStrokeJoin(Paint.Join.MITER);
        canvas.drawPath(path, paint);

        path.moveTo(100, 150);
        path.lineTo(350, 150);
        path.lineTo(350, 300);
        paint.setStrokeJoin(Paint.Join.BEVEL);
        canvas.drawPath(path, paint);

        path.moveTo(50, 200);
        path.lineTo(250, 200);
        path.lineTo(250, 300);
        paint.setStrokeJoin(Paint.Join.ROUND);
        canvas.drawPath(path, paint);
// paint.setPathEffect
        paint.setStrokeWidth(4);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);

        path1.moveTo(500, 300);
        path1.lineTo(600, 100);
        path1.lineTo(700, 300);
        canvas.drawPath(path1, paint);
        paint.setColor(Color.RED);
        paint.setPathEffect(cp100);
        canvas.drawPath(path1, paint);
        paint.setColor(Color.YELLOW);
        paint.setPathEffect(cp200);
        canvas.drawPath(path1, paint);
//
        paint.setColor(Color.GREEN);
        path2.moveTo(100, 600);
        path2.lineTo(300, 350);
        path2.lineTo(500, 600);
        canvas.drawPath(path2, paint);
        paint.setColor(Color.RED);
//使用DashPathEffect画线段
//实际请不要这样用，频繁生成对象会越来越卡
        dp1 = new DashPathEffect(new float[]{20, 10, 100, 100}, df);
        paint.setPathEffect(dp1);
        canvas.translate(0, 50);
        canvas.drawPath(path2, paint);
//画同一条线段,偏移值为15
        paint.setPathEffect(dp2);
        paint.setColor(Color.BLUE);
        canvas.translate(0, 50);
        canvas.drawPath(path2, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                anim1();
                return true;
        }
        return super.onTouchEvent(event);
    }

    public void anim1() {
        if (anim1 == null) {
            anim1 = ValueAnimator.ofInt(0, 230);
            anim1.setDuration(3000);
            anim1.setRepeatMode(ValueAnimator.RESTART);
            anim1.setRepeatCount(ValueAnimator.INFINITE);
            anim1.setInterpolator(new LinearInterpolator());
            anim1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    df = (int) animation.getAnimatedValue();
                    invalidate();
                }
            });
        }
        anim1.start();
    }
}
