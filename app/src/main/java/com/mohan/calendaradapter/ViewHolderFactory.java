package com.mohan.calendaradapter;

import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mohan.calendaradapter.adapter.GenericViewHolder;

import java.util.Date;

/**
 * Created by mohang on 9/10/17.
 */

public class ViewHolderFactory {

    @NonNull
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

    public static GenericViewHolder createRatingViewHolder(View view){
        return new RatingViewHolder(view);
    }

    private static class RatingViewHolder extends GenericViewHolder<String> {

        TextView ratingItem;
        LinearLayout ratingRoot;
        public RatingViewHolder(View itemView) {
            super(itemView);
            ratingItem=itemView.findViewById(R.id.tvRating);
            ratingRoot=itemView.findViewById(R.id.ratingRoot);
        }


        @Override
        public void setData(String data,int pos) {
            if(pos<6) {
                ratingRoot.setBackgroundResource(R.drawable.background_rating_red);
            }else if(pos<8){
                ratingRoot.setBackgroundResource(R.drawable.background_rating_yellow);
            }else {
                ratingRoot.setBackgroundResource(R.drawable.background_rating_green);
            }
            ratingItem.setText(data);
            ratingRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ratingRoot.setBackgroundColor(Color.GREEN);
                }
            });
        }
    }

    public interface OnDateClickListener{
        void onDateClick(Date date);
    }

}
