package com.example.lenovo.mvptest.presenter.contact;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;

/**
 * Created by lenovo on 2018/7/21.
 * auther:lenovo
 * Date：2018/7/21
 */

public interface MapContact extends BaseContact {
    interface View extends BaseContact.BaseView {
        void showAddr(BDLocation location);

        void showLatLng(BDLocation location);

        void showDesc(BDLocation location);
    }

    interface Presenter<T> extends BaseContact.BasePresenter<T> {
        void getLocation(LocationClient client, MapView mapView);

        void showCurrent(MapView mapView);

        @Override
        void attachView(T view);

        @Override
        void detachView();

        void setMapClicked(MapView mapView);

        void showClicked(LatLng latLng, MapView mapView);
    }
}
