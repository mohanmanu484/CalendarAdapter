package com.mohan.calendaradapter;

import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by mohang on 9/10/17.
 */

public  abstract class GenericAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Object> objectList;

    public GenericAdapter(List<Object> objectList) {
        this.objectList = objectList;
    }
    public void setData(List<Object> objects){
        objectList.clear();
        objectList.addAll(objects);
        notifyDataSetChanged();

    }

    @Override
    public GenericViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(parent.getContext()).inflate(viewType,parent,false),viewType);
    }

    @CallSuper
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((Binder)holder).setData(objectList.get(position));
    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutType(position,objectList.get(position));
    }


    public abstract  @LayoutRes int getLayoutType(int position,Object object);
    public abstract GenericViewholder<T> getViewHolder(View view,int viewType);
}
