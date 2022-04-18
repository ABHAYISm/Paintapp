package com.example.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText Email,password;
    Button button;
    TextView create;
    ProgressBar progressBar;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Email=findViewById(R.id.Email);
        password=findViewById(R.id.password);
        create =findViewById(R.id.loginbtn);
        button=findViewById(R.id.button);
        auth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email=Email.getText().toString().trim();
                String pass=password.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Email.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    password.setError("Password is required ");
                    return;
                }
                if(pass.length()<6){
                    password.setError("Password must be 6 characters or long");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                // now authentication
               auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()){
                           Toast.makeText(Login.this, "Logged in", Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(getApplicationContext(),MainActivity.class));
                       }else{
                           Toast.makeText(Login.this, "Some error occured", Toast.LENGTH_SHORT).show();
                       }

                   }
               });

            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });
    }

}