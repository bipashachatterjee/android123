package com.example.jibandeep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class OrganSearch extends AppCompatActivity {
    Button srch;
    Spinner pan,blood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organ_search);
         srch=(Button) findViewById(R.id.button3);
        blood=(Spinner) findViewById(R.id.spinner3);
        pan=(Spinner) findViewById(R.id.spinner4);
        srch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String btype=blood.getSelectedItem().toString();
                String panc=pan.getSelectedItem().toString();
                Intent re=new Intent(OrganSearch.this, OrganSearchView.class);
                re.putExtra("bt",btype);
                re.putExtra("pa",panc);
                startActivity(re);

            }
        });

    }
}