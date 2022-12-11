package com.hrdijital.evdekiokulum.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hrdijital.evdekiokulum.R;
import com.hrdijital.evdekiokulum.basket.Basket;

public class Menu extends AppCompatActivity {

    BottomNavigationView menu;
    FrameLayout frameLayout;
    Fragment fragment_active;
   Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        menu=findViewById(R.id.bottom);
        frameLayout=findViewById(R.id.frame);
        toolbar=findViewById(R.id.topAppBar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        func();




    }


    private void func(){
        getSupportFragmentManager().beginTransaction().add(R.id.frame, new homepage()).commit();
        menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.homepage:
                        fragment_active=new homepage();
                        break;
                    case R.id.lesson:
                        fragment_active=new Lesson();
                        break;
                    case R.id.account:
                        fragment_active=new Account();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment_active).commit();
                return true;
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.basket){}

        startActivity(new Intent(Menu.this, Basket.class));
        return super.onOptionsItemSelected(item);
    }









}