package com.example.lenovo.mvptest;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.example.lenovo.mvptest.component.AppComponent;
import com.example.lenovo.mvptest.component.DaggerAppComponent;
import com.example.lenovo.mvptest.module.APPModule;


/**
 * Created by lenovo on 2018/7/21.
 * auther:lenovo
 * Date：2018/7/21
 */

public class App extends Application {

    private AppComponent mAppComponent;
    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        SDKInitializer.initialize(this);
        initComponent();
    }

    public static App getInstance() {
        return app;
    }

    private void initComponent() {
        mAppComponent = DaggerAppComponent.builder()
                .aPPModule(new APPModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
