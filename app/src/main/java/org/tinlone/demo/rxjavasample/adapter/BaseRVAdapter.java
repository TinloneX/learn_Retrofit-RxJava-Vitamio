package org.tinlone.demo.rxjavasample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjl on 2017/3/31 0031.
 */

public abstract class BaseRVAdapter<DATA, T extends BaseRVAdapter.BaseViewHolder> extends RecyclerView.Adapter<T> {

    private List<DATA> datas;
    public Context mContext;

    public BaseRVAdapter(Context context, List<DATA> datas) {
        super();
        this.mContext = context;
        this.datas = (datas == null) ? (List<DATA>) new ArrayList<>() : datas;
    }

    public List<DATA> getDatas(){
        return datas==null?  new ArrayList<DATA>() :datas;
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public T onCreateViewHolder(ViewGroup parent, int viewType) {
        return this.onCreateViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    public abstract T onCreateViewHolder(LayoutInflater inflater, ViewGroup parent);

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        viewBindData((T) holder, datas.get(position));
    }

    public abstract void viewBindData(T holder, DATA data);

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static abstract class BaseViewHolder extends RecyclerView.ViewHolder {

        public BaseViewHolder(View itemView) {
            super(itemView);
        }
    }

}
