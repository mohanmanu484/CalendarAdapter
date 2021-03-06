package com.mohan.calendaradapter;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.TextView;

import com.mohan.calendaradapter.adapter.GenericViewHolder;

import java.util.Date;

/**
 * Created by mohang on 9/10/17.
 */

public class ViewHolderFactory {

    public static GenericViewHolder create(View view, @LayoutRes int viewtype){

        if(viewtype==R.layout.adapter_date_type) {
            return new MyViewHolder(view);
        }
        if(viewtype==R.layout.adapter_price_type){
            return new PriceViewHolder(view);
        }
        if(viewtype==R.layout.adapter_empty_type){
            return new EmptyViewHolder(view);
        }
        throw new IllegalArgumentException("unsupported view type "+viewtype);
    }

    private static class MyViewHolder extends GenericViewHolder<Date> {
        TextView weekDay;
        TextView date;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.weekDay=itemView.findViewById(R.id.tvWeekDay);
            this.date=itemView.findViewById(R.id.tvDate);
        }

        @Override
        public void setData(final Date data,int pos) {
            weekDay.setText(MyUtil.getWeekDayString(data));
            date.setText(MyUtil.getDateAndMonthString(data));
        }

    }

    private static class PriceViewHolder extends GenericViewHolder<String> {

        TextView priceData;

        public PriceViewHolder(View itemView) {
            super(itemView);
            this.priceData=itemView.findViewById(R.id.tvPrice);
        }


        @Override
        public void setData(String data,int pos) {
            priceData.setText(data);
        }
    }

    private static class EmptyViewHolder extends GenericViewHolder<Empty> {

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }


        @Override
        public void setData(Empty data,int pos) {

        }
    }

    public interface OnDateClickListener{
        void onDateClick(Date date);
    }

}
