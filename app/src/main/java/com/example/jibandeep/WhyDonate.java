package com.example.jibandeep;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WhyDonate extends AppCompatActivity {
Button s,b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_why_donate);

s=findViewById(R.id.button);
b=findViewById(R.id.button5);
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent=new Intent(Intent.ACTION_SEND);
                myintent.setType("text/plain");
                String body ="Your body here";
                String sub="Your subject";
                myintent.putExtra(Intent.EXTRA_SUBJECT,sub);
                myintent.putExtra(Intent.EXTRA_TEXT,body);
                startActivity(Intent.createChooser(myintent,"Share Using"));
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(WhyDonate.this,mainmenu.class);
                startActivity(i);
            }
        });
    }
}