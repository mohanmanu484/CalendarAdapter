package com.mohan.calendaradapter;

import java.util.List;

public interface ObservableList<T> extends List<T> {
    void addOnListChangedCallback(ObservableList.OnListChangedCallback<? extends ObservableList<T>> var1);

   // void removeOnListChangedCallback(ObservableList.OnListChangedCallback<? extends ObservableList<T>> var1);

    public interface  OnListChangedCallback<T extends ObservableList> {

        public abstract void onChanged(T var1);

        public abstract void onItemRangeChanged(T var1, int var2, int var3);

        public abstract void onItemRangeInserted(T var1, int var2, int var3);

        public abstract void onItemRangeMoved(T var1, int var2, int var3, int var4);

        public abstract void onItemRangeRemoved(T var1, int var2, int var3);
    }
}