package com.mohan.calendaradapter;

import java.util.List;

public interface ObservableList<T> extends List<T> {
    void addOnListChangedCallback(ObservableList.OnListChangedCallback<? extends ObservableList<T>> var1);

     interface  OnListChangedCallback<T extends ObservableList> {

         void onChanged(T var1);

         void onItemRangeChanged(T var1, int var2, int var3);

         void onItemRangeInserted(T var1, int var2, int var3);

         void onItemRangeMoved(T var1, int var2, int var3, int var4);

         void onItemRangeRemoved(T var1, int var2, int var3);
    }
}