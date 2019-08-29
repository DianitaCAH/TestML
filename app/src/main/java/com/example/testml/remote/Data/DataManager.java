package com.example.testml.remote.Data;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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
                Log.e("RESPONSE", response.toString());
                toastData(response.toString());
                //dataValues.setJsonDataResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR", error.toString());
                toastData(error.toString());
                //dataValues.setVolleyError(error);
            }
        }

        );
        VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    void toastData(String data){
        Toast.makeText(this.context, data, Toast.LENGTH_LONG).show();
    }


}
