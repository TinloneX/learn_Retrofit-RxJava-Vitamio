package org.tinlone.demo.rxjavasample.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CanvasChangeView extends View {

    private Paint paint;
    private Rect rect1;
    private float dy = 0f;
    private float dx = 0f;
    private float degrees = 0f;
    private float sx = 0f;
    private float sy = 0f;
    private float skx = 0f;

    public CanvasChangeView(Context context) {
        super(context);
    }

    public CanvasChangeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasChangeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CanvasChangeView init() {
        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        rect1 = new Rect(0, 0, 400, 220);
        return this;
    }

    public CanvasChangeView translate(boolean need) {
        dx = need ? 100 : 0;
        dy = need ? 100 : 0;
        return this;
    }

    public CanvasChangeView rotate(boolean need) {
        degrees = need ? 30 : 0;
        return this;
    }

    public CanvasChangeView scale(boolean need) {
        sx = need ? 0.5f : 1;
        sy = need ? 0.5f : 1;
        return this;
    }

    public CanvasChangeView skew(boolean need){
        skx = need ? 1.732f : 1;
        return this;
    }

    public void draw1(){
        init();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (paint == null)
            init();
        canvas.translate(dx, dy);
        canvas.drawRect(rect1, paint);
        canvas.translate(100, 100);
        paint.setColor(Color.RED);
        canvas.drawRect(rect1, paint);

        canvas.rotate(degrees);
        paint.setColor(Color.BLUE);
        canvas.drawRect(rect1, paint);

        canvas.skew(skx, 0f);
        paint.setColor(Color.LTGRAY);
        canvas.drawRect(rect1, paint);

        canvas.scale(sx, sy);
        paint.setColor(Color.YELLOW);
        canvas.drawRect(rect1, paint);

    }
}
