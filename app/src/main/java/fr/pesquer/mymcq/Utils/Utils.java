package fr.pesquer.mymcq.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import fr.pesquer.mymcq.R;
import okhttp3.Response;

public class Utils {

    public static void saveToken(Response response, Context context) throws JSONException, IOException {
        JSONObject json = new JSONObject(response.body().string());
        String access_token = json.getString("access_token");
        String refresh_token = json.getString("refresh_token");

        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.prefs_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(context.getString(R.string.user_token), access_token);
        editor.putString(context.getString(R.string.user_token_reset), refresh_token);
        editor.apply();
    }
}
