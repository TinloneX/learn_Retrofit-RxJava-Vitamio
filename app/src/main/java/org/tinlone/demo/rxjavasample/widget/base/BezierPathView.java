package org.tinlone.demo.rxjavasample.widget.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class BezierPathView extends View {

    private Paint paint;
    private Path path1;
    private ColorDrawable backGround;
    private Path path2;

    public BezierPathView(Context context) {
        super(context);
        init();
    }

    public BezierPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BezierPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStrokeWidth(4);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GREEN);
        paint.setTextSize(24);
        backGround = new ColorDrawable(Color.BLACK);
        path1 = new Path();
        path2 = new Path();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float mPreX = event.getX();
        float mPreY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mPreX <= 50 && mPreY <= 50) {
                    path2.reset();
                    postInvalidate();
                }
                path2.moveTo(mPreX, mPreY);
                //return true表示当前控件已经消费了下按动作，之后的ACTION_MOVE、ACTION_UP动作也会继续传递到当前控件中；
                // 如果我们在case MotionEvent.ACTION_DOWN时return false，那么后序的ACTION_MOVE、ACTION_UP动作就不会再传到这个控件来了
                return true;
            case MotionEvent.ACTION_MOVE:
                float endX = (mPreX + event.getX()) / 2;
                float endY = (mPreY + event.getY()) / 2;
                path2.quadTo(mPreX, mPreY, endX, endY);
                postInvalidate();
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackground(backGround);
        if (path2.isEmpty()) {
            path1.moveTo(100, 100);
            path1.quadTo(200, 0, 300, 100);
            path1.quadTo(400, 200, 500, 100);
            canvas.drawPath(path1, paint);
        }

        canvas.drawText("reset", 10, 30, paint);
        canvas.drawPath(path2, paint);
    }
}
