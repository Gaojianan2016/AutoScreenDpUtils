package com.gjn.autoscreendputils;

import android.app.Application;

import com.gjn.autoscreenlibrary.AutoScreenDpUtils;

/**
 * @author gjn
 * @time 2018/8/23 17:45
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AutoScreenDpUtils.init(this, 384, true);
    }
}
