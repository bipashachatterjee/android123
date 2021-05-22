package com.example.jibandeep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Mainmenu extends AppCompatActivity {
ImageView b,o,c,w;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
        b=(ImageView)findViewById(R.id.imageView8);
       o=(ImageView)findViewById(R.id.imageView10);
        c=(ImageView)findViewById(R.id.imageView9);
        w=(ImageView)findViewById(R.id.imageView11);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Mainmenu.this, BloodHome.class);
                startActivity(i);
            }
        });
        o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Mainmenu.this, OrganHome.class);
                startActivity(i);
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Mainmenu.this, CentralHome.class);
                startActivity(i);
            }
        });
        w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Mainmenu.this, WhyDonate.class);
                startActivity(i);
            }
        });
    }
}