package org.tinlone.demo.rxjavasample.adapter;

import android.content.Context;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public abstract class TBaseAdapter<T> extends android.widget.BaseAdapter {

    /**
     * Context object
     */
    private Context context;
    /**
     * Data Source
     */
    private List<T> data;
    /**
     * for packaging XML file to View-Object
     */
    private LayoutInflater inflater;

    /**
     * Constructor with two param
     *
     * @param context Context object
     * @param data    data source
     */
    public TBaseAdapter(Context context, List<T> data) {
        super();
        setContext(context);
        setData(data);
        setLayoutInflater();
    }

    protected final Context getContext() {
        return context;
    }

    private final void setContext(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context object cannot be null!");
        }
        this.context = context;
    }

    private final void setLayoutInflater() {
        inflater = LayoutInflater.from(context);
    }

    protected final LayoutInflater getLayoutInflater() {
        return inflater;
    }

    protected final List<T> getData() {
        return data;
    }

    protected final void setData(List<T> data) {
        if (data == null) {
            data = new ArrayList<T>();
        }
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public interface DataWatcher<T>{
        void chooseData(T data, boolean selected);
    }

}
