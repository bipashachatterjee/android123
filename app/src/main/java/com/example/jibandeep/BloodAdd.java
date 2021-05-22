package com.example.jibandeep;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

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
        final EditText mobile = (EditText) findViewById(R.id.number);
        final EditText address = (EditText) findViewById(R.id.address);
        final EditText hospital = (EditText) findViewById(R.id.hospital);
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
                String hospital1 = hospital.getText().toString();
                String combo = name1 + "," + mobile1 + "," + blood1 + "," + address1+","+hospital1;
                Toast.makeText(BloodAdd.this, combo, Toast.LENGTH_SHORT).show();
                if (name1.length() == 0) {
                    name.setError("Please Enter Your Name");
                    name.requestFocus();
                    return;
                }
                if (hospital1.length() == 0) {
                    hospital.setError("Please Enter Your Name");
                    hospital.requestFocus();
                    return;
                }
                if (mobile1.length() == 0){
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
                    @IgnoreExtraProperties
                     class User {
                        public String blood1;
                        public String mobile1;
                        public User(){
                        }
                        public User(String blood,String mobile){
                            this.blood1=blood;
                            this.mobile1=mobile;
                        }
                    }
                   mFirebaseDatabase.child("users").child(blood1).child(mobile1).setValue(combo, new DatabaseReference.CompletionListener() {
                       @Override
                       public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                           if (error != null) {
                               System.out.println("Data could not be saved " + error.getMessage());
                           } else {
                               System.out.println("Data saved successfully.");
                           }
                       }
                   });

                    Toast.makeText(BloodAdd.this,"Donor Added",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(BloodAdd.this, Mainmenu.class);
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

