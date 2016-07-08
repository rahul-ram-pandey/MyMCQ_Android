package fr.pesquer.mymcq.Webservice;


import android.content.Context;

import fr.pesquer.mymcq.R;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

public class UserWSAdapter {


    public static void login(String email, String password,Context context, Callback callback ) throws Exception {


        RequestBody param = new FormBody.Builder()
                .add(APIWSAdapter.User.EMAIL_FIELD, email)
                .add(APIWSAdapter.GRANT_TYPE, context.getString(R.string.security_password))
                .add(APIWSAdapter.CLIENT_ID, context.getString(R.string.security_client_id))
                .add(APIWSAdapter.CLIENT_PASSWORD, context.getString(R.string.security_client_password))
                .add(APIWSAdapter.User.PASSWORD_FIELD, password)
                .build();

        HttpUrl url = HttpUrl.parse(APIWSAdapter.BASE_URL+"/oauth/v2/token");
        Request request = new Request.Builder()
                .url(url)
                .post(param)
                .build();

        APIWSAdapter.client.newCall(request).enqueue(callback);
    }


    public static void getInfo(String token, Callback callback){
        HttpUrl url = HttpUrl.parse(APIWSAdapter.BASE_URL+"/user");
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer "+token)
                .build();

        APIWSAdapter.client.newCall(request).enqueue(callback);
    }

    public static void getNewToken(String refreshToken,Context context, Callback callback){
        RequestBody param = new FormBody.Builder()
                .add(APIWSAdapter.GRANT_TYPE, context.getString(R.string.security_refreshToken))
                .add(APIWSAdapter.CLIENT_ID, context.getString(R.string.security_client_id))
                .add(APIWSAdapter.CLIENT_PASSWORD, context.getString(R.string.security_client_password))
                .add(APIWSAdapter.User.TOKEN_REFRESH, refreshToken)
                .build();

        HttpUrl url = HttpUrl.parse(APIWSAdapter.BASE_URL+"/oauth/v2/token");
        Request request = new Request.Builder()
                .url(url)
                .post(param)
                .build();

        APIWSAdapter.client.newCall(request).enqueue(callback);
    }
}
