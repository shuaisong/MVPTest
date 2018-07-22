package com.example.lenovo.mvptest.component;

import android.content.Context;

import com.example.lenovo.mvptest.module.APPModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by lenovo on 2018/7/21.
 * auther:lenovo
 * Date：2018/7/21
 */
@Component(modules = APPModule.class)
public interface AppComponent {
    Context getContext();
}
