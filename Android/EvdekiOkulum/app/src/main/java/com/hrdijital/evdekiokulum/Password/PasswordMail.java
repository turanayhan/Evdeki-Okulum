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

public class PasswordMail extends AppCompatActivity {

    TextInputEditText email;
    Button btn_send;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_mail);
        email=findViewById(R.id.mail);
        btn_send=findViewById(R.id.btn_send);
        transition();
    }


    private  void transition(){

        email=findViewById(R.id.mail_password);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog = new ProgressDialog(PasswordMail.this);
                progressDialog.setMessage("Lütfen bekleyiniz..");
                progressDialog.show();
                startActivity(new Intent(PasswordMail.this,PasswordUpdate.class));

                finish();


            }
        });


    }



}