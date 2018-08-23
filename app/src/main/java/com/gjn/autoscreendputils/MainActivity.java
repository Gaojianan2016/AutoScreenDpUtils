package com.gjn.autoscreendputils;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.gjn.autoscreenlibrary.AutoScreenDpUtils;
import com.gjn.autoscreenlibrary.IAutoCancel;

public class MainActivity extends AppCompatActivity implements IAutoCancel{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AutoScreenDpUtils.setCustomDensity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
    }
}
