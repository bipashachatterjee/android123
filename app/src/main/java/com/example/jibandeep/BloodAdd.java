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

public class BloodAdd extends AppCompatActivity {
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private Button b;
    private CheckBox c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_add);
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference();
        b = (Button) findViewById(R.id.Button);
        final EditText name = (EditText) findViewById(R.id.name);
        final EditText mobile = (EditText) findViewById(R.id.phone);
        final EditText address = (EditText) findViewById(R.id.address);
        final Spinner blood = (Spinner) findViewById(R.id.spinner);
        c = findViewById(R.id.checkBox);
        //mobile.getText().toString();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name1 = name.getText().toString();
                String mobile1 = mobile.getText().toString();
                String blood1 = blood.getSelectedItem().toString();
                String address1 = address.getText().toString();
                String combo = name1 + "," + mobile1 + "," + blood1 + "," + address1;
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
                if (blood1.equals("") ) {
                    Toast.makeText(getApplicationContext(),"Please Select Your Blood Group",Toast.LENGTH_LONG).show();
                    blood.requestFocus();
                    return;
                }
                else {
                    mFirebaseDatabase.child("users").child(blood1).child(mobile1).setValue(combo);

                    Toast.makeText(BloodAdd.this,"Donor Added",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(BloodAdd.this,mainmenu.class);
                    startActivity(i);
                    finish();
                }
            }
        });
        c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    b.setEnabled(true);
                else
                    b.setEnabled(false);
            }
        });

    }
}