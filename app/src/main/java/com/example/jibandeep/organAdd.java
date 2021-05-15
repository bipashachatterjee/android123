package com.example.jibandeep;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class organAdd extends AppCompatActivity {
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private Button newb;
    private CheckBox c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organ_add);
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference();
        newb = (Button) findViewById(R.id.Button);
        final EditText name = (EditText) findViewById(R.id.name);
        final EditText mobile = (EditText) findViewById(R.id.phone);
        final EditText address = (EditText) findViewById(R.id.address);
        final EditText hospital = (EditText) findViewById(R.id.hospital);
        final Spinner blood = (Spinner) findViewById(R.id.spinner);
        c = findViewById(R.id.checkBox);
        newb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name1 = name.getText().toString();
                String mobile1 = mobile.getText().toString();
                String blood1 = blood.getSelectedItem().toString();
                String address1 = address.getText().toString();
                String hospital1 = hospital.getText().toString();
                String combo = name1 + "," + mobile1 + "," + blood1 + "," + hospital1+","+ address1;
                if (name.length() == 0) {
                    name.setError("Please Enter Your Name");
                    name.requestFocus();
                    return;
                }
                if (mobile1.length() == 0||mobile1.matches("^[0-9]{10}$")){
                    mobile.setError("Please Enter Your Correct Number");
                    mobile.requestFocus();
                    return;

                }
                if (blood.equals("") ) {
                    Toast.makeText(getApplicationContext(),"Please Select Your Blood Group",Toast.LENGTH_LONG).show();
                    blood.requestFocus();
                    return;
                }
                else {
                    mFirebaseDatabase.child("users").child(blood1).child(mobile1).setValue(combo);

                    Toast.makeText(organAdd.this,"Donor Added",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(organAdd.this,mainmenu.class);
                    startActivity(i);
                    finish();
                }
            }
            });
        c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    newb.setEnabled(true);
                else
                    newb.setEnabled(false);
            }
        });
    }
}
















