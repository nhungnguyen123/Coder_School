package com.uiapp.coder_school;

import android.content.ContentProvider;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.uiapp.coder_school.adapter.AdapterItem;
import com.uiapp.coder_school.adapter.OnClickedit;
import com.uiapp.coder_school.dto.ItemName;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvAdd;
    EditText mEdtname;
    Button mBtnAdd;

    private EventBus bus = EventBus.getDefault();


    public AdapterItem adapterItem;
    public List<ItemName> listName = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvAdd = (RecyclerView) findViewById(R.id.rv_name);
        mEdtname = (EditText) findViewById(R.id.edt_name);
        mBtnAdd = (Button) findViewById(R.id.btn_addd);

        rvAdd.setHasFixedSize(true);
        rvAdd.setLayoutManager(new GridLayoutManager(this, 1));
        adapterItem = new AdapterItem(listName, this);
        rvAdd.setAdapter(adapterItem);
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("click", mEdtname.getText().toString());
                if (mEdtname.getText().toString().equalsIgnoreCase("")) {

                } else {
                    ItemName itemName = new ItemName();
                    itemName.setName(mEdtname.getText().toString());
                    listName.add(itemName);
                    adapterItem.notifyDataSetChanged();
                    mEdtname.setText("");
                }

//                adapterItem.notifyDataSetChanged();
            }
        });

        adapterItem.setOnClickedit(new OnClickedit() {
            @Override
            public void clickEdit(int position, String name) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("position", position);
                bundle.putString("name", name);
                intent.putExtra("start", bundle);

                startActivity(intent);

            }
        });

    }

    //TODO đã lấy được nguồn về.
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onSourceEvent(ItemName itemName) {
//        String json = new Gson().toJson(event);
//        Log.e("sourceCategory", json.toString() + "");
//        setUpData();
//        adapterCategoty.notifyDataSetChanged();

        Log.e("Itemchange", itemName.getName() + "");
        for (int i = 0; i < listName.size(); i++) {
            if (i == itemName.getPosition()) {
                listName.get(i).setName(itemName.getName());

            }
            adapterItem.notifyDataSetChanged();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        bus.register(this); // registering the bus
    }

    @Override
    public void onStop() {
        bus.unregister(this); // un-registering the bus
        super.onStop();
    }

}
