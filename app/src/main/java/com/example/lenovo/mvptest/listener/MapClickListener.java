package com.example.lenovo.mvptest.listener;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.model.LatLng;
import com.example.lenovo.mvptest.busmessage.MapClickMessage;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by lenovo on 2018/7/22.
 * auther:lenovo
 * Date：2018/7/22
 */

public class MapClickListener implements BaiduMap.OnMapClickListener {
    @Override
    public void onMapClick(LatLng latLng) {
        EventBus.getDefault().post(new MapClickMessage(latLng));
    }

    @Override
    public boolean onMapPoiClick(MapPoi mapPoi) {
        return false;
    }
}
