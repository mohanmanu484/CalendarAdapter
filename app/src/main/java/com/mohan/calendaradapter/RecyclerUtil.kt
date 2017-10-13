package com.mohan.calendaradapter

import android.app.Activity
import android.graphics.Paint
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * Created by mohang on 10/10/17.
 */

 fun Activity.getLinearList(recyclerView: RecyclerView):RecyclerView{
    getRecyclerWithDecoration(recyclerView);
    recyclerView.layoutManager=LinearLayoutManager(this)
    return recyclerView
}

inline fun  getRecyclerWithDecoration(recyclerView: RecyclerView):RecyclerView{
    recyclerView.setHasFixedSize(true)
    //recyclerView.addItemDecoration(MyDecoration())
    val paint=Paint(Paint.ANTI_ALIAS_FLAG)
    paint.color=ContextCompat.getColor(recyclerView.context,R.color.accent_material_dark)
    paint.setStyle(Paint.Style.STROKE);
    paint.setStrokeWidth(4.0f);
    recyclerView.addItemDecoration(SimpleDividerItemDecoration())
    return recyclerView
}

fun Activity.getGridList(recyclerView: RecyclerView,spanCount:Int,orientation:Int,reverseLayout:Boolean):RecyclerView{

    getRecyclerWithDecoration(recyclerView)
    recyclerView.layoutManager=GridLayoutManager(this,spanCount,orientation,reverseLayout)
    return recyclerView

}

fun Activity.getGridList(recyclerView: RecyclerView,spanCount:Int):RecyclerView{

    getRecyclerWithDecoration(recyclerView);
    recyclerView.layoutManager=GridLayoutManager(this,spanCount)
    return recyclerView

}