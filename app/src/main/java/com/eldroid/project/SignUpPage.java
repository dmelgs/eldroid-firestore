package com.eldroid.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SignUpPage extends AppCompatActivity {
    Button login, register;
    EditText firstname, lastname, address, uname, email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        ref();

    }
    public void ref(){
        login = findViewById(R.id.login_button);
        register = findViewById(R.id.reg_button);
        firstname = findViewById(R.id.fname_eText);
        lastname = findViewById(R.id.lname_eText);
        address = findViewById(R.id.address_eText);
        uname = findViewById(R.id.uname_eText);
        email = findViewById(R.id.email_eText);
        password = findViewById(R.id.pass_eText);
    }
    public void gotoLoginPage(){
        startActivity(new Intent(SignUpPage.this, LoginPage.class));
    }

}