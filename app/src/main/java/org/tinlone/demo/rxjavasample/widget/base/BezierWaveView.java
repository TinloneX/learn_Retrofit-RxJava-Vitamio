package org.tinlone.demo.rxjavasample.widget.base;

import android.animation.ValueAnimator;
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
import android.view.animation.LinearInterpolator;

public class BezierWaveView extends View {

    private Paint paint;
    private ColorDrawable mBackGround;
    private Path path;
    private Path path1;
    private boolean drawWave;
    private Path wavePath;
    private int dy;
    private int dx;
    private Paint textPaint;
    private ValueAnimator animator;
    private int mItemWaveLength = 400;

    public BezierWaveView(Context context) {
        super(context);
        init();
    }

    public BezierWaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BezierWaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        textPaint = new Paint();
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint.setTextSize(60);
        textPaint.setColor(Color.WHITE);
        mBackGround = new ColorDrawable(Color.BLACK);
        path = new Path();
        path1 = new Path();
        wavePath = new Path();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (event.getX() <= 100 && event.getY() <= 100) {
                drawWave = !drawWave;
                startAnim();
            } else {
                if (animator != null) {
                    animator.cancel();
                }
            }
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackground(mBackGround);
        canvas.drawText("wave", 20, 40, textPaint);
        if (!drawWave) {
            paint.setStyle(Paint.Style.STROKE);
            path.moveTo(100, 200);
            path.quadTo(200, 100, 300, 200);
            path.quadTo(400, 300, 500, 200);
            paint.setColor(Color.YELLOW);
            canvas.drawPath(path, paint);
            path1.moveTo(100, 300);
            path1.rQuadTo(100, -100, 200, 0);
            // 相对上个结束点坐标
            path1.rQuadTo(100, 100, 200, 0);
            paint.setColor(Color.GREEN);
            canvas.drawPath(path1, paint);
        } else {
            wavePath.reset();
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            int originY = getHeight();
            int halfWaveLen = mItemWaveLength / 2;
            wavePath.moveTo(-mItemWaveLength + dx, originY - dy);
            for (int i = -mItemWaveLength; i <= getWidth() + mItemWaveLength; i += mItemWaveLength) {
                wavePath.rQuadTo(halfWaveLen / 2, -100, halfWaveLen, 0);
                wavePath.rQuadTo(halfWaveLen / 2, 100, halfWaveLen, 0);
            }
            wavePath.lineTo(getWidth(), getHeight());
            wavePath.lineTo(0, getHeight());
            wavePath.close();
            canvas.drawPath(wavePath, paint);
            String text = String.format("%s%%", dy * 100f / (getHeight()));
            canvas.drawText(text, (getWidth() - textPaint.measureText(text)) / 2, getHeight() / 2, textPaint);
        }
    }

    public void startAnim() {
        if (animator == null) {
            animator = ValueAnimator.ofInt(0, mItemWaveLength);
            animator.setDuration(2000);
            animator.setRepeatMode(ValueAnimator.REVERSE);
            animator.setRepeatCount(ValueAnimator.INFINITE);
            animator.setInterpolator(new LinearInterpolator());
            animator.addUpdateListener(animation -> {
                dx = (int) animation.getAnimatedValue();
                dy = 2 * dx;
                postInvalidate();
            });
        }
        animator.start();
    }
}
