package com.mohan.calendaradapter.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by mohang on 9/10/17.
 */

public abstract class GenericViewholder<T> extends RecyclerView.ViewHolder implements Binder<T>{
    protected GenericViewholder(View itemView) {
        super(itemView);
    }
}
