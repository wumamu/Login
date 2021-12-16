package com.example.login;

import android.content.Intent;
import android.os.Bundle;
//import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

//import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ChangePassword extends AppCompatActivity {
    Button button;
    private EditText password_first, password_second;
//    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_passward);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        button=(Button)findViewById(R.id.bt_submit);
        password_first = (EditText)findViewById(R.id.et_new_password);
        password_second = (EditText)findViewById(R.id.et_new_password_second);
        button.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            user_change_password();
//                startActivity(new Intent(ChangePassword.this, Login.class));
//                Intent i = new Intent(getApplicationContext(), Login.class);
//                startActivity(i);
        });
    }

    private void user_change_password() {
        String string_pf = password_first.getText().toString().trim();
        String string_ps = password_second.getText().toString().trim();
        if(string_pf.isEmpty()){
            password_first.setError("password is required");
            password_first.requestFocus();
        } else if(string_ps.isEmpty()){
            password_second.setError("please confirm your password");
            password_second.requestFocus();
        } else if(!string_pf.equals(string_ps)){
            password_second.setError("the second one did not match");
            password_second.requestFocus();
        } else {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                user.updatePassword(string_pf)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
//                                Log.d(TAG, "User password updated.");
                                Toast.makeText(ChangePassword.this, "Successfully Changed Password!", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(ChangePassword.this, Login.class));
                            }
                        });
            }

        }
//                .signInWithEmailAndPassword(string_useremail, string_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful()){
////                    Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
//                    startActivity(new Intent(Login.this, ProfileActivity.class));
//                } else {
//                    Toast.makeText(Login.this, "Failed to login! Please check your credentials", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
    }
}