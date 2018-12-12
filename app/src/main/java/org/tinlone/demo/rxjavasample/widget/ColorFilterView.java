package org.tinlone.demo.rxjavasample.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import org.tinlone.demo.rxjavasample.R;

import java.util.ArrayList;


public class ColorFilterView extends View {
    private final Context mContext;
    private Paint paint;
    private Paint textPaint;
    private ArrayList<ColorFilter> filters;
    private ArrayList<String> infos;
    private Bitmap bitmap;
    private Rect rect;
    private int index = 0;

    public ColorFilterView(Context context) {
        this(context, null);
    }

    public ColorFilterView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        paint = new Paint();
        textPaint = new Paint();
        textPaint.setTextSize(40);
        textPaint.setColor(Color.BLACK);
        textPaint.setStyle(Paint.Style.FILL);
        filters = new ArrayList<>();
        bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.a);
        rect = new Rect(0, 0, 600, 600 * bitmap.getHeight() / bitmap.getWidth());
        LightingColorFilter lcFilter1 = new LightingColorFilter(0x00ff00, 0x000000);
        LightingColorFilter lcFilter2 = new LightingColorFilter(0xffffff, 0x0000f0);
        PorterDuffColorFilter pdFilter_mul = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
        PorterDuffColorFilter pdFilter_clr = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.CLEAR);
        PorterDuffColorFilter pdFilter_src = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.SRC);
        PorterDuffColorFilter pdFilter_srcin = new PorterDuffColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
        PorterDuffColorFilter pdFilter_srcout = new PorterDuffColorFilter(Color.GREEN, PorterDuff.Mode.SRC_OUT);
        PorterDuffColorFilter pdFilter_srcovr = new PorterDuffColorFilter(Color.GREEN, PorterDuff.Mode.SRC_OVER);
        PorterDuffColorFilter pdFilter_dst = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.DST);
        PorterDuffColorFilter pdFilter_scr = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.SCREEN);
        PorterDuffColorFilter pdFilter_add = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.ADD);
        PorterDuffColorFilter pdFilter_dkn = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.DARKEN);
        PorterDuffColorFilter pdFilter_dstatop = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.DST_ATOP);
        PorterDuffColorFilter pdFilter_dstin = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.DST_IN);
        PorterDuffColorFilter pdFilter_dstout = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.DST_OUT);
        PorterDuffColorFilter pdFilter_dstover = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.DST_OVER);
        PorterDuffColorFilter pdFilter_lghtn = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.LIGHTEN);
        PorterDuffColorFilter pdFilter_ovly = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.OVERLAY);
        PorterDuffColorFilter pdFilter_xor = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.XOR);
        infos = new ArrayList<String>() {{
            add("LightingColorFilter -> 仅保留绿色");
            add("LightingColorFilter -> 仅强化蓝色");
            add("图形混合滤镜 -> 红色 -> MULTIPLY");
            add("图形混合滤镜 -> 红色 -> CLEAR");
            add("图形混合滤镜 -> 红色 -> SRC");
            add("图形混合滤镜 -> 绿色 -> SRC_IN");
            add("图形混合滤镜 -> 绿色 -> SRC_OUT");
            add("图形混合滤镜 -> 绿色 -> SRC_OVER");
            add("图形混合滤镜 -> 红色 -> DST");
            add("图形混合滤镜 -> 红色 -> SCREEN");
            add("图形混合滤镜 -> 红色 -> ADD");
            add("图形混合滤镜 -> 红色 -> DARKEN");
            add("图形混合滤镜 -> 红色 -> DST_ATOP");
            add("图形混合滤镜 -> 红色 -> DST_IN");
            add("图形混合滤镜 -> 红色 -> DST_OUT");
            add("图形混合滤镜 -> 红色 -> DST_OVER");
            add("图形混合滤镜 -> 红色 -> LIGHTEN");
            add("图形混合滤镜 -> 红色 -> OVERLAY");
            add("图形混合滤镜 -> 红色 -> XOR");
        }};
        filters.add(lcFilter1);
        filters.add(lcFilter2);
        filters.add(pdFilter_mul);
        filters.add(pdFilter_clr);
        filters.add(pdFilter_src);
        filters.add(pdFilter_srcin);
        filters.add(pdFilter_srcout);
        filters.add(pdFilter_srcovr);
        filters.add(pdFilter_dst);
        filters.add(pdFilter_scr);
        filters.add(pdFilter_add);
        filters.add(pdFilter_dkn);
        filters.add(pdFilter_dstatop);
        filters.add(pdFilter_dstin);
        filters.add(pdFilter_dstout);
        filters.add(pdFilter_dstover);
        filters.add(pdFilter_lghtn);
        filters.add(pdFilter_ovly);
        filters.add(pdFilter_xor);
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
        canvas.drawBitmap(bitmap, null, rect, paint);
        canvas.translate(0, 450);
        paint.setColorFilter(filters.get(index % filters.size()));
        canvas.drawBitmap(bitmap, null, rect, paint);
        canvas.translate(0, 450);
        canvas.drawText(infos.get(index % infos.size()), 50, 20, textPaint);
    }
}
