package com.hrdijital.evdekiokulum.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.google.android.material.textfield.TextInputEditText;
import com.hrdijital.evdekiokulum.Password.PasswordMail;
import com.hrdijital.evdekiokulum.R;
import com.hrdijital.evdekiokulum.config.Config;
import com.hrdijital.evdekiokulum.menu.Menu;
import com.hrdijital.evdekiokulum.register.Register;
import com.hrdijital.evdekiokulum.services.DataCallback;
import com.hrdijital.evdekiokulum.services.DefaultRequest;
import com.hrdijital.evdekiokulum.services.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    Activity activity;
    DefaultRequest defaultRequest;
    SessionManager manager;
    SharedPreferences preferences;
    //login sayfasındaki elemanlar
    ProgressDialog progressDialog;
    TextInputEditText email_text,password_text;
    TextView register_btn,password_btn;
    Button login;
    CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        email_text=findViewById(R.id.mail);
        password_text=findViewById(R.id.password);
        login=findViewById(R.id.btn_login);
        checkBox=findViewById(R.id.checkbox);
        register_btn=findViewById(R.id.register);
        password_btn=findViewById(R.id.password_btn);

        initalized();
        transition();
    }
    private void initalized() {
        activity = Login.this;
        defaultRequest = new DefaultRequest(activity);
        manager = new SessionManager(activity);
        preferences = activity.getSharedPreferences(SessionManager.PREF_NAME, SessionManager.PRIVATE_MODE);
    }


   //login sayfası tıklanma olayları
    private void transition(){

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Menu.class));

            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(Login.this);
                progressDialog.setMessage("Lütfen bekleyiniz..");
                progressDialog.show();
                startActivity(new Intent(Login.this,Register.class));
                progressDialog.dismiss();
                finish();


            }
        });
        password_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog = new ProgressDialog(Login.this);
                progressDialog.setMessage("Lütfen bekleyiniz..");
                progressDialog.show();
                startActivity(new Intent(Login.this, PasswordMail.class));
                finish();




            }
        });

    }




}