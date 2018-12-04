package org.tinlone.demo.rxjavasample.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import org.tinlone.demo.rxjavasample.util.TLog;

public class WhatsPaintC extends View {

    private Paint paint;
    private ColorDrawable mBackground;
    private Path path;
    private PathDashPathEffect pdpe;

    public WhatsPaintC(Context context) {
        this(context, null);
    }

    public WhatsPaintC(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WhatsPaintC(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        path = new Path();
        mBackground = new ColorDrawable(Color.BLACK);
    }

    private void resetPaint() {
        paint = new Paint();
        paint.setStrokeWidth(4);
        paint.setAntiAlias(true);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        TLog.w("onDraw");
        setBackground(mBackground);
        resetPaint();
        path.moveTo(100, 600);
        path.lineTo(400, 100);
        path.lineTo(700, 900);
        canvas.drawPath(path, paint);

        canvas.translate(0, 200);
        paint.setPathEffect(getPathEffect());
        canvas.drawPath(path, paint);
    }

    private PathEffect getPathEffect() {
        if (pdpe == null) {
            Path stampPath = new Path();
            stampPath.moveTo(0, 20);
            stampPath.lineTo(10, 0);
            stampPath.lineTo(20, 20);
            stampPath.close();
            stampPath.addCircle(0, 0, 3, Path.Direction.CCW);
            pdpe = new PathDashPathEffect(stampPath, 35, 0, PathDashPathEffect.Style.TRANSLATE);
        }
        return pdpe;
    }

}
