# AutoScreenDpUtils
自动适配屏幕工具

- 依赖使用
```
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}


dependencies {
    implementation 'com.github.Gaojianan2016:AutoScreenDpUtils:2.0.0x'
}
```

# 基础使用说明

初始化
新建一个Application

```
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
```

或者直接调用`AutoScreenDpUtils.init(this);`
在清单文件AndroidManifest.xml中的Application级写入
```
<application
  .....>
  <meta-data android:name="set_width_dp" android:value="384" />
</application>
```

第一个Activity不适配 即让`Activity  implements IAutoCancel`
```
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

        findViewById(R.id.tv2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                startActivity(intent);
            }
        });
    }
}
```

第二个Activity适配
```
public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AutoScreenDpUtils.setCustomDensity(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
}
```

第二个Activity适配其他宽度
```
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
```
