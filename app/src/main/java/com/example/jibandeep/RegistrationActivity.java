package com.example.jibandeep;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText name,email,phone,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mAuth = FirebaseAuth.getInstance();
       name=findViewById(R.id.name);
       email=findViewById(R.id.email);
       phone=findViewById(R.id.phone);
       pass=findViewById(R.id.pass);
    }
    public void back(View v){
        Intent i=new Intent(RegistrationActivity.this,MainActivity.class);
        startActivity(i);
        finish();
    }
    public void register(View v){
       String e =email.getText().toString();
       String n =name.getText().toString();
       String ph =phone.getText().toString();
       String ps= pass.getText().toString();
        if (e.length() == 0) {
            email.setError("Please Enter Your Email Address");
            email.requestFocus();
            return;
        }
        String em="";
        boolean flag_email = false;
        if (e.length() == 0) {
            em = "Please Enter Email";
            flag_email = true;
        } else if (e.indexOf('@') == -1){
            em = "There should be @ in email";
            flag_email = true;
        }
        else if(e.indexOf('@')<2){
            em = "Invalid email address";
            flag_email = true;
        }
        else if(e.lastIndexOf('.') == -1 || (e.length()-e.lastIndexOf('.'))<3){
            em = "No Domain Present";
            flag_email = true;
        }
        if(flag_email) {
            email.setError(em);
            email.requestFocus();
            return;
        }
        if (n.length() == 0) {
            name.setError("Please Enter Your Name");
            name.requestFocus();
            return;
        } if (ph.length() == 0) {
            phone.setError("Please Enter Your Phone Number");
            phone.requestFocus();
            return;
        } if (ps.length() == 0) {
            pass.setError("Please Create a Password");
            pass.requestFocus();
        }
        mAuth.createUserWithEmailAndPassword(e, ps)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(RegistrationActivity.this,"Authentication Successed.",Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            back(null);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegistrationActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            Log.e("Faliur",task.getException().toString());
                            //  updateUI(null);
                        }
                    }
                });
    }
}