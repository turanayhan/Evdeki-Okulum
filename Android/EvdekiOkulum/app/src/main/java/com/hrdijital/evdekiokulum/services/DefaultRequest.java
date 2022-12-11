package com.hrdijital.evdekiokulum.services;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.CubeGrid;
import com.hrdijital.evdekiokulum.R;
import com.hrdijital.evdekiokulum.config.Config;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class DefaultRequest {

    private static RequestQueue requestQueue;
    SessionManager manager;
    SharedPreferences preferences;
    Context context;
    String language;
    static Timer longTimer;
    static final int LOGOUT_TIME = 3500;

    public DefaultRequest(Context context) {

        getRequestQueueInstance(context);
        this.context = context;

        longTimer = new Timer();
        longTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                cancel();
            }
        }, LOGOUT_TIME);
    }


    private static synchronized RequestQueue getRequestQueueInstance(Context context) {

        if (requestQueue == null) {
            try {

                ServiceHandler handler = new ServiceHandler(context);
                URL url = new URL(Config.BASE);

                if (url.getProtocol().trim().equalsIgnoreCase("https")) {
                    requestQueue = Volley.newRequestQueue(context);
                } else {
                    HurlStack hurlStack = handler.makeHttpRequest();
                    requestQueue = Volley.newRequestQueue(context, hurlStack);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        }
        return requestQueue;
    }
    public void getRequest(final String url,   final JSONObject jsonObject, final DataCallback callback ) {


        JsonObjectRequest request = new JsonObjectRequest(com.android.volley.Request.Method.GET,
                url , jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    callback.onSuccess(response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                callback.onError(error);

            }
        }) {

            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        request.setRetryPolicy(
                new DefaultRetryPolicy(
                        0,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
        );
        requestQueue.add(request);
    }

    private Dialog getProgressDialog(Context context) {
        Dialog progressDialog = new Dialog(context);
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setContentView(R.layout.progress);
        Sprite doubleBounce = new CubeGrid();
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.getWindow().setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        return progressDialog;

    }
    private void ServerCheck() {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.server_error);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        Button netdismiss = (Button) dialog.findViewById(R.id.serverDismiss);
        netdismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    private void NetworkCheck() {


        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.network_error);
        dialog.show();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();

        TextView netdismiss = (TextView) dialog.findViewById(R.id.networkDismiss);
        netdismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }

    public void postRequest(String url, Context context, JSONObject jsonObject, DataCallback dataCallback) {
        if (context != null){
            final Dialog dialog = getProgressDialog(context);
            dialog.show();

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                    url  , jsonObject, response -> {
                        Log.d( "onResponse: ",response.toString());
                        dialog.dismiss();
                        try {
                            dataCallback.onSuccess(response);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }, error -> {
                        Log.d( "error: ",error.toString());
                        dialog.dismiss();
                        if (error instanceof NoConnectionError) {
                            NetworkCheck();
                        }
                        if (error instanceof ServerError) {
                            ServerCheck();
                        }

                    }) {
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();

                    return params;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> params = new HashMap<String, String>();
                    return params;
                }
            };
            request.setRetryPolicy(
                    new DefaultRetryPolicy(
                            0,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                    )
            );

            requestQueue.add(request);
        }
    }
}
