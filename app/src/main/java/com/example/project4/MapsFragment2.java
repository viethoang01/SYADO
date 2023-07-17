package com.example.project4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project4.model.Host;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MapsFragment2 extends Fragment {

    private double current_longitude, current_latitude;
    private TextView show;
    private View view;
    private static final int REQUEST_CHECK_SETTINGS = 0x1;
    private static final int REQUEST_PERMISSION_CODE = 10;

    private List<Host> list;
    private void getListHost(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("house_list");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    try {
                        Host host = postSnapshot.getValue(Host.class);
                        Log.e("host",host.toString());
                        list.add(host);
                    }catch (RuntimeException e){
                        Log.e("err",""+e);
                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    private LocationManager locationManager;
    private LocationListener locationListener;
    private boolean check = false;

    // check GPS Status
    public boolean buttonCheckGPS_Status() {
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            return true;
        } else {
            return false;
        }
    }
    // end check

    private void onBindingView() {
        list = new ArrayList<>();
        getListHost();
        show = view.findViewById(R.id.show);

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        getCurrentLocation();
    }


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
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }


        };

        // Đăng ký LocationListener để cập nhật vị trí hiện tại
        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Yêu cầu cấp quyền truy cập vị trí
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
//            googleMap.setMinZoomPreference(15.0f);
//            googleMap.setMaxZoomPreference(30.0f);
//            googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//
//
//            LatLng sydney2 = new LatLng(20.282587194681778, 106.09508426776776);
//            googleMap.addMarker(new MarkerOptions().position(sydney2).title("Marker in sydney2"));
            TimeDateCurrenrt t = new TimeDateCurrenrt();


                    LatLng latLng = new LatLng(21.0103104213575, 105.51943661201959);
                    googleMap.addMarker(new MarkerOptions().position(latLng).title("Trọ Mai Linh")).setSnippet(t.convertVnd(2500000));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

            LatLng latLng2 = new LatLng(21.009829674706104, 105.51596046936339);
            googleMap.addMarker(new MarkerOptions().position(latLng2).title("Trọ Phương Nam")).setSnippet(t.convertVnd(3000000));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng2));

            LatLng latLng3 = new LatLng(21.011191786194676, 105.52604557460053);
            googleMap.addMarker(new MarkerOptions().position(latLng3).title("Trọ Bảo Ngọc")).setSnippet(t.convertVnd(1200000));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng3));

            LatLng latLng4 = new LatLng(21.01195296072706, 105.51935078133674);
            googleMap.addMarker(new MarkerOptions().position(latLng3).title("Trọ Bảo Ngọc")).setSnippet(t.convertVnd(1200000));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng3));

            LatLng latLng5 = new LatLng(21.012994561687684, 105.52162529443277);
            googleMap.addMarker(new MarkerOptions().position(latLng5).title("Trọ Tuấn Cường")).setSnippet(t.convertVnd(2200000));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng5));


            LatLng latLng9 = new LatLng(21.019764790733383, 105.52849174970704);
            googleMap.addMarker(new MarkerOptions().position(latLng9).title("Trọ Thu Thủy")).setSnippet(t.convertVnd(2200000));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng9));

            LatLng latLng6 = new LatLng(21.016159381626224, 105.51638962342246);
            googleMap.addMarker(new MarkerOptions().position(latLng6).title("Trọ Mai Hương")).setSnippet(t.convertVnd(3200000));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng6));

            LatLng latLng7 = new LatLng(21.01896359624295, 105.52111031098028);
            googleMap.addMarker(new MarkerOptions().position(latLng7).title("Trọ Thành Nam")).setSnippet(t.convertVnd(4200000));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng7));

            LatLng latLng8 = new LatLng(21.015919017919753, 105.52111031098028);
            googleMap.addMarker(new MarkerOptions().position(latLng8).title("Trọ Thủy Ngân")).setSnippet(t.convertVnd(2800000));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng8));
            googleMap.setMinZoomPreference(15.0f);
            googleMap.setMaxZoomPreference(30.0f);
            googleMap.getUiSettings().setZoomControlsEnabled(true); //

            googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                    // Xử lý sự kiện khi người dùng nhấp vào tiêu đề của marker
//                    String title = marker.getTitle();
//                    Intent intent = new Intent(getActivity(),DetailHostActiviy.class);
//                    intent.putExtra("name",marker.getTitle());
//                    startActivity(intent);
//                    Toast.makeText(getApplicationContext(), "Bạn đã nhấp vào " + title, Toast.LENGTH_SHORT).show();
                }
            });
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }

            googleMap.setMyLocationEnabled(true);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_maps, container, false);

        onBindingView();
//        getCurrentLocation();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}