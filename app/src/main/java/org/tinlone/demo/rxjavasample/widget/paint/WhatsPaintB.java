package org.tinlone.demo.rxjavasample.widget.paint;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.SumPathEffect;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class WhatsPaintB extends View {

    private Paint paint;
    private ColorDrawable mBackground;
    private Path path;
    private DiscretePathEffect dpe1;
    private DiscretePathEffect dpe2;
    private DiscretePathEffect dpe3;
    private CornerPathEffect cp5;
    private DashPathEffect dp10;
    private SumPathEffect spe;

    public WhatsPaintB(Context context) {
        this(context, null);
    }

    public WhatsPaintB(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WhatsPaintB(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        path = getPath();
        cp5 = new CornerPathEffect(15);
        mBackground = new ColorDrawable(Color.BLACK);
        dpe1 = new DiscretePathEffect(2, 5);
        dpe2 = new DiscretePathEffect(6, 5);
        dpe3 = new DiscretePathEffect(6, 15);
        dp10 = new DashPathEffect(new float[]{10, 10}, 15);
        spe = new SumPathEffect(cp5,dp10);
    }

    private Path getPath() {
        Path path = new Path();
        // 定义路径的起点
        path.moveTo(0, 0);

        // 定义路径的各个点
        for (int i = 0; i <= 40; i++) {
            path.lineTo(i*35, (float) (Math.random() * 150));
        }
        return path;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackground(mBackground);
        paint.setStrokeWidth(4);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.MITER);
        paint.setPathEffect(cp5);
        paint.setColor(Color.GREEN);
        canvas.drawPath(path, paint);

        canvas.translate(0, 150);
        paint.setPathEffect(dp10);
        canvas.drawPath(path, paint);
//第二条Path
        canvas.translate(0, 150);
        paint.setPathEffect(dpe1);
        canvas.drawPath(path, paint);
//第三条Path
        canvas.translate(0, 150);
        paint.setPathEffect(dpe2);
        canvas.drawPath(path, paint);
//第四条Path
        canvas.translate(0, 150);
        paint.setPathEffect(dpe3);
        canvas.drawPath(path, paint);

        canvas.translate(0, 150);
        paint.setPathEffect(spe);
        canvas.drawPath(path, paint);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            return true;
        }
        return super.onTouchEvent(event);
    }
}
