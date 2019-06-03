package org.tinlone.demo.rxjavasample.widget.base;


import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author tinlone
 * @date 2017/10/25 0025.
 */

public class PathView extends View {
    private Paint paint;
    private Context mContext;
    private Path path;
    private Path cwRectPath;
    private RectF rect1;
    private RectF rect2;
    private float[] radii;
    private Path pathc;
    private Path patho;
    private Path patha;
    private float[] pos;

    public PathView(Context context) {
        this(context, null);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        path = new Path();
        path.moveTo(100, 100);
        path.lineTo(100, 400);
        path.lineTo(300, 400);
        path.close();

        // CW顺时针 CCW逆时针绘制路径
        cwRectPath = new Path();
        RectF rectF = new RectF(350, 250, 540, 400);
        cwRectPath.addRect(rectF, Path.Direction.CCW);

        rect1 = new RectF(650, 50, 740, 300);

        rect2 = new RectF(790, 50, 880, 200);
        radii = new float[]{10, 15, 20, 25, 30, 35, 40, 45};

        pathc = new Path();

        pathc.addCircle(200, 250, 100, Path.Direction.CCW);

        patho = new Path();
        RectF rect = new RectF(750, 450, 640, 200);
        patho.addOval(rect, Path.Direction.CCW);
        patha = new Path();
        // RectF recta = new RectF(50, 50,240, 200);
        patha.addArc(rect2, 0, 100);
        pos = new float[]{10, 1200,
                110, 1220,
                200, 1200,
                310, 1220,
                400, 1200,
                510, 1220,
                600, 1200,
                710, 1220,
                800, 1200,
                910, 1200,
                1000, 1220
        };
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        paint.setColor(Color.RED);
        //绘制线
        canvas.drawPath(path, paint);
        // 矩形路径
        canvas.drawPath(cwRectPath, paint);
        String text = "Hello,World!Hello,You!";
        paint.setColor(Color.GREEN);
        paint.setTextSize(40);
        //将文本绘制在路径上，文字方向与路径方向一致
        canvas.drawTextOnPath(text, cwRectPath, 0, 20, paint);
        //绘制圆角（等角）矩形
        paint.setColor(Color.RED);
        path.addRoundRect(rect1, 10, 15, Path.Direction.CCW);
        //绘制圆角（偏角）矩形
        paint.setColor(Color.BLUE);
        path.addRoundRect(rect2, radii, Path.Direction.CCW);
        canvas.drawPath(path, paint);
        //绘制圆形路径
        canvas.drawPath(pathc, paint);
        // 绘制椭圆路径
        paint.setColor(Color.GREEN);
        canvas.drawPath(patho, paint);
        //绘制弧形路径
        paint.setColor(Color.RED);
        canvas.drawPath(patha, paint);

        paint.setColor(Color.DKGRAY);
        canvas.drawLine(0, 500, width, 500, paint);

        //设置画笔宽度
        paint.setStrokeWidth(5);
        //指定是否使用抗锯齿功能，如果使用，会使绘图速度变慢
        paint.setAntiAlias(true);
        //设置文字大小
        paint.setTextSize(80);

        //绘图样式，设置为填充
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText("Hello,World! Hello,You!(FILL)", 10, 600, paint);

        //绘图样式设置为描边
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawText("Hello,World! Hello,You!(STROKE)", 10, 700, paint);

        //绘图样式设置为填充且描边
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawText("Hello,World!(FILL_AND_STROKE)", 10, 800, paint);
        //右倾角 （-0.25）
        paint.setTextSkewX(-0.25f);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText("Hello,World! Hello,You!(-0.25FILL)", 10, 900, paint);
        //左倾角   （0.25）
        paint.setTextSkewX(0.25f);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawText("Hello,World! Hello,You! (0.25STROKE)", 10, 1000, paint);
        paint.setTextScaleX(1.25f);
        canvas.drawText("Hello,World! (1.25ScaleX)", 10, 1100, paint);
        paint.setTextScaleX(0f);
        paint.setTextSkewX(0f);

        canvas.drawPosText("Hello,World", pos, paint);
        String familyName = "宋体";
        Typeface font = Typeface.create(familyName, Typeface.NORMAL);
        paint.setTypeface(font);
        canvas.drawText("宋体字,Hello,World", 10, 1300, paint);
        //得到AssetManager
        AssetManager mgr = mContext.getAssets();
        //根据路径得到Typeface
        Typeface typeface = Typeface.createFromAsset(mgr, "xtr.ttf");
        paint.setTypeface(typeface);
        paint.setTextSize(130f);
        canvas.drawText("新唐人简篆体", 10, 1500, paint);
    }
}
