package com.example.lenovo.mvptest.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lenovo on 2018/7/21.
 * auther:lenovo
 * Date：2018/7/21
 */
@Module
public class APPModule {
    private Context mContext;

    public APPModule(Context context) {
        mContext = context;
    }

    @Provides
    Context provideContext() {
        return mContext;
    }
}
