package org.tinlone.demo.rxjavasample.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;

import org.tinlone.demo.rxjavasample.R;
import org.tinlone.demo.rxjavasample.activity.view.ViewATestActivity;
import org.tinlone.demo.rxjavasample.config.Configs;

import java.util.List;
import java.util.Random;

/**
 * @author Administrator
 * @date 2017/7/27 0027
 */
public class MyAdapter extends RBaseAdapter<String, RBaseAdapter.BaseHolder> {
    private final Random mRandom;
    private List<String> urls;

    public MyAdapter() {
        super(R.layout.item1, Configs.TITLES);
        this.urls = Configs.TITLES;
        mRandom = new Random();
    }

    @Override
    protected void onBindView(@NonNull BaseHolder holder, int position) {
        final String text = urls.get(position) + Configs.VIEW_NAME.get(position);
        int r1 = mRandom.nextInt(255);
        int g1 = mRandom.nextInt(255);
        int b1 = mRandom.nextInt(255);
        int bg = Color.rgb(r1, g1, b1);
        int tc = Color.rgb(255 - r1, 255 - g1, 255 - b1);
        holder.itemView.setBackgroundColor(bg);
        holder.setTextColor(R.id.tv2, tc);
        holder.setText(R.id.tv2, text);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, ViewATestActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("index", position);
            intent.putExtras(bundle);
            mContext.startActivity(intent);
        });
    }

}
