package org.tinlone.demo.rxjavasample.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import org.tinlone.demo.rxjavasample.R;

public class XferModeView extends View {

    private Paint paint;
    private Paint textPaint;
    private Bitmap bitmap;
    private int width;
    private int height;
    private Rect rect;
    private PorterDuffXfermode porterDuffXfermode;

    public XferModeView(Context context) {
        this(context, null);
    }

    public XferModeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XferModeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        textPaint = new Paint();
        textPaint.setTextSize(40);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.BLACK);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.a);
        width = 800;
        height = 800 * bitmap.getHeight() / bitmap.getWidth();
        rect = new Rect(0, 0, width, height);
        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.BLUE);

        int layerId = canvas.saveLayer(0, 0, width, height, paint, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(bitmap, null, rect, paint);
        paint.setXfermode(porterDuffXfermode);
        canvas.drawBitmap(bitmap, width / 3f, height / 3f, paint);
        paint.setXfermode(null);
        canvas.restoreToCount(layerId);

    }
}
