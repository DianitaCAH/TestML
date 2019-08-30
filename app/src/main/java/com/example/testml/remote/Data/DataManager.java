package com.example.testml.remote.Data;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.testml.remote.VolleySingleton;

import org.json.JSONObject;

public class DataManager {

    private Context context;

    public DataManager(Context context) {
        this.context = context;
    }

    public void sendRequest(String url, final DataValues dataValues) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest((url), new JSONObject(),new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("DataManager RESPONSE", response.toString());
                dataValues.setJsonDataResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("DataManager ERROR", error.toString());
                dataValues.setVolleyError(error);
            }
        }

        );
        VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    public void sendGETRequest(String url, final DataValues dataValues) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, (url), new JSONObject(),new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("DataManager RESPONSE", response.toString());
                dataValues.setJsonDataResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("DataManager ERROR", error.toString());
                dataValues.setVolleyError(error);
            }
        }

        );
        VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }



}
