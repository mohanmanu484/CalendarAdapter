package com.mohan.calendaradapter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import com.mohan.calendaradapter.adapter.GenericAdapter
import com.mohan.calendaradapter.adapter.GenericViewHolder
import com.mohan.calendaradapter.adapter.ObservableArrayList
import com.mohan.calendaradapter.adapter.ObservableList
import java.util.*

class MainActivity : AppCompatActivity() {

    val list= ObservableArrayList<Any>()
    val verticalList= ObservableArrayList<Any>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gridlayout)
        val calendarView=findViewById<RecyclerView>(R.id.rvCalendar)
        getGridList(calendarView,8,0,false).adapter=createGenericAdapter(list)
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
        val verticalView=findViewById<RecyclerView>(R.id.rvVerticalDates)
        getLinearList(verticalView).adapter=createGenericAdapter(verticalList);
        verticalList.add(Empty())
        for(i in 0..6){
            verticalList.add(Date())
        }

    }

    fun createGenericAdapter(list: ObservableList<Any>): GenericAdapter<Any> {

        return object : GenericAdapter<Any>(list) {
            override fun getLayoutType(position: Int, `object`: Any): Int {
                if(`object` is String){
                    return@getLayoutType R.layout.adapter_price_type
                }
                if(`object` is Date){
                    return@getLayoutType R.layout.adapter_date_type
                }
                return R.layout.adapter_empty_type
            }

            override fun getViewHolder(view: View?, viewType: Int): GenericViewHolder<Any> {
                return ViewHolderFactory.create(view, viewType);
            }
        }
    }

}
