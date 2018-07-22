package com.example.lenovo.mvptest.listener;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.lenovo.mvptest.MainActivity;
import com.example.lenovo.mvptest.presenter.contact.MapContact;

/**
 * Created by lenovo on 2018/7/21.
 * auther:lenovo
 * Date：2018/7/21
 */

public class MyLocationListener extends BDAbstractLocationListener {
    private MainActivity mMainActivity;
    private MapView mMapView;
    private MyLocationData mLocData;
    private boolean isFirst = true;

    public MyLocationListener(MapContact.View view, MapView mapView) {
        if (view == null) {
            throw new NullPointerException("mMainActivity =null");
        } else
            this.mMainActivity = (MainActivity) view;
        this.mMapView = mapView;
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        BaiduMap mBaiduMap = mMapView.getMap();//mBaiduMap是地图控制器对象
        if (mLocData == null)
            mLocData = new MyLocationData.Builder()
                    .accuracy(bdLocation.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(0).latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude()).build();
        mBaiduMap.setMyLocationData(mLocData);
        if (isFirst) {
            MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude()), 16.0f);
            mBaiduMap.animateMapStatus(mapStatusUpdate);
            isFirst = false;
        }
// 设置定位数据
        mMainActivity.showAddr(bdLocation);
        mMainActivity.showLatLng(bdLocation);
        mMainActivity.showDesc(bdLocation);
//// 当不需要定位图层时关闭定位图层
//        mBaiduMap.setMyLocationEnabled(false);

    }
}
