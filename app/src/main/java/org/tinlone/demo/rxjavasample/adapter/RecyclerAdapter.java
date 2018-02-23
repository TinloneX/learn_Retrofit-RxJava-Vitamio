package org.tinlone.demo.rxjavasample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.tinlone.demo.rxjavasample.R;
import org.tinlone.demo.rxjavasample.bean.CallLogBean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class RecyclerAdapter extends RecyclerView.Adapter {


    private final Context mContext;
    List<CallLogBean> data;

    public RecyclerAdapter(Context context, List<CallLogBean> callLogBeen) {
        mContext = context;
        data = callLogBeen;
    }

    @Override
    public int getItemCount() {
        return data.size() + 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == -1) {
            return new HeaderHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_none_header, parent, false));
        } else {
            return new TextHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_text_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            return;
        }
        ((TextHolder) holder).mTextView.setText(data.get(position - 1).toString());
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return -1;
        }
        return 1;
    }

    static class TextHolder extends RecyclerView.ViewHolder {

        TextView mTextView;

        public TextHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_text);
        }
    }

    static class HeaderHolder extends RecyclerView.ViewHolder {

        public HeaderHolder(View itemView) {
            super(itemView);
        }
    }
}
