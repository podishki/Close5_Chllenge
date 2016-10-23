package com.example.kiran.close5_chllenge;

import android.util.Base64;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpsManager {

    static StringBuilder sb;
    public static String getData(String uri){

        BufferedReader reader = null;
        try {
            URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            sb = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line;
            while ( (line = reader.readLine()) != null){
                sb.append(line + "\n");
            }
            // return sb.toString();

        }catch (MalformedURLException e) {
            e.printStackTrace();


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return sb.toString();

        // ANDRIOD dev team is no longer improving AndroidHttpClient feature
        /*AndroidHttpClient client = AndroidHttpClient.newInstance("AndroidAgent");
        HttpGet request = new HttpGet(uri);
        HttpResponse response;
        try{
            response = client.execute(request);
            return EntityUtils.toString(response.getEntity());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            client.close();
        }*/

    }

}
