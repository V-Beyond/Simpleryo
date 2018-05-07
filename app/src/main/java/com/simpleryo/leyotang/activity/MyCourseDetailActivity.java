package com.simpleryo.leyotang.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.okhttplib.HttpInfo;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.base.BaseActivity;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.bean.CurrentAddressInfo;
import com.simpleryo.leyotang.bean.OrderDetailBean;
import com.simpleryo.leyotang.network.SimpleryoNetwork;
import com.simpleryo.leyotang.utils.PermissionUtils;
import com.simpleryo.leyotang.utils.XActivityUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * @author huanglei
 * @ClassNname：MyCourse.java
 * @Describe 我的订单课程详情页面
 * @time 2018/3/19 13:28
 */
@ContentView(R.layout.activity_my_course_detail)
public class MyCourseDetailActivity extends BaseActivity implements OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    @ViewInject(R.id.tv_name)
    TextView tv_name;
    @ViewInject(R.id.iv_my_course_detail_img)
    ImageView iv_my_course_detail_img;
    @ViewInject(R.id.iv_coach_img)
    ImageView iv_coach_img;
    @ViewInject(R.id.tv_order_number)
    TextView tv_order_number;
    @ViewInject(R.id.tv_order_course_name)
    TextView tv_order_course_name;
    @ViewInject(R.id.tv_coach_name)
    TextView tv_coach_name;
    @ViewInject(R.id.tv_course_duration)
    TextView tv_course_duration;
    @ViewInject(R.id.tv_store_name)
    TextView tv_store_name;
    @ViewInject(R.id.tv_course_address)
    TextView tv_course_address;
    public Transformation transformation = new RoundedTransformationBuilder()
            .cornerRadiusDp(30)
            .oval(true)
            .build();
    String id;
    //谷歌地图
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    //经纬度
    double lat;
    double lng;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_name.setText("课程详情");
        id = getIntent().getStringExtra("id");
        initData();
    }

    /**
     * 获取订单详情
     */
    public void initData(){
        SimpleryoNetwork.getOrderDetail(MyCourseDetailActivity.this, new MyBaseProgressCallbackImpl(MyCourseDetailActivity.this) {
            @Override
            public void onSuccess(HttpInfo info) {
                super.onSuccess(info);
                loadingDialog.dismiss();
                OrderDetailBean orderDetailBean = info.getRetDetail(OrderDetailBean.class);
                if (orderDetailBean.getCode().equalsIgnoreCase("0")) {
                    tv_order_number.setText("订单号：" + orderDetailBean.getData().getNo());
                    if (orderDetailBean.getData().getImageUrl() != null) {
                        Picasso.with(MyCourseDetailActivity.this).load(orderDetailBean.getData().getImageUrl()).into(iv_my_course_detail_img);
                    } else {
                        Picasso.with(MyCourseDetailActivity.this).load("http://p3.so.qhimgs1.com/bdr/_240_/t01144f848052b04663.jpg").into(iv_my_course_detail_img);
                    }
                    if (orderDetailBean.getData().getCoach() != null) {
                        if (orderDetailBean.getData().getCoach().getAvatarUrl() != null) {
                            Picasso.with(MyCourseDetailActivity.this).load(orderDetailBean.getData().getCoach().getAvatarUrl()).transform(transformation).into(iv_coach_img);
                        } else {
                            Picasso.with(MyCourseDetailActivity.this).load("http://p3.so.qhimgs1.com/bdr/_240_/t01144f848052b04663.jpg").transform(transformation).into(iv_coach_img);
                        }
                        tv_coach_name.setText("授课教练：" + orderDetailBean.getData().getCoach().getName());
                    }else{
                        tv_coach_name.setText("授课教练：无");
                        Picasso.with(MyCourseDetailActivity.this).load("http://p3.so.qhimgs1.com/bdr/_240_/t01144f848052b04663.jpg").transform(transformation).into(iv_coach_img);
                    }
                    if (orderDetailBean.getData().getCourseName() != null) {
                        tv_order_course_name.setText("课程名称：" + orderDetailBean.getData().getCourseName());
                    } else {
                        tv_order_course_name.setText("课程名称：无");
                    }
                    tv_store_name.setText("机构：" + orderDetailBean.getData().getStore().getName());
                    tv_course_duration.setText("上课时间：" + orderDetailBean.getData().getCourse().getDurations().getStartDate() + "至" + orderDetailBean.getData().getCourse().getDurations().getEndDate());
                    tv_course_address.setText("上课方式：线下授课，授课地点：" + orderDetailBean.getData().getStore().getAddress().getDetail());
                    lat = orderDetailBean.getData().getStore().getAddress().getLat();
                    lng = orderDetailBean.getData().getStore().getAddress().getLng();
                }
            }

            @Override
            public void onFailure(HttpInfo info) {
                super.onFailure(info);
                loadingDialog.dismiss();
                Toast.makeText(MyCourseDetailActivity.this, "数据一不小心走丢了，请稍后回来", Toast.LENGTH_SHORT).show();
            }
        }, id);
        // Construct a PlaceDetectionClient.
        mPlaceDetectionClient = Places.getPlaceDetectionClient(this, null);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */,
                        this /* OnConnectionFailedListener */)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();
        mGoogleApiClient.connect();
        // Construct a FusedLocationProviderClient.
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
    }


    @Event(value = {R.id.iv_back}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                XActivityUtils.getInstance().popActivity(MyCourseDetailActivity.this);
                break;
        }
    }

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private Location mLastKnownLocation;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setTrafficEnabled(true);
        mMap.setBuildingsEnabled(true);
        mMap.setIndoorEnabled(true);
        UiSettings mUiSettings = mMap.getUiSettings();
        // Keep the UI Settings state in sync with the checkboxes.
        mUiSettings.setZoomControlsEnabled(true);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            // Uncheck the box until the layer has been enabled and request missing permission.
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, false);
        }
