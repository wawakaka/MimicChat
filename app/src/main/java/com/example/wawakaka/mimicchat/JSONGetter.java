package com.example.wawakaka.mimicchat;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Wawakaka on 3/25/2017.
 */

public class JSONGetter {
    private static final String MAIN_URL = "http://188.166.211.222:9090/messages.json ";
    private static Response response;


    //method for get json object
    public static JSONObject getObject(){
        try{
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(MAIN_URL)
                    .build();
            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());
        }catch (IOException | JSONException e){
            Log.e("failed to get data ",""+e.getLocalizedMessage());
        }
        return null;
    }

    //method for get json array
    public static JSONArray getArray(){
        try{
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(MAIN_URL)
                    .build();
            response = client.newCall(request).execute();
            return new JSONArray(response.body().string());
        }catch (IOException | JSONException e){
            Log.e("failed to get data ",""+e.getLocalizedMessage());
        }
        return null;
    }


}
