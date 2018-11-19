package com.example.tasya.tablayoutrecyclerview2;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class FragmentMap extends Fragment implements OnMapReadyCallback, LocationListener {
    private static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 1;
    Marker m;
    Realm realm;
    RealmHelper realmHelper;
    private GoogleMap mMap;
    private LocationManager locationManager;
    private Double global_latitude, global_longitude;
    private Boolean init = true;

    public FragmentMap() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);


        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_FINE_LOCATION);

        }
        /********** get Gps location service LocationManager object ***********/
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                3000,   // 3 sec// setiap 3 detik merequest
                10, this); //minimum jarak terupdate

        /********* After registration onLocationChanged method  ********/
        /********* called periodically after each 3 sec ***********/
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Button btnChangeMapHybrid = view.findViewById(R.id.change_map_hybrid);
        btnChangeMapHybrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                //mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                Polyline line = mMap.addPolyline(new PolylineOptions()
                        .add(new LatLng(51.5, -0.1), new LatLng(40.7, -74.0))
                        .width(5)
                        .color(Color.BLUE));
            }
        });


        Button btnChangeMap = view.findViewById(R.id.change_map_normal);
        btnChangeMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        });

//        Button btnReCenter = view.findViewById(R.id.recenter);
//        btnReCenter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                m.remove();
//                LatLng area = new LatLng(global_latitude, global_longitude);
//                //mMap.addMarker(new MarkerOptions().position(area).title("Marker in Sydney")).setIcon(BitmapDescriptorFactory.fromBitmap(bmp));
//                MarkerOptions marker = new MarkerOptions().title("We are here").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).position(area);
//                m = mMap.addMarker(marker);
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(area));
//                mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));
//            }
//        });

        Button btnAdd = view.findViewById(R.id.add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addActivity = new Intent(getContext(), AddActivity.class);
                addActivity.putExtra("LATITUDE", global_latitude);
                addActivity.putExtra("LONGITUDE", global_longitude);
                startActivity(addActivity);
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    @Override
    public void onLocationChanged(Location location) {

        m.remove();

        String str = "Latitude: " + location.getLatitude() + "Longitude: " + location.getLongitude();
        //Toast.makeText(getContext(), str, Toast.LENGTH_LONG).show();
        global_latitude = location.getLatitude();
        global_longitude = location.getLongitude();


        InfoWindowData info = new InfoWindowData();
        info.setImage("ic_action_name");
        info.setHotel("Hotel : excellent hotels available");
        info.setFood("Food : all types of restaurants available");
        info.setTransport("Reach the site by bus, car and train.");


        LatLng area = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions marker = new MarkerOptions().title("We are here").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).position(area);
        m = mMap.addMarker(marker);
        m.setTag(info);

        if (init) {
            mMap.moveCamera(CameraUpdateFactory.newLatLng(area));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(14.0f));
            init = false;
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        //Toast.makeText(getApplicationContext(), "Gps turned on ", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        //Toast.makeText(getBaseContext(), "Gps turned off ", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        CustomInfoWindowGoogleMap customInfoWindow = new CustomInfoWindowGoogleMap(getContext());
        mMap.setInfoWindowAdapter(customInfoWindow);
        load_point();
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
//                m.remove();
//
//                MarkerOptions marker = new MarkerOptions().title("We are here").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).position(new LatLng(latLng.latitude, latLng.longitude));
//                m = mMap.addMarker(marker);

                Toast.makeText(getContext(), latLng.latitude + ", " + latLng.longitude, Toast.LENGTH_SHORT).show();
            }
        });
        //mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        this.mMap.setMyLocationEnabled(true);
        mMap.setBuildingsEnabled(true);
        mMap.setIndoorEnabled(true);
        mMap.setTrafficEnabled(true);
        UiSettings mUiSettings = mMap.getUiSettings();
        mUiSettings.setZoomControlsEnabled(true);
        mUiSettings.setCompassEnabled(true);
        mUiSettings.setMyLocationButtonEnabled(true);
        mUiSettings.setScrollGesturesEnabled(true);
        mUiSettings.setZoomGesturesEnabled(true);
        mUiSettings.setTiltGesturesEnabled(true);
        mUiSettings.setRotateGesturesEnabled(true);

        MarkerOptions marker = new MarkerOptions().title("init").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).position(new LatLng(-34, 151));
        m = mMap.addMarker(marker);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
    @Override
    public void onResume() {
        if (!init) {
            load_point();
        }
        super.onResume();
    }

    private void load_point() {

        List<Add> adds = realmHelper.getAllAdd();
        for (Add add : adds) {
            InfoWindowData info = new InfoWindowData();
            info.setImage(add.getImage_lok());
            info.setFood("" + add.getLatitude());
            info.setTransport("" + add.getLongitude());

            MarkerOptions marker_add = new MarkerOptions().title(add.getNama()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)).position(new LatLng(add.getLatitude(), add.getLongitude()));
            Marker mw = mMap.addMarker(marker_add);
            mw.setTag(info);

        }

    }
}
