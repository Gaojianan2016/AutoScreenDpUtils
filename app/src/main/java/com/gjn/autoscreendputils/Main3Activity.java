package com.gjn.autoscreendputils;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gjn.autoscreenlibrary.AutoScreenDpUtils;
import com.gjn.autoscreenlibrary.IAutoChange;

public class Main3Activity extends AppCompatActivity implements IAutoChange {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AutoScreenDpUtils.setCustomDensity(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

    }

    @Override
    public float newWidth() {
        return 200;
    }
}
