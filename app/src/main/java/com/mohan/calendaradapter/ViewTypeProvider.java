package com.mohan.calendaradapter;

import android.support.annotation.LayoutRes;

/**
 * Created by mohang on 9/10/17.
 */

public interface ViewTypeProvider  {
    @LayoutRes
    int getLayoutType(int position,Object object);
}
