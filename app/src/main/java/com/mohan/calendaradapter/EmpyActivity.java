package com.mohan.calendaradapter;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mohan.calendaradapter.adapter.GenericAdapter;
import com.mohan.calendaradapter.adapter.GenericViewholder;

import java.util.Date;

/**
 * Created by mohang on 9/10/17.
 */

public class EmpyActivity extends AppCompatActivity {

    ObservableList<Object> observableList=new ObservableArrayList<>();
    Handler handler=new Handler();
    private int count;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.observable_layout);

        RecyclerView recyclerView=findViewById(R.id.observableList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,8));
        observableList.add(new Date());
        recyclerView.setAdapter(new GenericAdapter(observableList) {
            @Override
            public int getLayoutType(int position, Object object) {
                return R.layout.adapter_date_type;
            }

            @Override
            public GenericViewholder getViewHolder(final View view, int viewType) {
                return ViewHolderFactory.create(view,viewType);
            }
        });

    }

    public void remove(View view){
        observableList.clear();
    }
}
