package Utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.eldroid.project.LoginPage;
import com.eldroid.project.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FireBaseUtils {
    private final FirebaseAuth mAuth;
    private final FirebaseFirestore db;
    private Context context;
    private FirebaseUser currentUser;

    public FireBaseUtils(Context context) {
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        this.context = context;
    }

    public void setupFirebase() {
        new FireBaseSetUpThread(context, this).execute();
    }

    public void onFirebaseSetupComplete() {
        Toast.makeText(context, "Firebase has been set up!", Toast.LENGTH_SHORT).show();
    }

    public void signUpUser(String email, String username, String password, String firstName, String lastName, String address) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {
            FirebaseUser user = authResult.getUser();
            if (user != null) {
                Map<String, Object> userData = new HashMap<>();
                userData.put("email", email); //Hash key and values
                userData.put("username", username);
                userData.put("firstName", firstName);
                userData.put("lastName", lastName);
                userData.put("address", address);
                db.collection("users").document(user.getUid()).set(userData).addOnSuccessListener(documentReference -> {
                    Toast.makeText(context, "User registered successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, LoginPage.class);
                    context.startActivity(intent);
                }).addOnFailureListener(e -> {
                    Toast.makeText(context, "Failed to register user", Toast.LENGTH_SHORT).show();
                });
            }
        }).addOnFailureListener(e -> {
            Toast.makeText(context, "Failed to register user", Toast.LENGTH_SHORT).show();
        });
    }

    public void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    db.collection("users").document(user.getUid()).get().addOnCompleteListener(task1 -> {
                        if (task1.isSuccessful()) {
                            Intent intent = new Intent(context, MainActivity.class);
                            context.startActivity(intent);
                            //ReusableMethods.showMessage(context, "Welcome" + );
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

    public String getCurrentUser() {
        return currentUser.getUid();
    }

    public Task<DocumentSnapshot> getUserData(String userId, OnCompleteListener<DocumentSnapshot> listener) {
        return db.collection("users").document(userId).get().addOnCompleteListener(listener);
    }
    public void logoutUser() {
        mAuth.signOut();
        Intent intent = new Intent(context, LoginPage.class);
        context.startActivity(intent);
        Toast.makeText(context, "User logged out successfully", Toast.LENGTH_SHORT).show();
    }

}
