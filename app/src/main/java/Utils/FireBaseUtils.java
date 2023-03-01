package Utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.eldroid.project.LoginPage;
import com.eldroid.project.MainActivity;
import com.eldroid.project.SignUpPage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FireBaseUtils {
    private final FirebaseAuth mAuth;
    private final FirebaseFirestore db;
    private Context context;
    public FireBaseUtils(Context context){
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        this.context = context;
    }
    public void signUpUser(String email, String username, String password, String firstName, String lastName, String address) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    FirebaseUser user = authResult.getUser();
                    if (user != null) {
                        Map<String, Object> userData = new HashMap<>();
                        userData.put("email", email);
                        userData.put("username", username);
                        userData.put("firstName", firstName);
                        userData.put("lastName", lastName);
                        userData.put("address", address);
                        db.collection("users").document(user.getUid()).set(userData)
                                .addOnSuccessListener(documentReference -> {
                                    // Data saved successfully
                                    Toast.makeText(context, "User registered successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(context, LoginPage.class);
                                    context.startActivity(intent);
                                })
                                .addOnFailureListener(e -> {
                                    // Data failed to save
                                    Toast.makeText(context, "Failed to register user", Toast.LENGTH_SHORT).show();
                                });
                    }
                })
                .addOnFailureListener(e -> {
                    // Registration failed
                    Toast.makeText(context, "Failed to register user", Toast.LENGTH_SHORT).show();
                });
    }

    public void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            db.collection("users").document(user.getUid()).get()
                                    .addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
                                            Intent intent = new Intent(context, MainActivity.class);
                                            context.startActivity(intent);
                                            System.out.print("login success");
                                        } else {
                                            mAuth.signOut();
                                            ReusableMethods.showMessage(context, "User not registered");
                                            System.out.print("login failed");
                                        }
                                    });
                        }
                    } else {
                        ReusableMethods.showMessage(context, "Invalid login credentials");
                        System.out.print("login failed");
                    }
                });
    }
}
