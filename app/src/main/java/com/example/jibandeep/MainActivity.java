package com.example.jibandeep;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 101;
    private FirebaseAuth mAuth;
    private SignInButton gButton;
    TextView textview;
    GoogleSignInClient mGoogleSignInClient;
    EditText email,ps;
    Button login;
    GoogleSignInOptions gso;
  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview =findViewById(R.id.goRegister);
        String text="Don't Have a Account?? Register Now..";
        SpannableString ss=new SpannableString(text);
        email=findViewById(R.id.email);
        ps=findViewById(R.id.pass);
        login=findViewById(R.id.login);
        mAuth = FirebaseAuth.getInstance();
        gButton = findViewById(R.id.gButton);
        ClickableSpan clickableSpan=new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Toast.makeText(getApplicationContext(),"one",Toast.LENGTH_LONG).show();
                Intent i=new Intent(MainActivity.this,RegistrationActivity.class);
                startActivity(i);
            }
        };
        ss.setSpan(clickableSpan,23,37, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textview.setText(ss);
        textview.setMovementMethod(LinkMovementMethod.getInstance());
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

         mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        gButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e=email.getText().toString();
                String pass=ps.getText().toString();
                if (pass.length() == 0) {
                    ps.setError("Please Create a Password");
                    ps.requestFocus();
                }
                if(pass.length()<4){
                    ps.setError("Your Password Should Contain Minimum 4 digits");
                    ps.requestFocus();
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
                mAuth.signInWithEmailAndPassword(e, pass)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                   Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_LONG).show();
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                } else {
                                    Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_LONG).show();
                                }
                            }
                        });

            }
        });
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);

                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
            }
        }
    }
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(MainActivity.this,"successFul",Toast.LENGTH_LONG).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this,task.getException().toString(),Toast.LENGTH_LONG).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        Intent i=new Intent(MainActivity.this, Mainmenu.class);
        startActivity(i);
    }

}
