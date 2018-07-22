package com.example.lenovo.mvptest.presenter;


import com.example.lenovo.mvptest.presenter.contact.BaseContact;

/**
 * Created by lenovo on 2018/7/21.
 * auther:lenovo
 * Date：2018/7/21
 */

public class RxPresenter<T extends BaseContact.BaseView> implements BaseContact.BasePresenter<T> {

    protected T view;

    @Override
    public void attachView(T view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }
}
