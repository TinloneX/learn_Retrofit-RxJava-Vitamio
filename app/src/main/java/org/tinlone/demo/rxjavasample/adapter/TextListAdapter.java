package org.tinlone.demo.rxjavasample.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.widget.TextView;

import org.tinlone.demo.rxjavasample.R;
import org.tinlone.demo.rxjavasample.activity.view.ViewListFactory;

import java.util.List;
import java.util.Random;

/**
 * @author Administrator
 * @date 2017/7/27 0027
 */
public class TextListAdapter extends RBaseAdapter<String, RBaseAdapter.BaseHolder> {
    private final Random mRandom;
    private List<String> mContents;

    public TextListAdapter() {
        super(R.layout.item1, ViewListFactory.VIEW_TITLES);
        mRandom = new Random();
        mContents = ViewListFactory.VIEW_NAMES;
    }

    public TextListAdapter(List<String> titles) {
        super(R.layout.item1, titles);
        mRandom = new Random();
    }

    public TextListAdapter(List<String> titles, List<String> contents) {
        super(R.layout.item1, titles);
        mRandom = new Random();
        mContents = contents;
    }

    private static String hex(int number) {
        StringBuilder builder = new StringBuilder(
                Integer.toHexString(number & 0xff));
        while (builder.length() < 2) {
            builder.append("0");
        }
        return builder.toString().toUpperCase();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onBindView(@NonNull BaseHolder holder, int position) {
        String text = mData.get(position) + (mContents == null ? "" : mContents.get(position));
        TextView tv2 = (TextView) holder.getView(R.id.tv2);
        int r1 = mRandom.nextInt(256);
        int g1 = mRandom.nextInt(256);
        int b1 = mRandom.nextInt(256);
        int bg = Color.rgb(r1, g1, b1);
        text += String.format("\nBackgroundColor:#%s%s%s", hex(r1), hex(g1), hex(b1));
        int tc = Color.rgb(inv(r1), inv(g1), inv(b1));
        text += String.format("\tTextColor:#%s%s%s", hex(inv(r1)), hex(inv(g1)), hex(inv(b1)));
        holder.itemView.setBackgroundColor(bg);
        tv2.setTextColor(tc);
        tv2.setText(text);
    }

    private int inv(int co) {
        return 255 - co;
    }
}
