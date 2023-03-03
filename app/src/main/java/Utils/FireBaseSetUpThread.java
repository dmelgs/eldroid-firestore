package Utils;

import android.os.AsyncTask;

import com.eldroid.project.LoginPage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


public class FireBaseSetUpThread extends AsyncTask<Void, Void, Void> {
    private final FireBaseUtils firebase;
    private final Context context;

    public FireBaseSetUpThread(Context context, FireBaseUtils firebase) { //constructor to initialize context and firebase

        this.context = context;
        this.firebase = firebase;

    }

    @Override
    protected Void doInBackground(Void... voids) { //ang gamit ani is while ga run ang application nag run pod ni siya
        // Set up Firebase Auth
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            // Wala ra buhaton
        } else {
            // User is not logged in, redirect to login page
            Toast.makeText(context, "You are not signed in, Please login!!!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, LoginPage.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);

        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        // Notify that Firebase Auth has been set up
        firebase.onFirebaseSetupComplete();
    }
}
