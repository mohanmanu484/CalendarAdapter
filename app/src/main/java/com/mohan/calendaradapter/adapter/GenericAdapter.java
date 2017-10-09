package com.mohan.calendaradapter.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mohan.calendaradapter.ObservableList;

/**
 * Created by mohang on 9/10/17.
 */

public abstract class GenericAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ObservableList.OnListChangedCallback<ObservableList<Object>> {

    ObservableList<Object> observableList;

    public GenericAdapter(ObservableList<Object> objectList) {
        this.observableList = objectList;
        observableList.addOnListChangedCallback(this);
    }

    @Override
    public GenericViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false), viewType);
    }

    @CallSuper
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((Binder) holder).setData(observableList.get(position),position);
    }

    @Override
    public int getItemCount() {
        return observableList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutType(position, observableList.get(position));
    }


    public abstract @LayoutRes
    int getLayoutType(int position, Object object);

    public abstract GenericViewholder<T> getViewHolder(View view, int viewType);

    @Override
    public void onChanged(ObservableList<Object> var1) {
        notifyDataSetChanged();
    }

    @Override
    public void onItemRangeChanged(ObservableList<Object> var1, int var2, int var3) {
        notifyItemRangeChanged(var2, var3);
    }

    @Override
    public void onItemRangeInserted(ObservableList<Object> var1, int var2, int var3) {
        notifyItemRangeInserted(var2, var3);
    }

    @Override
    public void onItemRangeMoved(ObservableList<Object> var1, int var2, int var3, int var4) {

        // notifyItem
    }

    @Override
    public void onItemRangeRemoved(ObservableList<Object> var1, int var2, int var3) {
        notifyItemRangeRemoved(var2, var3);
    }
}
