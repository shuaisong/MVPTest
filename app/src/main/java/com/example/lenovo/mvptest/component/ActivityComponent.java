package com.example.lenovo.mvptest.component;


import com.example.lenovo.mvptest.MainActivity;
import com.example.lenovo.mvptest.module.ActivityModule;

import dagger.Component;

/**
 * Created by lenovo on 2018/7/21.
 * auther:lenovo
 * Date：2018/7/21
 */
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    MainActivity getActivity();

    MainActivity inject(MainActivity activity);
}
