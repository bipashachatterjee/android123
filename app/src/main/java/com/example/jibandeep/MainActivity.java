package com.example.jibandeep;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText email,password;
Button b,r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        email =findViewById(R.id.email);
        password=findViewById(R.id.pass);
    }
    public void login(View v){
      String e=email.getText().toString();
      String ps=password.getText().toString();
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
        }if (ps.length() == 0) {
            password.setError("Please Create a Password");
            password.requestFocus();
        }
        mAuth.signInWithEmailAndPassword(e, ps)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent i=new Intent(MainActivity.this,SplashScreen.class);
                            startActivity(i);
                            finish();
                            FirebaseUser user = mAuth.getCurrentUser();
                           // updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e("Error", "signInWithEmail:failure", task.getException());
                        Toast.makeText(MainActivity.this,"Invaild User",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void register_page(View v){
        Intent i=new Intent(MainActivity.this,RegistrationActivity.class);
        startActivity(i);
        finish();
    }
}
