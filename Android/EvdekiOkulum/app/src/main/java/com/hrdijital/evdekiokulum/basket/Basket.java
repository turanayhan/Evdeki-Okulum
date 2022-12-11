package com.hrdijital.evdekiokulum.basket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hrdijital.evdekiokulum.R;
import com.hrdijital.evdekiokulum.payment.Payment;

public class Basket extends AppCompatActivity {

    Button btn_basket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basket);
        btn_basket=findViewById(R.id.btn_basket);
        transition();
    }

    public void  transition(){

        btn_basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Basket.this, Payment.class));
            }
        });


    }


}