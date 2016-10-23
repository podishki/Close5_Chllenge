package com.example.kiran.close5_chllenge.parsers;

import android.util.Log;

import com.example.kiran.close5_chllenge.model.Districts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kiran on 7/27/2016.
 */
public class DistrictsJSONParser {
    public static ArrayList<Districts> parseFeed(String content) {

        int count =0;
        try {
            JSONArray ar = new JSONArray(content);
            ArrayList<Districts> districtList = new ArrayList<Districts>();

            for (int i = 0; i < ar.length(); i++) {
                JSONObject obj = ar.getJSONObject(i);
                Districts district = new Districts();

                district.setAddress(obj.getString("address"));
                district.setDate(obj.getString("date"));
                district.setCategory(obj.getString("category"));
                district.setPddistrict(obj.getString("pddistrict"));
                district.setDescript(obj.getString("descript"));
                //Log.d("KIRAN", "parseFeed: "+ district.getCategory());
                JSONObject obj1 = obj.getJSONObject("location");
                district.setLongitude(obj1.getString("longitude"));
                district.setLatitude(obj1.getString("latitude"));
                //Log.d("KIRAN", "parseFeed: "+ "Long:" + district.getLatitude()+ "Lati:" + district.getLongitude());
                if(obj.getString("date").equals("2015-12-30T00:00:00") && count <= 15) {
                    districtList.add(district);
                    Log.d("KIRAN", "parseFeed: "+ district.getCategory());
                    count++;
                }
            }
            Log.d("KIRAN", "parseFeed: "+ districtList);
            return districtList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
