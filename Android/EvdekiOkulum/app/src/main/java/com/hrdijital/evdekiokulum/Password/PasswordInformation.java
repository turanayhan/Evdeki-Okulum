package com.hrdijital.evdekiokulum.Password;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hrdijital.evdekiokulum.R;
import com.hrdijital.evdekiokulum.login.Login;

public class PasswordInformation extends AppCompatActivity {
    ProgressDialog progressDialog;

    Button btn_homepage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_information);
        btn_homepage=findViewById(R.id.btn_homepage);
        transition();
    }



    private  void transition(){

        btn_homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog = new ProgressDialog(PasswordInformation.this);
                progressDialog.setMessage("Lütfen bekleyiniz..");
                progressDialog.show();
                startActivity(new Intent(PasswordInformation.this, Login.class));
                finish();


            }
        });


    }
}