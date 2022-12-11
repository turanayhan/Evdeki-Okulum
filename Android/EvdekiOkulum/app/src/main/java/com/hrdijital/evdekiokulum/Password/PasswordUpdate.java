package com.hrdijital.evdekiokulum.Password;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.hrdijital.evdekiokulum.R;
import com.hrdijital.evdekiokulum.login.Login;

public class PasswordUpdate extends AppCompatActivity {

    Button btn_update;
    TextInputEditText password1;
    TextInputEditText password2;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_update);
        btn_update=findViewById(R.id.btn_update);
        password1=findViewById(R.id.password1);
        password2=findViewById(R.id.password2);
        transition();
    }




    private  void transition(){

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog = new ProgressDialog(PasswordUpdate.this);
                progressDialog.setMessage("Lütfen bekleyiniz..");
                progressDialog.show();
                startActivity(new Intent(PasswordUpdate.this, PasswordInformation.class));

                finish();


            }
        });


    }




}