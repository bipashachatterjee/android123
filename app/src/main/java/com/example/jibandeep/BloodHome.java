package com.example.jibandeep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
//import android.widget.ImageView;

public class BloodHome extends AppCompatActivity {
    ImageView add;
    ImageView search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_home);
        add=(ImageView) findViewById(R.id.imageView);
        search=(ImageView) findViewById(R.id.imageView2);
        // this button
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BloodHome.this, BloodAdd.class));
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BloodHome.this, BloodSearchView.class));
            }
        });
    }
}