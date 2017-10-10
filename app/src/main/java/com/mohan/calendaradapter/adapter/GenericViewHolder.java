package com.mohan.calendaradapter.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by mohang on 9/10/17.
 */

public abstract class GenericViewHolder<T> extends RecyclerView.ViewHolder implements Binder<T>{
    protected GenericViewHolder(View itemView) {
        super(itemView);
    }
}
