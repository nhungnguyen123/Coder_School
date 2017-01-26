package com.uiapp.coder_school.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.uiapp.coder_school.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hongnhung on 1/26/17.
 */

public class ItemHoler extends RecyclerView.ViewHolder {

    @Bind(R.id.info_text)
    TextView mTvName;


    public ItemHoler(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
