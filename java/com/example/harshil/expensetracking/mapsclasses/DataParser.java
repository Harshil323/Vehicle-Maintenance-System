package com.example.harshil.expensetracking.mapsclasses;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataParser

{

    private HashMap<String,String> getsingleNearbyPlace(JSONObject googlePlaceJson) {

        HashMap<String ,String>    googleplacename= new HashMap<>();
        String namePlace = "-NA-";
        String vicinity  ="-NA-";
        String latitude = "";
        String longetude= "";
        String referance = "";

        try {
            if (!googlePlaceJson.isNull("name")){
                namePlace = googlePlaceJson.getString("name");
            }
            if (!googlePlaceJson.isNull("vicinity")){
                vicinity = googlePlaceJson.getString("vicinity");
            }
            latitude = googlePlaceJson.getJSONObject("geometry").getJSONObject("location").getString("let");
            longetude = googlePlaceJson.getJSONObject("geometry").getJSONObject("location").getString("lng");
            referance = googlePlaceJson.getString("reference");


            googleplacename.put("place_name",namePlace);
            googleplacename.put("vicinity",vicinity);
            googleplacename.put("lat",latitude);
            googleplacename.put("lng",longetude);
            googleplacename.put("reference",referance);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return googleplacename;

    }


    private List<HashMap<String,String>> getAllNearbyPlaces(JSONArray jsonArray)

    {

        int counter  = jsonArray.length();
        List<HashMap<String,String>> nearbyPalcesList = new ArrayList<>();

        HashMap<String,String> nearbyPlacesName = null;
        for (int i=0;i<counter;i++){
            try {
                nearbyPlacesName = getsingleNearbyPlace((JSONObject)jsonArray.get(i));

                nearbyPalcesList .add(nearbyPlacesName);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return  nearbyPalcesList;

    }

    public List<HashMap<String,String>> parse(String jsondata){
        JSONArray jsonArray = null;
        JSONObject jsonObject;


        try {
            jsonObject = new JSONObject(jsondata);
            jsonArray  = jsonObject.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  getAllNearbyPlaces(jsonArray);

    }
}
