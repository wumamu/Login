package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {
    Button button_change_password, button_logout;
    TextView textView;
    FirebaseUser user;
//    DatabaseReference reference;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        textView=(TextView)findViewById(R.id.tv_welcome);
        button_change_password =(Button)findViewById(R.id.bt_change_password);
        button_logout =(Button)findViewById(R.id.bt_logout);
        user = FirebaseAuth.getInstance().getCurrentUser();
//        reference = FirebaseDatabase.get
//        userID = user.getUid();

        textView.setText("Hello, " + user.getEmail());
        button_change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent i = new Intent(getApplicationContext(), ChangePassword.class);
                startActivity(i);
            }
        });
        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ProfileActivity.this, Login.class));
            }
        });
    }
}