//        // Add a marker in Sydney, Australia, and move the camera.
        final LatLng sydney = new LatLng(31.22, 121.48);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in ShangHai"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13f));
        Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();
        locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if (task.isSuccessful()) {
                    // Set the map's camera position to the current location of the device.
                    mLastKnownLocation = task.getResult();
//                    if (lat != 0 && lng != 0) {
//                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
//                                new LatLng(lat,
//                                        lng), 15));
//                        mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).title("Marker in ShangHai"));
//                    } else {
//                        mMap.moveCamera(CameraUpdateFactory
//                                .newLatLngZoom(sydney, 15));
//                        mMap.getUiSettings().setMyLocationButtonEnabled(false);
//                        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in ShangHai"));
//                    }
                    if (mLastKnownLocation != null) {
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                new LatLng(mLastKnownLocation.getLatitude(),
                                        mLastKnownLocation.getLongitude()), 15));
                        //获取当前位置信息
                        SimpleryoNetwork.getAddressInfo(MyCourseDetailActivity.this, new MyBaseProgressCallbackImpl() {
                            @Override
                            public void onSuccess(HttpInfo info) {
                                super.onSuccess(info);
                                CurrentAddressInfo currentAddressInfo = info.getRetDetail(CurrentAddressInfo.class);
                                if (currentAddressInfo.getStatus().equalsIgnoreCase("OK")) {
                                    mMap.addMarker(new MarkerOptions().position(new LatLng(mLastKnownLocation.getLatitude(),
                                            mLastKnownLocation.getLongitude())).title(currentAddressInfo.getResults().get(0).getFormatted_address().split(" ")[0]));
                                }
                            }
                        }, mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude());
                    } else {
                        mMap.moveCamera(CameraUpdateFactory
                                .newLatLngZoom(sydney, 15));
                        mMap.getUiSettings().setMyLocationButtonEnabled(false);
                    }
                } else {
                    mMap.moveCamera(CameraUpdateFactory
                            .newLatLngZoom(sydney, 15));
                    mMap.getUiSettings().setMyLocationButtonEnabled(false);
                }
            }
        });
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            // Return null here, so that getInfoContents() is called next.
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                // Inflate the layouts for the info window, title and snippet.
                View infoWindow = getLayoutInflater().inflate(R.layout.custom_info_contents,
                        (FrameLayout) findViewById(R.id.map), false);

                TextView title = ((TextView) infoWindow.findViewById(R.id.title));
                title.setText(marker.getTitle());

                TextView snippet = ((TextView) infoWindow.findViewById(R.id.snippet));
                snippet.setText(marker.getSnippet());

                return infoWindow;
            }
        });
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] results) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }
        if (PermissionUtils.isPermissionGranted(permissions, results,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            mMap.setMyLocationEnabled(true);
        }
    }

    private PlaceDetectionClient mPlaceDetectionClient;
    private static final int M_MAX_ENTRIES = 5;
    private String[] mLikelyPlaceNames;
    private String[] mLikelyPlaceAddresses;
    private String[] mLikelyPlaceAttributions;
    private LatLng[] mLikelyPlaceLatLngs;

    /**
     * Displays a form allowing the user to select a place from a list of likely places.
     */
    private void openPlacesDialog() {
        // Ask the user to choose the place where they are now.
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // The "which" argument contains the position of the selected item.
                LatLng markerLatLng = mLikelyPlaceLatLngs[which];
                String markerSnippet = mLikelyPlaceAddresses[which];
                if (mLikelyPlaceAttributions[which] != null) {
                    markerSnippet = markerSnippet + "\n" + mLikelyPlaceAttributions[which];
                }

                // Add a marker for the selected place, with an info window
                // showing information about that place.
                mMap.addMarker(new MarkerOptions()
                        .title(mLikelyPlaceNames[which])
                        .position(markerLatLng)
                        .snippet(markerSnippet));

                // Position the map's camera at the location of the marker.
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markerLatLng,
                        15));
            }
        };

        // Display the dialog.
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Choose a place")
                .setItems(mLikelyPlaceNames, listener)
                .show();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onConnected(Bundle bundle) {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }


}
