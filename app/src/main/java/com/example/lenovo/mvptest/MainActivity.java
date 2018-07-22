package com.example.lenovo.mvptest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.MapView;
import com.example.lenovo.mvptest.busmessage.MapClickMessage;
import com.example.lenovo.mvptest.component.AppComponent;
import com.example.lenovo.mvptest.component.DaggerActivityComponent;
import com.example.lenovo.mvptest.module.ActivityModule;
import com.example.lenovo.mvptest.presenter.MapPresenter;
import com.example.lenovo.mvptest.presenter.contact.MapContact;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MapContact.View {
    @Inject
    MapPresenter mMapPresenter;

    @Inject
    public MainActivity() {

    }

    @BindView(R.id.latlng)
    TextView mLatlng;
    @BindView(R.id.desc)
    TextView mDesc;
    @BindView(R.id.Addr)
    TextView mAddr;
    @BindView(R.id.mapView)
    MapView mMapView;
    @BindView(R.id.current)
    ImageButton mCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //
    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .activityModule(new ActivityModule(this))
                .build()
                .inject(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        checkPermission();
    }

    /*
    申请权限
    */
    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission_group.LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission_group.LOCATION}, 0);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {
        mMapPresenter.getLocation(new LocationClient(this), mMapView);
        mMapPresenter.setMapClicked(mMapView);
    }

    @Override
    public void configViews() {
/*        mAddr = findViewById(R.id.Addr);
        mDesc = findViewById(R.id.desc);
        mLatlng = findViewById(R.id.latlng);
        mCurrent = findViewById(R.id.current);
        mMapView = findViewById(R.id.mapView);*/
    }

    @Override
    public void initPicture() {

    }

    @Override
    public void start() {

    }

    @Override
    public void complete() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void mapClicked(MapClickMessage message) {
        mMapPresenter.showClicked(message.getLatLng(), mMapView);
    }

    @Override
    public void showAddr(BDLocation location) {
        double altitude = location.getAltitude();
        mAddr.setText(String.format("%s%s%s%s%s", location.getAddrStr(), location.getBuildingID(), location.getIndoorLocationSurpportBuidlingName(), location.getStreetNumber(), String.valueOf(altitude)));
    }

    @Override
    public void showLatLng(BDLocation location) {
        mLatlng.setText(String.format("%s,%s", location.getLatitude(), location.getLongitude()));
    }

    @Override
    public void showDesc(BDLocation location) {
        mDesc.setText(location.getLocationDescribe());
    }

    @OnClick({R.id.current})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.current:
                mMapPresenter.showCurrent(mMapView);
                break;
        }
    }

}
