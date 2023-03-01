package Utils;

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

    public FireBaseUtils(){
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }
    public void signUpUser(String email, String username, String password, String firstName, String lastName, String address,
                           OnCompleteListener<AuthResult> onCompleteListener, OnFailureListener onFailureListener) {
        try{
            mAuth.createUserWithEmailAndPassword(email, password) //Use email og password para create
                    .addOnCompleteListener(onCompleteListener)
                    .addOnFailureListener(onFailureListener)
                    .addOnSuccessListener(authResult -> {
                        // save data
                        FirebaseUser user = authResult.getUser();
                        if (user != null) {
                            Map<String, Object> userData = new HashMap<>(); //Use hashmap for key and value
                            userData.put("email", email); //first parameter is the key, second is value
                            userData.put("username", username);
                            userData.put("firstName", firstName);
                            userData.put("lastName", lastName);
                            userData.put("address", address);
                            db.collection("users").document(user.getUid()).set(userData);
                        }
                    });
        }catch (Exception e){
            System.out.print(e);
        }
    }
    public void loginUser(String email, String password) {
        try{
            mAuth.signInWithEmailAndPassword(email, password);
        }catch (Exception e){
            System.out.print(e);
        }

    }
}
