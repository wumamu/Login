package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    private Button button;
    private EditText useremail, password;
    private FirebaseAuth mAuth;
//    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        button=(Button)findViewById(R.id.bt_login);
//        button.setOnClickListener(this);
        useremail = (EditText)findViewById(R.id.et_username);
        password = (EditText)findViewById(R.id.et_password);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                user_login();
            }
        });

        mAuth = FirebaseAuth.getInstance();

    }

    private void user_login() {
        String string_useremail = useremail.getText().toString().trim();
        String string_password = password.getText().toString().trim();
        if(string_useremail.isEmpty()){
            useremail.setError("Email is required");
            useremail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(string_useremail).matches()){
            useremail.setError("Please enter a valid Email");
            useremail.requestFocus();
            return;
        }
        if(string_password.isEmpty()){
            password.setError("Password is required");
            password.requestFocus();
            return;
        }
//        if(!Patterns.EMAIL_ADDRESS.matcher(string_password).matches()){
//            password.setError("Please enter a valid password");
//            password.requestFocus();
//            return;
//        }
        mAuth.signInWithEmailAndPassword(string_useremail, string_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
//                    Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
                    startActivity(new Intent(Login.this, ProfileActivity.class));
                } else {
                    Toast.makeText(Login.this, "Failed to login! Please check your credentials", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}