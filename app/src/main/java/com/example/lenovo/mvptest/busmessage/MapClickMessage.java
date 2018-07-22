package com.example.lenovo.mvptest.busmessage;

import com.baidu.mapapi.model.LatLng;

/**
 * Created by lenovo on 2018/7/22.
 * auther:lenovo
 * Date：2018/7/22
 */

public class MapClickMessage {
    public LatLng getLatLng() {
        return mLatLng;
    }

    private LatLng mLatLng;

    public MapClickMessage(LatLng latLng) {
        mLatLng = latLng;
    }
}
