package com.uiapp.coder_school;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.uiapp.coder_school.dto.ItemName;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EditActivity extends Activity {

    @Bind(R.id.btn_edit)
    Button mBtnEdit;

    @Bind(R.id.edt_edit)
    EditText mEdtEdit;

    private EventBus bus = EventBus.getDefault();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        ButterKnife.bind(this);
        final Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("start");
        String name = bundle.getString("name");
        final int position = bundle.getInt("position");
        Log.e("name", name + "H");


        mEdtEdit.setText(name);

        mBtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ItemName itemName = new ItemName();
                itemName.setPosition(position);
                itemName.setName(mEdtEdit.getText().toString());
                bus.postSticky(itemName);
                finish();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        bus.register(this); // registering the bus
    }

    @Subscribe
    public void onSourceEvent(String name) {
        Log.e("nameLog", name);
    }

    @Override
    public void onStop() {
        bus.unregister(this); // un-registering the bus
        super.onStop();
    }
}
