package org.tinlone.demo.rxjavasample.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import org.tinlone.demo.rxjavasample.R;

import java.util.ArrayList;

public class ColorMatrixView extends View {

    private final Context mContext;
    private Paint paint;
    private Bitmap bitmap;
    private Rect rect1;
    private ArrayList<ColorMatrixColorFilter> filters = new ArrayList<>();
    private int index = 0;
    private Paint textPaint;
    private ArrayList<String> filterType;

    public ColorMatrixView(Context context) {
        this(context, null);
    }

    public ColorMatrixView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorMatrixView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        paint = new Paint();
        textPaint = new Paint();
        textPaint.setTextSize(40);
        textPaint.setColor(Color.BLACK);
        ColorMatrix bluePipe = new ColorMatrix(new float[]{
//              R  G  B  A  饱和度
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 0, 1, 0,
        });
        ColorMatrix greenPipe = new ColorMatrix(new float[]{
                0, 0, 0, 0, 0,
                0, 1, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 1, 0,
        });
        ColorMatrix greenPipe_50 = new ColorMatrix(new float[]{
                1, 0, 0, 0, 0,
                0, 1, 0, 0, 50,
                0, 0, 1, 0, 50,
                0, 0, 0, 1, 0,
        });
        ColorMatrix redPipe = new ColorMatrix(new float[]{
                1, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 1, 0,
        });
        ColorMatrix inversePipe = new ColorMatrix(new float[]{
                -1, 0, 0, 0, 255,
                0, -1, 0, 0, 255,
                0, 0, -1, 0, 255,
                0, 0, 0, 1, 0,
        });
        ColorMatrix lightPipe_12 = new ColorMatrix(new float[]{
                1.2f, 0, 0, 0, 0,
                0, 1.2f, 0, 0, 0,
                0, 0, 1.2f, 0, 0,
                0, 0, 0, 1.2f, 0,
        });
        ColorMatrix grayPip = new ColorMatrix(new float[]{
                0.213f, 0.715f, 0.072f, 0, 0,
                0.213f, 0.715f, 0.072f, 0, 0,
                0.213f, 0.715f, 0.072f, 0, 0,
                0, 0, 0, 1, 0,
        });
        ColorMatrix r2gMatrix = new ColorMatrix(new float[]{
                0, 1, 0, 0, 0,
                1, 0, 0, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 0, 1, 0
        });
        ColorMatrix r2bMatrix = new ColorMatrix(new float[]{
                0, 0, 1, 0, 0,
                0, 1, 0, 0, 0,
                1, 0, 0, 0, 0,
                0, 0, 0, 1, 0
        });
        ColorMatrix g2bMatrix = new ColorMatrix(new float[]{
                1, 0, 0, 0, 0,
                0, 0, 1, 0, 0,
                0, 1, 0, 0, 0,
                0, 0, 0, 1, 0
        });
        ColorMatrixColorFilter blueFilter = new ColorMatrixColorFilter(bluePipe);
        ColorMatrixColorFilter greenFilter = new ColorMatrixColorFilter(greenPipe);
        ColorMatrixColorFilter greenFilter_50 = new ColorMatrixColorFilter(greenPipe_50);
        ColorMatrixColorFilter redFilter = new ColorMatrixColorFilter(redPipe);
        ColorMatrixColorFilter inverseFilter = new ColorMatrixColorFilter(inversePipe);
        ColorMatrixColorFilter lightFilter = new ColorMatrixColorFilter(lightPipe_12);
        ColorMatrixColorFilter grayFilter = new ColorMatrixColorFilter(grayPip);
        ColorMatrixColorFilter r2gFilter = new ColorMatrixColorFilter(r2gMatrix);
        ColorMatrixColorFilter r2bFilter = new ColorMatrixColorFilter(r2bMatrix);
        ColorMatrixColorFilter g2bFilter = new ColorMatrixColorFilter(g2bMatrix);
        bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.a);
        rect1 = new Rect(0, 0, 800, 800 * bitmap.getHeight() / bitmap.getWidth());
        filters.add(blueFilter);
        filters.add(greenFilter);
        filters.add(redFilter);
        filters.add(greenFilter_50);
        filters.add(inverseFilter);
        filters.add(lightFilter);
        filters.add(grayFilter);
        filters.add(r2gFilter);
        filters.add(r2bFilter);
        filters.add(g2bFilter);
        filterType = new ArrayList<String>() {{
            add("蓝色通道");
            add("绿色通道");
            add("红色通道");
            add("绿蓝饱和度+50");
            add("反色(底片效果)");
            add("亮度提升(1.2倍)");
            add("灰色图");
            add("红绿反色");
            add("红蓝反色");
            add("绿蓝反色");
        }};
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            index++;
            invalidate();
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.reset();
        paint.setAntiAlias(true);
        paint.setARGB(255, 200, 100, 100);
        canvas.drawRect(0, 0, 200, 200, paint);
        canvas.translate(250, 0);
        paint.setColorFilter(filters.get(index % filters.size()));
        canvas.drawRect(0, 0, 200, 200, paint);

        paint.reset();
        canvas.translate(-250, 250);
        canvas.drawBitmap(bitmap, null, rect1, paint);
        canvas.translate(0, 550);
        paint.setColorFilter(filters.get(index % filters.size()));
        canvas.drawBitmap(bitmap, null, rect1, paint);
        canvas.translate(0, 550);
        canvas.drawText(filterType.get(index % filterType.size()), 20, 20, textPaint);
    }
}
