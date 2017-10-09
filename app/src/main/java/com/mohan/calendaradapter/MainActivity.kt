package com.mohan.calendaradapter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    val list=ArrayList<Any>();
    val verticalList=ArrayList<Any>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gridlayout)

        for (i in 0..6){
            list.add(Date())
            list.add("2045")
            list.add("2045")
            list.add("2045")
            list.add("2045")
            list.add("2045")
            list.add("2045")
            list.add("2045")
        }

        val calendarView=findViewById<RecyclerView>(R.id.rvCalendar)
        calendarView.setHasFixedSize(true)
        calendarView.addItemDecoration(MyDecoration())
        calendarView.layoutManager= GridLayoutManager(this@MainActivity, 8, 0, false)
        calendarView.adapter=CalendarAdapter(list)

        verticalList.add(Empty())
        verticalList.add(Date())
        verticalList.add(Date())
        verticalList.add(Date())
        verticalList.add(Date())
        verticalList.add(Date())
        verticalList.add(Date())
        verticalList.add(Date())
        val verticalView=findViewById<RecyclerView>(R.id.rvVerticalDates)
        verticalView.setHasFixedSize(true)
        verticalView.addItemDecoration(MyDecoration())
        verticalView.layoutManager= LinearLayoutManager(this@MainActivity)
        verticalView.adapter=CalendarAdapter(verticalList)

    }
}
