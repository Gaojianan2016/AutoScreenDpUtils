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
        //第二个参数为设计图上面的宽度以dp为单位 第三个参数为显示log
        AutoScreenDpUtils.init(this, 384, true);
    }
}
