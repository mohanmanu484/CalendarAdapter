package com.mohan.calendaradapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mohang on 9/10/17.
 */

public class CalendarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    List<Object> calendarItems=new ArrayList<>();

    public CalendarAdapter(List<Object> calendarItems) {
        this.calendarItems.addAll(calendarItems);
    }

    public CalendarAdapter() {
    }

    public void setCalendarItems(List<Object> calendarItems) {
        this.calendarItems.clear();
        this.calendarItems.addAll(calendarItems);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

       View view= LayoutInflater.from(parent.getContext()).inflate(viewType,parent,false);

       RecyclerView.ViewHolder viewHolder;
       if(viewType==R.layout.adapter_date_type) {
           viewHolder=new DateViewHolder(view);
       }
       else if(viewType==R.layout.adapter_price_type){
           viewHolder=new PriceViewHolder(view);
       }
       else {
           viewHolder=new EmptyViewHolder(view);
       }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((Binder)holder).setData(calendarItems.get(position));
    }

    @Override
    public int getItemCount() {
        return calendarItems.size();
    }

    @Override
    public int getItemViewType(int position) {

        Object object=calendarItems.get(position);

        if(object instanceof Date){
            return R.layout.adapter_date_type;
        }
        if(object instanceof String){
            return R.layout.adapter_price_type;
        }
        if(object instanceof Empty){
            return R.layout.adapter_empty_type;
        }
        throw new IllegalArgumentException(object.getClass()+" is not supported for calendar");
    }

    public static class PriceViewHolder extends RecyclerView.ViewHolder implements Binder<String>{

        TextView priceData;

        public PriceViewHolder(View itemView) {
            super(itemView);
            this.priceData=itemView.findViewById(R.id.tvPrice);
        }


        @Override
        public void setData(String data) {
            priceData.setText(data);
        }
    }
    public static class DateViewHolder extends RecyclerView.ViewHolder implements Binder<Date>{

        TextView weekDay;
        TextView date;
        public DateViewHolder(View itemView) {
            super(itemView);
            this.weekDay=itemView.findViewById(R.id.tvWeekDay);
            this.date=itemView.findViewById(R.id.tvDate);
        }

        @Override
        public void setData(Date data) {
            weekDay.setText(MyUtil.getWeekDayString(data));
            date.setText(MyUtil.getDateAndMonthString(data));
        }
    }

    public static class EmptyViewHolder extends RecyclerView.ViewHolder implements Binder<Empty>{

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }


        @Override
        public void setData(Empty data) {

        }
    }

     interface Binder<T>{
        void setData(T data);
    }



}
