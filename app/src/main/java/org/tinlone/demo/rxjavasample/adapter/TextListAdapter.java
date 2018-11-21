package org.tinlone.demo.rxjavasample.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;

import org.tinlone.demo.rxjavasample.R;
import org.tinlone.demo.rxjavasample.config.Configs;

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
        super(R.layout.item1, Configs.VIEW_TITLES);
        mRandom = new Random();
        mContents = Configs.VIEW_NAMES;
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

    @Override
    protected void onBindView(@NonNull BaseHolder holder, int position) {
        final String text = mData.get(position) + (mContents == null ? "" : mContents.get(position));
        int r1 = mRandom.nextInt(256);
        int g1 = mRandom.nextInt(256);
        int b1 = mRandom.nextInt(256);
        int bg = Color.rgb(r1, g1, b1);
        int tc = Color.rgb(255 - r1, 255 - g1, 255 - b1);
        holder.itemView.setBackgroundColor(bg);
        holder.setTextColor(R.id.tv2, tc);
        holder.setText(R.id.tv2, text);
    }

}
