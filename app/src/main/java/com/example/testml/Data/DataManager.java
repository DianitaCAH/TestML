package com.example.testml.Data;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.testml.R;
import com.example.testml.Remote.VolleySingleton;

import org.json.JSONObject;

public class DataManager {


    private Context context;

    public DataManager(Context context) {
        this.context = context;
    }

    public void sendRequest(String url) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest((url), new JSONObject(),new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("RESPONSE", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR", error.toString());

            }
        }

        );
        VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }


}
