package com.mohan.calendaradapter.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mohan.calendaradapter.R;

/**
 * Created by mohang on 12/10/17.
 */

public class NewRatingBar extends LinearLayout {

    private RatingListener ratingListener;
    LinearLayout viewGroup;
    int mDirtyCount=0;

    public void init(){

        setWeightSum(10);

       inflate(getContext(), R.layout.rating_bar_layout,this);
       viewGroup=findViewById(R.id.ratingRoot);
       initListeners();
    }

    private void greyOutBackground(){
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            TextView child= (TextView) viewGroup.getChildAt(i);
            child.setBackgroundResource(R.drawable.background_rating_grey);
        }
    }

    private void initListeners() {
       // LinearLayout viewGroup=findViewById(R.id.ratingRoot);
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            viewGroup.getChildAt(i).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mDirtyCount++<100) {
                        if (ratingListener != null) {
                            int pos = Integer.valueOf((String) view.getTag());
                         //   greyOutBackground();
                            if(view instanceof CommonRatingView) {
                                ((CommonRatingView) view).setSelectedState(CommonRatingView.States.SELECTED);
                                //((CommonRatingView) view).setBorderColor(CommonRatingView.States.SELECTED);
                            }
                            ratingListener.onRatingSelect(pos);
                        }
                    }
                }
            });

        }
    }

    private int getColorForPos(int pos){
        if(pos<7){
            return Color.RED;
        }else if(pos<9){
            return Color.YELLOW;
        }
        return Color.GREEN;
    }


    public void setRatingListener(RatingListener ratingListener) {
        this.ratingListener = ratingListener;
    }

    public interface RatingListener{
        void onRatingSelect(int rate);
    }


    public NewRatingBar(Context context) {
        super(context);
        init();
    }

    public NewRatingBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NewRatingBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

}
