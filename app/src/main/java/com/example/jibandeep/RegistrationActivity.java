package com.example.jibandeep;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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

public class RegistrationActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText name,email,age,password;
     Button register,back;
     private ProgressDialog mLoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mAuth = FirebaseAuth.getInstance();
       name=findViewById(R.id.name);
       email=findViewById(R.id.email);
       age=findViewById(R.id.age);
       password=findViewById(R.id.pass);
       register=findViewById(R.id.register);
       back=findViewById(R.id.back);
       mLoadingBar=new ProgressDialog(RegistrationActivity.this);
       back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i= new Intent(RegistrationActivity.this,MainActivity.class);
               startActivity(i);
               finish();
           }
       });
       register.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String rName=name.getText().toString();
               String e=email.getText().toString();
               String rAge=age.getText().toString();
               String pass=password.getText().toString();
               String em="";
               boolean flag_email = false;
               if (pass.length() == 0) {
                   password.setError("Please Create a Password");
                   password.requestFocus();
               }
              else if(pass.length()<4){
                   password.setError("Your Password Should Contain Minimum 4 digits");
                   password.requestFocus();
               }
               else if (rName.length() == 0) {
                   name.setError("Please Enter Your Name");
                   name.requestFocus();
               }
               else if (rAge.length() == 0) {
                   age.setError("Please Enter Your Age");
                   age.requestFocus();
               }
               else if(rAge.length()>3){
                   age.setError("Your Age Should Contain Maximum 3 Digits");
                   age.requestFocus();
               }

               else if (e.length() == 0) {
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
               else{
                   mLoadingBar.setTitle("Registration");
                   mLoadingBar.setMessage("Please Wait");
                   mLoadingBar.setCanceledOnTouchOutside(false);
                   mLoadingBar.show();
                   mAuth.createUserWithEmailAndPassword(e,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful())
                           {
                               Toast.makeText(RegistrationActivity.this,"Registration Successful",Toast.LENGTH_LONG).show();
                               Intent i= new Intent(RegistrationActivity.this, Mainmenu.class);
                               i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                               startActivity(i);
                           }
                           else {
                               Toast.makeText(RegistrationActivity.this,task.getException().toString(),Toast.LENGTH_LONG).show();
                           }
                       }
                   });
               }
           }
       });
    }
}
















