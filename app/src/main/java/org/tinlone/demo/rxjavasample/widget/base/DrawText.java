package org.tinlone.demo.rxjavasample.widget.base;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class DrawText extends View {

    private Paint paint;
    private int baseLineX;
    private int baseLineY;
    private Paint pointPaint;

    public DrawText(Context context) {
        super(context);
        init();
    }

    public DrawText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        pointPaint = new Paint();
        pointPaint.setColor(Color.BLACK);
        pointPaint.setStyle(Paint.Style.FILL);
        this.paint.setColor(Color.RED);
        baseLineX = 0;
        baseLineY = 100;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(baseLineX, baseLineY, 3000, baseLineY, paint);
        canvas.drawCircle(baseLineX, baseLineY, 5, pointPaint);
        paint.setColor(Color.GREEN);
        paint.setTextSize(80);
        canvas.drawText("Hello,yajago!", baseLineX, baseLineY, paint);
        baseLineX = 200;
        baseLineY = 200;
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawCircle(baseLineX, baseLineY, 5, pointPaint);
        canvas.drawText("Hello,yajago!", baseLineX, baseLineY, paint);
        baseLineX = 400;
        baseLineY = 300;
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawCircle(baseLineX, baseLineY, 5, pointPaint);
        canvas.drawText("Hello,yajago!", baseLineX, baseLineY, paint);
        baseLineX = 500;
        baseLineY = 400;
        paint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawCircle(baseLineX, baseLineY, 5, pointPaint);
        canvas.drawText("Hello,yajago!", baseLineX, baseLineY, paint);
        baseLineX = 0;
        baseLineY = 550;
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setStrokeWidth(2);
        canvas.drawText("Hello,yajago!", baseLineX, baseLineY, paint);
        Paint.FontMetrics fm = paint.getFontMetrics();
        float ascent = baseLineY + fm.ascent;
        float descent = baseLineY + fm.descent;
        float top = baseLineY + fm.top;
        float bottom = baseLineY + fm.bottom;
        //画基线
        paint.setColor(Color.RED);
        canvas.drawLine(baseLineX, baseLineY, 3000, baseLineY, paint);
        //画top
        paint.setColor(Color.BLUE);
        canvas.drawLine(baseLineX, top, 3000, top, paint);
        //画ascent
        paint.setColor(Color.GREEN);
        canvas.drawLine(baseLineX, ascent, 3000, ascent, paint);
        //画descent
        paint.setColor(Color.YELLOW);
        canvas.drawLine(baseLineX, descent, 3000, descent, paint);
        //画bottom
        paint.setColor(Color.RED);
        canvas.drawLine(baseLineX, bottom, 3000, bottom, paint);

        //需要在（50,600为顶点写字）
        pointPaint.setColor(Color.YELLOW);
        canvas.drawCircle(50, 600, 5, pointPaint);
        paint.setTextAlign(Paint.Align.LEFT);
        baseLineX = 50;
        Paint.FontMetricsInt metricsInt = paint.getFontMetricsInt();
        baseLineY = 600 - metricsInt.top;
        Log.i("qqqqq", "" + metricsInt.top);
        pointPaint.setColor(Color.BLACK);
        canvas.drawCircle(baseLineX, baseLineY, 5, pointPaint);
        paint.setColor(Color.BLUE);
        canvas.drawLine(baseLineX, baseLineY, baseLineX + paint.measureText("(50,600)"), baseLineY, paint);
        paint.setColor(Color.GREEN);
        canvas.drawText("(50,600)", baseLineX, baseLineY, paint);

    }
}
