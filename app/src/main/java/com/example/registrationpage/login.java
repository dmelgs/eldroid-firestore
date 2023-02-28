package com.example.registrationpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {
    EditText uname, pass;
    Button login, goRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
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
    }
    public void goToRegisterPage(){
        startActivity(new Intent(login.this, RegistrationPage.class));
    }
    public void goToWelcomePage(){
        startActivity(new Intent(login.this, welcomepage.class));
    }

}
