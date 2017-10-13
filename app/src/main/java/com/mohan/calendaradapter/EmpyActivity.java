package com.mohan.calendaradapter;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.mohan.calendaradapter.adapter.ObservableArrayList;
import com.mohan.calendaradapter.adapter.ObservableList;
import com.mohan.calendaradapter.adapter.RatingBar;

/**
 * Created by mohang on 9/10/17.
 */

public class EmpyActivity extends AppCompatActivity {

    ObservableList<Object> observableList=new ObservableArrayList<>();
    Handler handler=new Handler();
    private int count;
    public static final String TAG = "EmpyActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RatingBar ratingBar=findViewById(R.id.ratingBar);
        ratingBar.setRatingListener(new RatingBar.RatingListener() {
            @Override
            public void onRatingSelect(int rate) {
                Log.d(TAG, "onRatingSelect: "+rate);
            }
        });

      /*  RecyclerView recyclerView=findViewById(R.id.observableList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,10));
        Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4.0f);
       // recyclerView.addItemDecoration(new SimpleDividerItemDecoration());
        observableList.add("1");
        observableList.add("2");
        observableList.add("3");
        observableList.add("4");
        observableList.add("5");
        observableList.add("6");
        observableList.add("7");
        observableList.add("8");
        observableList.add("9");
        observableList.add("10");

      /*  recyclerView.setAdapter(new GenericAdapter(observableList) {
            @Override
            public int getLayoutType(int position, Object object) {
                return R.layout.adapter_rating_item;
            }
            @Override
            public GenericViewHolder getViewHolder(final View view, int viewType) {
                return ViewHolderFactory.createRatingViewHolder(view);
            }
        });*//*
*/





    }

    public void  resetBackground(){
        LinearLayout linearLayout=findViewById(R.id.ratingBar);

        for (int i = 0; i < 10; i++) {

            linearLayout.getChildAt(i).setBackgroundResource(R.drawable.background_rating_grey);
        }
    }

    public void remove(View view){
        observableList.clear();
    }

    public void add(View view){
        observableList.add("New");
    }
}
