package org.tinlone.demo.rxjavasample.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.tinlone.demo.rxjavasample.R;

import java.util.List;

/**
 *
 * 未完工
 * 
 * @author Administrator
 * @date 2017/12/18 0018
 */

public class MyWheelView extends ScrollView {

    private Context mContext;
    private int mTotalRow = 3;
    private LinearLayout mWrapper;
    private int mDefaultTextSize = 12;
    private int mSelectTextSize = 14;
    private int mDefaultTextColor = Color.DKGRAY;
    private int mSelectTextColor = Color.BLACK;
    private float mUnSelectAlpha = 0.9f;

    public MyWheelView(Context context) {
        this(context, null);
    }

    public MyWheelView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyWheelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        mContext = context;
        mWrapper = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.wheel_view_wrapper, this);
        mWrapper.setOrientation(LinearLayout.VERTICAL);
    }

    public MyWheelView setData(List<String> data, int position) {
        if (data != null && position >= 0) {
            mWrapper.removeAllViews();
            for (int i = 0; i < data.size(); i++) {
                mWrapper.addView(createItem(data.get(i), i, position));
            }
        }
        return this;
    }

    private TextView createItem(String data, int index, int position) {
        boolean select = index == position;
        boolean near = index == position + 1 || index == position - 1;
        TextView item = (TextView) LayoutInflater.from(mContext).inflate(R.layout.wheel_item, null);
        item.setText(data);
        item.setTextSize(select ? mSelectTextSize : mDefaultTextSize);
        item.setTextColor(select ? mSelectTextColor : mDefaultTextColor);
        item.setAlpha(select ? 1f : mUnSelectAlpha);
        item.setAlpha(near ? mUnSelectAlpha * mUnSelectAlpha : 1f);
        item.setTag(index);
        return item;
    }


}
