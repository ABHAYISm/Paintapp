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

public class Register extends AppCompatActivity {
    EditText fullname,Email,password,phone;
    Button button;
    TextView loginbtn;
    FirebaseAuth auth;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fullname=findViewById(R.id.fullname);
        Email=findViewById(R.id.Email);
        password=findViewById(R.id.password);
        phone=findViewById(R.id.phone);
        button=findViewById(R.id.button);
        loginbtn=findViewById(R.id.loginbtn);
        auth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }

        if(auth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=Email.getText().toString().trim();
                String pasw=password.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Email.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(pasw)){
                    password.setError("Password is required ");
                    return;
                }
                if(pasw.length()<6){
                    password.setError("Password must be 6 charecters or long");
                    return;
                }
                progressBar.setVisibility(view.VISIBLE);

                //now registering the user in firebase

                auth.createUserWithEmailAndPassword(email,pasw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "Resistered", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else{
                            Toast.makeText(Register.this, "Some error occured", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
        
    }
}