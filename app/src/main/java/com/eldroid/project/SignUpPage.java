package com.eldroid.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import Utils.FireBaseUtils;
import Utils.ReusableMethods;
public class SignUpPage extends AppCompatActivity {
    Context context;
    Button login, register;
    EditText firstname, lastname, address, uname, email, password;
    FireBaseUtils firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        ref();
        context = this;
        firebase = new FireBaseUtils(context);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoLoginPage();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });
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

    public void createUser(){
            try {
                User user = new User();
                user.setEmail(email.getText().toString().trim());
                user.setUsername(uname.getText().toString().trim());
                user.setPassword(password.getText().toString().trim());
                user.setFirstname(firstname.getText().toString().trim());
                user.setLastname(lastname.getText().toString().trim());
                user.setAddress(address.getText().toString().trim());

                firebase.signUpUser(user.getEmail(),
                        user.getUsername(),
                        user.getPassword(),
                        user.getFirstname(),
                        user.getLastname(),
                        user.getAddress());

                ReusableMethods.showMessage(this,"Account Created Successfully");
            }catch (Exception e){
                e.printStackTrace();
            }
    }


}