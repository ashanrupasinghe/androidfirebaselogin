package com.example.mylogin;

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
import com.google.firebase.auth.FirebaseAuth;

public class PasswordActivity extends AppCompatActivity {

    private EditText passwordEmail;
    private Button resetPassword;
    private FirebaseAuth firebaseAuth;
    private TextView goBackPasswordReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        passwordEmail = (EditText)findViewById(R.id.etPasswordEmail);
        resetPassword = (Button)findViewById(R.id.btnResetPassword);
        firebaseAuth = FirebaseAuth.getInstance();
        goBackPasswordReset = (TextView)findViewById(R.id.tvGoBackResetPassword);
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_email = passwordEmail.getText().toString().trim();

                if(user_email.equals("")){
                    Toast.makeText(PasswordActivity.this,"Please enter your regisered email address",Toast.LENGTH_SHORT).show();
                }
                else{
                    firebaseAuth.sendPasswordResetEmail(user_email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(PasswordActivity.this,"Password Reset email is sent.",Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(PasswordActivity.this,MainActivity.class));

                            }else{
                                Toast.makeText(PasswordActivity.this,"Error in sending password reset email.",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        goBackPasswordReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PasswordActivity.this,MainActivity.class));
            }
        });
    }


}