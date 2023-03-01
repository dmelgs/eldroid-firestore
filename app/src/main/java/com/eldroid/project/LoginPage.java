package com.eldroid.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import Utils.FireBaseUtils;
import Utils.ReusableMethods;

public class LoginPage extends AppCompatActivity {
    EditText email, pass;
    Button login, goRegister;
    FireBaseUtils fireBaseUtils = new FireBaseUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        ref();

        goRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRegisterPage();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToWelcomePage();
            }
        });
    }
    public void ref(){
        login = findViewById(R.id.logInbutton);
        goRegister = findViewById(R.id.createBtn);
        email = findViewById(R.id.email_login);
        pass = findViewById(R.id.pass_login);
    }
    public void goToRegisterPage(){startActivity(new Intent(LoginPage.this, SignUpPage.class));}
    public void goToWelcomePage(){
        try {
            fireBaseUtils.loginUser(email.getText().toString(), pass.getText().toString());
            startActivity(new Intent(LoginPage.this, MainActivity.class)
            );
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}