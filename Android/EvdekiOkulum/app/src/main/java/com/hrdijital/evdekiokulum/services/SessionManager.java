package com.hrdijital.evdekiokulum.services;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
public class SessionManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    public static final int PRIVATE_MODE = 0;
    public static final String PREF_NAME = "evdekiokulum";
    public static final String IS_LOGIN = "true";
    public static final  String FCM_TOKEN ="FCM_TOKEN";

    private DefaultRequest setRequest;

    public SessionManager(Context context) {
        setRequest = new DefaultRequest(_context);
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        editor.apply();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
    public void saveToken(String token) {
        editor.putString(FCM_TOKEN, token);
        editor.commit();
    }
    public void loginSession(String isLogin){
        editor.putString(IS_LOGIN, isLogin);
        editor.commit();
    }
}
