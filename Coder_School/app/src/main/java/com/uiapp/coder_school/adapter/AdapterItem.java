package com.uiapp.coder_school.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uiapp.coder_school.R;
import com.uiapp.coder_school.dto.ItemName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongnhung on 1/19/17.
 */

public class AdapterItem extends RecyclerView.Adapter {

    public List<ItemName> listName = new ArrayList<>();
    public Context mContext;
    OnClickedit onClickedit;

    public void setOnClickedit(OnClickedit onClickedit) {
        this.onClickedit = onClickedit;
    }

    public AdapterItem(List<ItemName> listName, Context mContext) {
        this.listName = listName;
        this.mContext = mContext;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null);
        return new ItemHoler(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        ItemHoler itemHoler = (ItemHoler) holder;
        final ItemName itemName = listName.get(position);
        Log.e("ItemName", itemName.getName() + "");
        itemHoler.mTvName.setText(itemName.getName());

        itemHoler.mTvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickedit.clickEdit(position, itemName.getName());
            }
        });

        itemHoler.mTvName.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onClickedit.clickDelete(position,itemName.getName());
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        if (listName == null) {
            return 0;
        } else {
            return listName.size();
        }
    }
}
