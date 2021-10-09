package com.example.firebaseloginauthdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Signup extends AppCompatActivity {
    EditText email,password;
    Button signup;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setTitle("Signup");
        mAuth = FirebaseAuth.getInstance();
        email=findViewById(R.id.singupEmailId);
        password=findViewById(R.id.singuppasswrdId);
        signup=findViewById(R.id.signupBtnId);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userRegister();
            }
        });
    }

    private void userRegister() {

        String eml=email.getText().toString().trim();
        String pwd=password.getText().toString().trim();
        mAuth.createUserWithEmailAndPassword(eml,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    Toast.makeText(getApplicationContext(),"Resgister sucess",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                } else {

                    if (task.getException()instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(),"Already Resgisterrd ",Toast.LENGTH_LONG).show();

                    }else
                    Toast.makeText(getApplicationContext(),"Resgister Error",Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}