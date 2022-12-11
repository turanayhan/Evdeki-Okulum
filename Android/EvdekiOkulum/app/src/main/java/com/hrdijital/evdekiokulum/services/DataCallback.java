package com.hrdijital.evdekiokulum.services;
import com.android.volley.VolleyError;

public interface DataCallback {

    void onSuccess(Object result);
    void onError(VolleyError error);
}
