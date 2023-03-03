package Utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.Objects;

public class ReusableMethods {

    public static void showMessage(Context context, String message) {
        try {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isEmpty(String component) {
        if (component != null) {
            return true;
        } else {
            return false;
        }
    }

    // Email validation method
    public static boolean isEmailValid(String email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        } else {
            return true;
        }
    }

    // Password validation method
    public static boolean isPasswordValid(String password) {
        return password != null && password.trim().length() >= 6;
    }
}
