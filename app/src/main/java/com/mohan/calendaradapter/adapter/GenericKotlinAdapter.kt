package com.mohan.calendaradapter.adapter

import android.support.annotation.CallSuper
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by mohang on 9/10/17.
 */

abstract class GenericKotlinAdapter<T>(internal  val observableList: ObservableList<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), ObservableList.OnListChangedCallback<ObservableList<Any>> {

    init {
        if (observableList == null) {
            throw IllegalArgumentException("object list cannot be null")
        }
        observableList.addOnListChangedCallback(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<T> {
        return getViewHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false), viewType)
    }

    @CallSuper
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Binder<Any>).setData(observableList[position], position)
    }

    override fun getItemCount(): Int {
        return observableList.size
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutType(position, observableList[position])
    }


    @LayoutRes
    abstract fun getLayoutType(position: Int, `object`: Any): Int

    abstract fun getViewHolder(view: View, viewType: Int): GenericViewHolder<T>

    override fun onChanged(var1: ObservableList<Any>) {
        notifyDataSetChanged()
    }

    override fun onItemRangeChanged(var1: ObservableList<Any>, var2: Int, var3: Int) {
        notifyItemRangeChanged(var2, var3)
    }

    override fun onItemRangeInserted(var1: ObservableList<Any>, var2: Int, var3: Int) {
        notifyItemRangeInserted(var2, var3)
    }

    override fun onItemRangeMoved(var1: ObservableList<Any>, var2: Int, var3: Int, var4: Int) {

        // notifyItem
    }

    override fun onItemRangeRemoved(var1: ObservableList<Any>, var2: Int, var3: Int) {
        notifyItemRangeRemoved(var2, var3)
    }
}
