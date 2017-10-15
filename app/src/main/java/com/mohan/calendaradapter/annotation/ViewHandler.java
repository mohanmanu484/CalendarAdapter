package com.mohan.calendaradapter.annotation;

import android.view.View;

/**
 * Created by mohang on 15/10/17.
 */

public class ViewHandler {

    public void bindBookButtonListener(View view, final String data, final BookButtonClickListener bookButtonClickListener){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookButtonClickListener.onBookButtonClick(data);
            }
        });
    }
    public interface BookButtonClickListener{
        void onBookButtonClick(String data);
    }
}
