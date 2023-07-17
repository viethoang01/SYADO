package com.example.project4;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationRequest;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.viewmodel.CreationExtras;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.List;


public class SearchFragment extends Fragment implements LocationListener {
    private double current_longitude,current_latitude;
    private TextView show;
    private View view;
    private static final int REQUEST_CHECK_SETTINGS = 0x1;
    private static final int REQUEST_PERMISSION_CODE = 10;

    private LocationManager locationManager;
    private LocationListener locationListener;
    private ProgressDialog progressDialog;
    private boolean check = false;

    // check GPS Status
    public boolean buttonCheckGPS_Status(){
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            return true;
        }
        else {
            return false;
        }
    }
    // end check




    public void getCurrentLocation() {
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            // GPS chưa được bật
           Toast toast = Toast.makeText(getActivity(), "Vui lòng bật GPS để sử dụng chức năng này", Toast.LENGTH_SHORT);
            toast.show();
            

        }
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                // Vị trí hiện tại của thiết bị
                current_latitude = latitude;
                current_longitude = longitude;
                Toast.makeText(getActivity(), "Lat: " + current_latitude + ", Long: " + current_longitude, Toast.LENGTH_SHORT).show();
                Log.d("Current location", "Lat: " + latitude + ", Long: " + longitude);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {}

            @Override
            public void onProviderEnabled(String provider) {}

            @Override
            public void onProviderDisabled(String provider) {}
        };

        // Đăng ký LocationListener để cập nhật vị trí hiện tại
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Yêu cầu cấp quyền truy cập vị trí
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }

    private void onBindingView(){
        show = view.findViewById(R.id.show);

        //
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Đang xử lý");
        progressDialog.setMessage("Vui lòng chờ");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true); //chỉ sử dụng cho style spinner
        //

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
    }
    private void onBindingAction(){
        getCurrentLocation();
//        SupportMapFragment mapFragment =
//                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.layout.fragment_search);
//        Toast.makeText(getActivity(), "130", Toast.LENGTH_SHORT).show();
//
//        if (mapFragment != null) {
//            mapFragment.getMapAsync(callback);
//        }


    }

    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {


            if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                int REQUEST_LOCATION_PERMISSION =10;
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
                return;
            }else{
                if(current_latitude == 0 || current_longitude == 0 ){
                    current_latitude = 20.282856;
                    current_longitude = 106.094824;
                    Toast.makeText(getActivity(), current_latitude+" | "+current_longitude, Toast.LENGTH_SHORT).show();
                    return;
                }
                LatLng currentLocation = new LatLng(current_latitude, current_longitude);
                if((currentLocation != null)){
                    Toast.makeText(getActivity(), "null", Toast.LENGTH_SHORT).show();

                }

                Toast.makeText(getActivity(), currentLocation.latitude+"null"+currentLocation.longitude, Toast.LENGTH_SHORT).show();

                googleMap.addMarker(new MarkerOptions().position(currentLocation).title("My Current Location"));
                googleMap.setMinZoomPreference(15.0f);
                googleMap.setMaxZoomPreference(30.0f);
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));

                googleMap.getUiSettings().setZoomControlsEnabled(true); //
                googleMap.getUiSettings().setCompassEnabled(true);  // laban
                googleMap.setMyLocationEnabled(true);
            }

        }
    };

        public SearchFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_search, container, false);
        onBindingView();
        onBindingAction();
        return view;
    }


    @Override
    public void onLocationChanged(@NonNull Location location) {

        current_longitude = location.getLongitude();
        current_longitude = location.getLatitude() ;
    }

    @Override
    public void onLocationChanged(@NonNull List<Location> locations) {
        LocationListener.super.onLocationChanged(locations);
    }

    @Override
    public void onFlushComplete(int requestCode) {
        LocationListener.super.onFlushComplete(requestCode);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }

    @NonNull
    @Override
    public CreationExtras getDefaultViewModelCreationExtras() {
        return super.getDefaultViewModelCreationExtras();
    }
}