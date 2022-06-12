package com.example.harshil.expensetracking;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.harshil.expensetracking.mapsclasses.GetNearByPlaces;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class where_to_refuel extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private GoogleMap mMap;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    private  double latitude,longitude;
    private int ProximateRadius = 10000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_where_to_refuel);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(where_to_refuel.this);

    }

    public void onClick(View v)
    {

        String hospital = "hospital",school = "school", restaurant = "restaurant";
        Object transferData[] = new Object[2];
        GetNearByPlaces getNearByPlaces = new GetNearByPlaces();



        switch (v.getId())
        {
            case R.id.searchicon:
                EditText editText = (EditText)  findViewById(R.id.locationSearch);
                String address= editText.getText().toString();
                MarkerOptions usermarkerOptions = new MarkerOptions();
                List<Address> addressList = null;
                if (!TextUtils.isEmpty(address))
                {


                    Geocoder geocoder = new Geocoder(where_to_refuel.this );
                    try
                    {
                        addressList = geocoder.getFromLocationName(address,10);
                        if (addressList != null)
                        {

                            for (int i=0;i<addressList.size();i++)
                            {
                                Address userAddress = addressList.get(i);
                                LatLng latLng = new LatLng(userAddress.getLatitude(), userAddress.getLongitude());

                                usermarkerOptions.position(latLng);
                                usermarkerOptions.title(address);
                                usermarkerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                                //move map camera
                                mMap.addMarker(usermarkerOptions);
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                                mMap.animateCamera(CameraUpdateFactory.zoomTo(12));


                            }

                        }
                        else {
                            Toast.makeText(where_to_refuel.this,"Location Not found",Toast.LENGTH_SHORT).show();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }



                }
                else {
                    Toast.makeText(where_to_refuel.this,"Please Write Location",Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.hospitalLocation:

                mMap.clear();
                String url = getUrl(latitude, longitude, hospital);
                transferData[0] = mMap;
                transferData[1] = url;

                getNearByPlaces.execute(transferData);
                Toast.makeText(where_to_refuel.this,"Searching for nearby locations",Toast.LENGTH_SHORT).show();
                Toast.makeText(where_to_refuel.this,"Showing nearby locations",Toast.LENGTH_SHORT).show();
                break;

            case R.id.resturantLocation:

                mMap.clear();
                url = getUrl(latitude, longitude, restaurant);
                transferData[0] = mMap;
                transferData[1] = url;

                getNearByPlaces.execute(transferData);
                Toast.makeText(where_to_refuel.this,"Searching for nearby locations",Toast.LENGTH_SHORT).show();
                Toast.makeText(where_to_refuel.this,"Showing nearby locations",Toast.LENGTH_SHORT).show();
                break;

            case R.id.fuelLocation:

                mMap.clear();
                url = getUrl(latitude, longitude, school);
                transferData[0] = mMap;
                transferData[1] = url;

                getNearByPlaces.execute(transferData);
                Toast.makeText(where_to_refuel.this,"Searching for nearby locations",Toast.LENGTH_SHORT).show();
                Toast.makeText(where_to_refuel.this,"Showing nearby locations",Toast.LENGTH_SHORT).show();
                break;

        }

    }

    private String getUrl(double latitude,double longitude,String nearbyplaces)

    {

        StringBuilder googleURL = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googleURL.append("location="+ latitude+","+longitude);
        googleURL.append("&radius="+ ProximateRadius);
        googleURL.append("&type="+nearbyplaces);
        googleURL.append("&sensor=true");
        googleURL.append("&key="+"AIzaSyANz0d8YH2Vv3t1Tl7qg5p5ZGfAF-0xEBg");

        Log.d("Google Maps Activity ","url="+googleURL.toString());
        return  googleURL.toString();


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(where_to_refuel.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        }
        else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

    }
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(where_to_refuel.this)
                .addConnectionCallbacks(where_to_refuel.this)
                .addOnConnectionFailedListener(where_to_refuel.this)
                .addApi(LocationServices.API).build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(where_to_refuel.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, where_to_refuel.this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

        latitude=location.getLatitude();
        longitude=location.getLongitude();

        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }
        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));

        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, where_to_refuel.this);
        }

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

}