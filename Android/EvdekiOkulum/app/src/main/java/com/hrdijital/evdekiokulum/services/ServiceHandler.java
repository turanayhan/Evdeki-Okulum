package com.hrdijital.evdekiokulum.services;

import android.content.Context;

import com.android.volley.toolbox.HurlStack;

import org.json.JSONObject;

public class ServiceHandler {
    static JSONObject myJson = null;
    public Context context;
    private HurlStack httpsURLConnection;

    public ServiceHandler(Context context) {
        this.context=context;
    }

    public HurlStack makeHttpRequest( String registerUrl) {
        return httpsURLConnection;
    }

    public HurlStack makeHttpRequest() {

        return httpsURLConnection;

    }
}