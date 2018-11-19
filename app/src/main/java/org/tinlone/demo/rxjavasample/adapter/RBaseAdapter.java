package org.tinlone.demo.rxjavasample.adapter;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 简易RecyclerView Adapter
 *
 * @param <DATA> 数据类型
 * @param <VH>   ViewHolder
 */
public abstract class RBaseAdapter<DATA, VH extends RBaseAdapter.BaseHolder> extends RecyclerView.Adapter<VH> {

    protected List<DATA> mData;
    protected Context mContext;

    private int mLayoutId;
    private OnItemClickListener mListener = position -> {
    };

    public RBaseAdapter(@LayoutRes int layoutId, @NonNull List<DATA> data) {
        mData = data;
        mLayoutId = layoutId;
    }

    public void setLayoutId(@LayoutRes int layoutId) {
        mLayoutId = layoutId;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext == null)
            mContext = parent.getContext();
        return (VH) new BaseHolder(LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.mItemView.setOnClickListener(v -> mListener.onClick(position));
        onBindView(holder, position);
    }

    protected abstract void onBindView(@NonNull VH holder, int position);

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setOnItemClickListener(@NonNull OnItemClickListener listener) {
        mListener = listener;
    }

    /**
     * 增添数据
     *
     * @param data 数据
     */
    public void addData(@NonNull List<DATA> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 插入数据
     *
     * @param insertIndex 插入点
     * @param data        数据
     */
    public void insertData(@IntRange(from = 0) int insertIndex, @NonNull List<DATA> data) {
        mData.addAll(insertIndex, data);
        notifyDataSetChanged();
    }

    /**
     * 移除单条数据
     *
     * @param index index
     * @return 被移除数据
     */
    public DATA remove(@IntRange(from = 0) int index) {
        if (index >= mData.size()) {
            return null;
        }
        DATA remove = mData.remove(index);
        notifyDataSetChanged();
        return remove;
    }

    /**
     * @param start 开始index
     * @param end   结束index
     * @return 被移除数据
     */
    public List<DATA> remove(@IntRange(from = 0) int start, @IntRange(from = 0) int end) {
        start = start < 0 ? 0 : start;
        end = end >= mData.size() ? mData.size() - 1 : end;
        ArrayList<DATA> data = new ArrayList<>();
        for (int i = start; i < end; i++) {
            data.add(mData.remove(i));
        }
        notifyDataSetChanged();
        return data;
    }

    public static class BaseHolder extends RecyclerView.ViewHolder {

        View mItemView;

        public BaseHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
        }

        public final View getView(@IdRes int id) {
            return mItemView.findViewById(id);
        }

        public final void setText(@IdRes int tvId, CharSequence text) {
            ((TextView) mItemView.findViewById(tvId)).setText(text);
        }

        public final void setTextColor(@IdRes int tvId, @ColorInt int color) {
            ((TextView) mItemView.findViewById(tvId)).setTextColor(color);
        }

    }

    public interface OnItemClickListener {
        void onClick(int position);
    }

}
