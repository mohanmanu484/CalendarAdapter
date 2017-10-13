package com.mohan.calendaradapter;

import android.view.View;

/**
 * Created by mohang on 10/10/17.
 */

public interface ViewListener<T> {

    void onViewClick(View view,T data,int pos);
}
