package com.example.mysneakersrack.activities;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;

import android.view.View;


import com.example.mysneakersrack.R;



public class MainActivity extends AppCompatActivity {




    private void goToActivity(Class<?> activity){
        Intent intent = new Intent(this, activity);
        this.startActivity(intent);
        this.finish();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        this.findViewById(R.id.button_rack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go to MainActivity
                MainActivity.this.goToActivity(MyRack.class);

            }
        });

        this.findViewById(R.id.button_story).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go to MainActivity
                MainActivity.this.goToActivity(Story.class);

            }
        });

    }




}