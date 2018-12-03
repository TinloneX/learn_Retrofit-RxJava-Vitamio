package org.tinlone.demo.rxjavasample.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class WhatsPaint extends View {

    private Paint paint;
    private ColorDrawable mBackground;
    private Path mPath;
    private Paint textPaint;

    public WhatsPaint(Context context) {
        this(context,null);
    }

    public WhatsPaint(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WhatsPaint(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        textPaint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GREEN);
        textPaint.setTextSize(40);
        textPaint.setColor(Color.WHITE);
        mBackground = new ColorDrawable(Color.BLACK);
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);



    }
}
