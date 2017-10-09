package com.mohan.calendaradapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mohang on 9/10/17.
 */

public class EmpyActivity extends AppCompatActivity {

    List<Object> objectList=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridlayout);

        RecyclerView recyclerView=findViewById(R.id.rvVerticalDates);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,8));

        objectList.add(new Empty());
        objectList.add(new Empty());
        objectList.add(new Date());
        objectList.add(new Date());
        objectList.add(new Date());
        objectList.add("Mon");
        objectList.add(new Date());
        objectList.add(new Date());
        objectList.add(new Date());
        objectList.add(new Date());
        objectList.add(new Date());
        objectList.add(new Date());
        objectList.add(new Date());
        objectList.add(new Date());
        objectList.add(new Date());
        objectList.add("Mon");
        objectList.add("Mon");
        objectList.add("Mon");
        objectList.add("Mon");
        objectList.add(new Empty());




    }
}
