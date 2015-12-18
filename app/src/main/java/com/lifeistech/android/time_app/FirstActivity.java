package com.lifeistech.android.time_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.NumberPicker;

public class FirstActivity extends AppCompatActivity {
    int minNumber = 1;
    int secNumber = 10;

    private NumberPicker minPicker;
    private NumberPicker minPicker2;
    private NumberPicker secPicker;
    private NumberPicker secPicker2;

    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        pref = getSharedPreferences("pref_timer", Context.MODE_PRIVATE);
        minPicker = (NumberPicker) findViewById(R.id.minpicker);
        minPicker2 = (NumberPicker) findViewById(R.id.minpicker2);
        secPicker = (NumberPicker) findViewById(R.id.secpicker);
        secPicker2 = (NumberPicker) findViewById(R.id.secpicker2);

        minPicker.setMaxValue(9);
        minPicker.setMinValue(0);
        minPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        minPicker2.setMaxValue(9);
        minPicker2.setMinValue(0);
        minPicker2.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);


        secPicker.setMaxValue(5);
        secPicker.setMinValue(0);
        secPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);


        secPicker2.setMaxValue(9);
        secPicker2.setMinValue(0);
        secPicker2.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);


        minPicker.setValue(pref.getInt("key_min_picker", 0));
        minPicker2.setValue(pref.getInt("key_min_picker2",0));
        secPicker.setValue(pref.getInt("key_sec_picker", 0));
        secPicker2.setValue(pref.getInt("key_sec_picker2", 0));

    }


    public void start(View v) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("minute", minPicker.getValue());
        intent.putExtra("minute2", minPicker2.getValue());
        intent.putExtra("second", secPicker.getValue());
        intent.putExtra("second2", secPicker2.getValue());

        startActivity(intent);


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            SharedPreferences.Editor editor = pref.edit();
            // prefの保存
            editor.putInt("key_min_picker",minPicker.getValue());
            editor.putInt("key_min_picker2", minPicker2.getValue());
            editor.putInt("key_sec_picker",secPicker.getValue());
            editor.putInt("key_sec_picker2",secPicker2.getValue());


            editor.commit();


            return super.onKeyDown(keyCode, event);

        } else {
            return super.onKeyDown(keyCode, event);

        }
    }
}