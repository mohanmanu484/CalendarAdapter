package com.mohan.calendaradapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by mohang on 5/10/17.
 */

public class MyDecoration extends RecyclerView.ItemDecoration {


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        outRect.left=2;
        outRect.right=2;
        outRect.top=2;
        outRect.bottom=2;


       // super.getItemOffsets(outRect, view, parent, state);
    }
}
