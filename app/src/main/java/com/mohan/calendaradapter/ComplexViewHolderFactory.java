package com.mohan.calendaradapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;

import com.mohan.calendaradapter.adapter.GenericViewHolder;

/**
 * Created by mohang on 9/10/17.
 */

public class ComplexViewHolderFactory {

    @NonNull
    public static GenericViewHolder create(View view, @LayoutRes int viewtype){

        return new AnnoationViewHolder(view);
    }

}
