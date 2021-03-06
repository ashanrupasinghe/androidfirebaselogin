package com.example.mylogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter=5;
    private  TextView userRegistration;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    protected TextView forgetPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText)findViewById(R.id.etEmailUpdate);
        Password = (EditText)findViewById(R.id.etPassword);
        Info = (TextView)findViewById(R.id.tvInfo);
        Login = (Button)findViewById(R.id.btnLogin);
        userRegistration = (TextView)findViewById(R.id.tvRegister);

        Info.setText("Number of attempts remaining: 5");
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        forgetPassword = (TextView) findViewById(R.id.tvForgotPassword);
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user!=null){
            finish();
            startActivity(new Intent(MainActivity.this,SecondActivity.class));
        }

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Name.getText().toString(),Password.getText().toString());
            }
        });

        userRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,RegistrationActivity.class));
            }
        });

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,PasswordActivity.class));
            }
        });
    }

    private void validate(String userNmae, String userPassword){
        progressDialog.setMessage("processing...");
        progressDialog.show();
            firebaseAuth.signInWithEmailAndPassword(userNmae,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        //Toast.makeText(MainActivity.this, "Login Successfull",Toast.LENGTH_SHORT).show();
                        //startActivity(new Intent(MainActivity.this,SecondActivity.class));
                        checkEmailVerification();
                    }else{
                        Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                        counter--;
                        progressDialog.dismiss();
                        Info.setText("Number of attempts remaining: "+counter);
                        if (counter==0){
                            Login.setEnabled(false);
                        }
                    }
                }
            });
    }

    private void checkEmailVerification(){
        startActivity(new Intent(MainActivity.this,SecondActivity.class));
        /*
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();
        if(emailflag){
            finish();
            startActivity(new Intent(MainActivity.this,SecondActivity.class));
        }else{
            Toast.makeText(this,"Verify Your Email",Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
        */
    }
}