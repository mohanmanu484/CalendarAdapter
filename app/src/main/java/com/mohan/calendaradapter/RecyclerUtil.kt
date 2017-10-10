package com.mohan.calendaradapter

import android.app.Activity
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
    recyclerView.addItemDecoration(MyDecoration())
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