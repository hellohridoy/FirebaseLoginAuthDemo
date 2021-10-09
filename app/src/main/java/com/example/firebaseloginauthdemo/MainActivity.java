package com.example.firebaseloginauthdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText email,password;
    TextView textView;
    Button button;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Login");
        mAuth = FirebaseAuth.getInstance();
        email=findViewById(R.id.singinEmailId);
        password=findViewById(R.id.singuppasswrdId);
        textView=findViewById(R.id.signuptextId);
        button=findViewById(R.id.signinBtnId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Signup.class);
                startActivity(intent);
            }
        });
    }

    private void userLogin() {
        String eml= email.getText().toString().trim();
        String pwd= password.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(eml,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent intent = new Intent(getApplicationContext(),Profile.class);
                    startActivity(intent);
                }
                else Toast.makeText(getApplicationContext(),"Login Error",Toast.LENGTH_LONG).show();


            }
        });

    }
}