package com.example.harshil.expensetracking.mapsclasses;

import android.os.AsyncTask;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.List;

public class GetNearByPlaces  extends AsyncTask<Object,String,String> {

    private  String googlelacedata,url;
    private GoogleMap mMap;
    @Override
    protected String doInBackground(Object... objects) {

        mMap = (GoogleMap)objects[0];
        url = (String) objects[1];


        downloadUrl downloadUrl = new downloadUrl();
        try {
            googlelacedata=downloadUrl.ReadUrl(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return googlelacedata;
    }

    @Override
    protected void onPostExecute(String s) {
        List<HashMap<String,String>>  nearbyplacesList = null;
        DataParser dataParser = new DataParser();
        nearbyplacesList = dataParser.parse(s);
        DisplayNearbyPlaces(nearbyplacesList);
    }

    private void DisplayNearbyPlaces(List<HashMap<String,String>>  nearbyplacesList){

        for (int i=0;i<nearbyplacesList.size();i++){
            MarkerOptions markerOptions = new MarkerOptions();

            HashMap<String,String >googleNearbyPlaces = nearbyplacesList.get(i);
            String namePlace = googleNearbyPlaces.get("place_name");
            String vicinity = googleNearbyPlaces.get("vicinity");
            double lat = Double.parseDouble(googleNearbyPlaces.get("lat"));
            double lng = Double.parseDouble(googleNearbyPlaces.get("lng"));

            LatLng latLng = new LatLng(lat,lng);

            markerOptions.position(latLng);
            markerOptions.title(namePlace +":"+vicinity);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
            //move map camera
            mMap.addMarker(markerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(12f));
          }
    }
}
