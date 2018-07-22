package com.example.lenovo.mvptest.presenter.contact;

/**
 * Created by lenovo on 2018/7/21.
 * auther:lenovo
 * Date：2018/7/21
 */

public interface BaseContact {
    interface BasePresenter<T> {
        void attachView(T view);

        void detachView();
    }

    interface BaseView {

        void start();

        void complete();
    }
}
