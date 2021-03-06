package org.tinlone.demo.rxjavasample.widget.base;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author tinlone
 * @date 2017/10/25 0025.
 */

public class RegionView extends View {

    private Paint mPaint;
    private RegionIterator iterator1;
    private Rect rect;
    private RegionIterator iterator2;
    private Rect rect2;
    private RegionIterator iterator3;
    private Rect rect3;
    private Rect rect4;
    private Rect rect5;
    private Region region;
    private Region region4;
    private RegionIterator regionIterator;

    public RegionView(Context context) {
        this(context, null);
    }

    public RegionView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RegionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        Region region1 = new Region(10, 10, 10, 100);
        region1.set(100, 100, 200, 200);
        iterator1 = new RegionIterator(region1);
        rect = new Rect();
        rect2 = new Rect();
        rect3 = new Rect();
        RectF rectF = new RectF(200, 200, 250, 400);
        Path path = new Path();
        path.addOval(rectF, Path.Direction.CCW);
        Region region2 = new Region();
        Region region3 = new Region();
        region2.setPath(path, new Region(200, 200, 250, 300));
        region3.setPath(path, new Region(200, 200, 250, 500));
        iterator2 = new RegionIterator(region2);
        iterator3 = new RegionIterator(region3);

        rect4 = new Rect(300, 500, 600, 600);
        rect5 = new Rect(400, 400, 500, 700);
        region = new Region(rect4);
        region4 = new Region(rect5);
        regionIterator = new RegionIterator(region);
        rect4 = new Rect();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(2);
        while (iterator1.next(rect)) {
            canvas.drawRect(rect, mPaint);
        }
        mPaint.setColor(Color.RED);
        while (iterator2.next(rect2)) {
            canvas.drawRect(rect2, mPaint);
        }
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        while (iterator3.next(rect3)) {
            canvas.drawRect(rect3, mPaint);
        }

        canvas.drawRect(rect4, mPaint);
        canvas.drawRect(rect5, mPaint);

//        region.op(region2, Region.Op.INTERSECT);
        region.op(region4, Region.Op.DIFFERENCE);
//        region.op(region2, Region.Op.REPLACE);
//        region.op(region2, Region.Op.REVERSE_DIFFERENCE);
//        region.op(region2, Region.Op.UNION);
//        region.op(region2, Region.Op.XOR);
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.FILL);

        while (regionIterator.next(rect4)) {
            canvas.drawRect(rect4, mPaint);
        }


    }
}
