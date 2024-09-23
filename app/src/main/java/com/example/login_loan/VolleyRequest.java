package com.example.login_loan;

import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.Map;

public class VolleyRequest {

    public static void post(Context context, String url, final Map<String, String> params, final VolleyCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callback.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("VolleyError", "Error: " + error.toString());
                        callback.onError("Error: " + error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };


        stringRequest.setTag("VolleyRequest");


        queue.add(stringRequest);
    }

    public interface VolleyCallback {
        void onSuccess(String result);
        void onError(String error);
    }
}
