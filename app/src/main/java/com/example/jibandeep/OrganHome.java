package com.example.jibandeep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class OrganHome extends AppCompatActivity {
    ImageView add;
    ImageView search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organ_home);
        add=(ImageView) findViewById(R.id.imageView);
        search=(ImageView) findViewById(R.id.imageView2);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrganHome.this, OrganAdd.class));
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrganHome.this, OrganSearch.class));
            }
        });
    }
    }
