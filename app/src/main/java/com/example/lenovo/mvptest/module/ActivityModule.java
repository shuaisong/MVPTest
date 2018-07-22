package com.example.lenovo.mvptest.module;

import com.example.lenovo.mvptest.MainActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lenovo on 2018/7/22.
 * auther:lenovo
 * Date：2018/7/22
 */
@Module
public class ActivityModule {
    private MainActivity mMainActivity;

    public ActivityModule(MainActivity mMainActivity) {
        this.mMainActivity = mMainActivity;
    }

    @Provides
    MainActivity provideContext() {
        return mMainActivity;
    }
}
