package org.tinlone.demo.rxjavasample.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.tinlone.demo.rxjavasample.R;

import java.util.List;
import java.util.Random;


/**
 *
 * @author Administrator
 * @date 2017/7/27 0027
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyVH> {
    private final Random mRandom;
    private Context mContext;
    private List<String> urls;
    private List<Class> activities;

    public MyAdapter(Context context, List<String> urls,List<Class> activities) {
        mContext = context;
        this.urls = urls;
        this.activities = activities;
        mRandom = new Random();
    }

    @Override
    public MyVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyVH(LayoutInflater.from(mContext).inflate(R.layout.item1, parent, false));
    }

    @Override
    public void onBindViewHolder(final MyVH holder, @SuppressLint("RecyclerView") final int position) {
        final String text = urls.get(position) +" to "+ activities.get(position).getSimpleName();
        int r1 = mRandom.nextInt(255);
        int r2 = 255 - r1;
        int g1 = mRandom.nextInt(255);
        int g2 = 255 - g1;
        int b1 = mRandom.nextInt(255);
        int b2 = 255 - b1;
        int bg = Color.rgb(r1,g1,b1);
        int tc = Color.rgb(r2,g2,b2);
        holder.itemView.setBackgroundColor(bg);
        holder.tv2.setTextColor(tc);
        holder.tv2.setText(text);
        holder.tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, activities.get(position)));
            }
        });

    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

    static class MyVH extends RecyclerView.ViewHolder {

        private TextView tv2;

        MyVH(View itemView) {
            super(itemView);
            tv2 = (TextView) itemView.findViewById(R.id.tv2);
        }

    }

}
