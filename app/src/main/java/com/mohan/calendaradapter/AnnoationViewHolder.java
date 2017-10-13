package com.mohan.calendaradapter;

import android.view.View;
import android.widget.TextView;

import com.mohan.calendaradapter.adapter.GenericViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mohang on 10/10/17.
 */

@ViewHolder( R.layout.adapter_empty_type)
public class AnnoationViewHolder extends GenericViewHolder<String>{


    @BindView(R.id.tvPrice)
    TextView name;


    protected AnnoationViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(itemView);
    }

    @Override
    public void setData(String data, int pos) {
        name.setText(data);
    }
}
