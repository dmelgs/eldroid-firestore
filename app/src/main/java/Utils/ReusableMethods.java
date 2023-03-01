package Utils;

import android.content.Context;
import android.widget.Toast;

public class ReusableMethods {

    public static void showMessage(Context context,String message){
        try{
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
