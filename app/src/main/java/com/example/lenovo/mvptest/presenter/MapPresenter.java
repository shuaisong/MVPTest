package com.example.lenovo.mvptest.presenter;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.lenovo.mvptest.App;
import com.example.lenovo.mvptest.MainActivity;
import com.example.lenovo.mvptest.R;
import com.example.lenovo.mvptest.listener.MapClickListener;
import com.example.lenovo.mvptest.listener.MyLocationListener;
import com.example.lenovo.mvptest.presenter.contact.MapContact;

import javax.inject.Inject;

/**
 * Created by lenovo on 2018/7/21.
 * auther:lenovo
 * Date：2018/7/21
 */

public class MapPresenter extends RxPresenter<MapContact.View> implements MapContact.Presenter<MapContact.View> {
    @Inject
    public MapPresenter() {
    }

    @Inject
    MainActivity mMainActivity;

    @Override
    public void getLocation(LocationClient mMClient, MapView mMapView) {
//        mMClient = new LocationClient(((MainActivity) view).getApplicationContext());
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);

        option.setIsNeedLocationDescribe(true);
        option.setIsNeedAddress(true);
        option.setCoorType("bd09ll");

        option.setScanSpan(3000);//可选，设置发起定位请求的间隔，int类型，单位ms
//如果设置为0，则代表单次定位，即仅定位一次，默认为0
//如果设置非0，需设置1000ms以上才有效

        option.setOpenGps(true);
        option.setLocationNotify(true);
//可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false

        option.setIgnoreKillProcess(false);
//可选，定位SDK内部是一个service，并放到了独立进程。
//设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)

        option.SetIgnoreCacheException(false);
//可选，设置是否收集Crash信息，默认收集，即参数为false

        option.setEnableSimulateGps(false);
//可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false
        mMClient.setLocOption(option);

        mMClient.registerLocationListener(new MyLocationListener(mMainActivity, mMapView));
        BaiduMap mBaiduMap = mMapView.getMap();//mBaiduMap是地图控制器对象
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        MyLocationConfiguration.LocationMode mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;   //默认为 LocationMode.NORMAL 普通态
        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory.fromResource(R.mipmap.icon);
        int accuracyCircleFillColor = App.getInstance().getResources().getColor(R.color.circlefillcolor);//自定义精度圈填充颜色
        int accuracyCircleStrokeColor = App.getInstance().getResources().getColor(R.color.circlestrokecolor);//自定义精度圈边框颜色
        mBaiduMap.setMyLocationConfiguration(new MyLocationConfiguration(
                mCurrentMode, true, mCurrentMarker,
                accuracyCircleFillColor, accuracyCircleStrokeColor));

        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        mMClient.start();
    }

    @Override
    public void showCurrent(MapView mapView) {
        double latitude = mapView.getMap().getLocationData().latitude;
        double longitude = mapView.getMap().getLocationData().longitude;
        LatLng latLng = new LatLng(latitude, longitude);
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(latLng, 16.0f);
        mapView.getMap().animateMapStatus(mapStatusUpdate);
    }

    @Override
    public void setMapClicked(MapView mapView) {
        mapView.getMap().setOnMapClickListener(new MapClickListener());
    }

    @Override
    public void showClicked(LatLng latLng, MapView mapView) {
        MyLocationData mLocData = new MyLocationData.Builder()
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(0).latitude(latLng.latitude)
                .longitude(latLng.longitude).build();
        mapView.getMap().setMyLocationData(mLocData);
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(latLng, 16.0f);
        mapView.getMap().animateMapStatus(mapStatusUpdate);
    }
}
