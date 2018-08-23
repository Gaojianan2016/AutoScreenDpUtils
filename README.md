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
    implementation 'com.github.Gaojianan2016:AutoScreenDpUtils:1.0.1'
}
```

#基础使用说明

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

```

第二个Activity适配
```
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
```

xml布局
activity_main
```
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.gjn.autoscreendputils.MainActivity">

    <TextView
        android:id="@+id/tv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <TextView
        android:id="@+id/textView"
        android:layout_width="384dp"
        android:layout_height="60dp"
        android:background="@android:color/holo_blue_bright"
        android:gravity="center"
        android:text="384-60"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <TextView
        android:id="@+id/textView2"
        android:layout_width="192dp"
        android:layout_height="50dp"
        android:layout_marginTop="32dp"
        android:background="@android:color/darker_gray"
        android:gravity="center"
        android:text="192-50"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textView" />

    <TextView
        android:id="@+id/textView3"
        android:layout_width="192dp"
        android:layout_height="50dp"
        android:layout_marginTop="32dp"
        android:background="@android:color/holo_red_light"
        android:gravity="center"
        android:text="192-50"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textView2" />

</android.support.constraint.ConstraintLayout>
```

activity_main2
```
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.gjn.autoscreendputils.Main2Activity">

    <TextView
        android:id="@+id/textView"
        android:layout_width="384dp"
        android:layout_height="100dp"
        android:layout_marginTop="16dp"
        android:background="@color/colorPrimary"
        android:gravity="center"
        android:text="384-100"
        android:textColor="@android:color/white"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <TextView
        android:id="@+id/textView2"
        android:layout_width="192dp"
        android:layout_height="70dp"
        android:layout_marginTop="32dp"
        android:background="@android:color/holo_blue_bright"
        android:gravity="center"
        android:text="192-70"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textView" />

    <TextView
        android:id="@+id/textView3"
        android:layout_width="192dp"
        android:layout_height="120dp"
        android:layout_marginTop="32dp"
        android:background="@android:color/holo_green_light"
        android:gravity="center"
        android:text="192-120"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textView2" />
</android.support.constraint.ConstraintLayout>

```
