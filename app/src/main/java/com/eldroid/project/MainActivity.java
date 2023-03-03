package com.eldroid.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import Utils.FireBaseUtils;
import Utils.ReusableMethods;

public class MainActivity extends AppCompatActivity {
    Button logout;
    private FireBaseUtils firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String userId = mAuth.getCurrentUser().getUid();

        firebase = new FireBaseUtils(this);
        firebase.setupFirebase();

        TextView usertext = findViewById(R.id.usernametextview);
        logout = findViewById(R.id.logout_btn);

        firebase.getUserData(userId, new OnCompleteListener<DocumentSnapshot>() { //this method is for retrieving/reading data from firestore
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String uname = document.getString("username");
                        usertext.setText("Hi! "+uname);
                    } else {
                        ReusableMethods.showMessage(MainActivity.this, "User not found");
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Error getting user data: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebase.logoutUser();
            }
        });
    }
}