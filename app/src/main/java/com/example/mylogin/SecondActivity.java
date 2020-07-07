package com.example.mylogin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SecondActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private Button Logout;
    //private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        firebaseAuth = FirebaseAuth.getInstance();
        Logout = (Button)findViewById(R.id.btnLogout);
        //progressDialog = new ProgressDialog(this);

        Logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //progressDialog.setMessage("processing...");
                //progressDialog.show();
                firebaseAuth.signOut();
                finish();
                //progressDialog.dismiss();
                Toast.makeText(SecondActivity.this,"Logout Success",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SecondActivity.this,MainActivity.class));
            }
        });
    }
}