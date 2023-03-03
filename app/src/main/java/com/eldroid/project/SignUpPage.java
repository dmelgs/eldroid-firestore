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
        firebase = new FireBaseUtils(context); //create instance of FireBaseUtils, in our case we named it firebase
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
    public void ref(){ //this is just to organize
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
                User user = new User(); //Create tag new instance/object of Class User, named "user" .
                //We set the values of each variables from the edit text box
                user.setEmail(email.getText().toString().trim());
                user.setUsername(uname.getText().toString().trim());
                user.setPassword(password.getText().toString().trim());
                user.setFirstname(firstname.getText().toString().trim());
                user.setLastname(lastname.getText().toString().trim());
                user.setAddress(address.getText().toString().trim());

                //then we get the data and pass into singUpUser method in the instance/object of FireBaseUtils named "firebase"
                firebase.signUpUser(user.getEmail(),
                        user.getUsername(),
                        user.getPassword(),
                        user.getFirstname(),
                        user.getLastname(),
                        user.getAddress());

            }catch (Exception e){
                e.printStackTrace();
            }
    }


}