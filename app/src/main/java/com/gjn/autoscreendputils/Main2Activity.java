package com.gjn.autoscreendputils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gjn.autoscreenlibrary.AutoScreenDpUtils;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AutoScreenDpUtils.setCustomDensity(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
}
