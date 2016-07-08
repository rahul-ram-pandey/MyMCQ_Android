package fr.pesquer.mymcq.Webservice;

import java.util.ArrayList;

import fr.pesquer.mymcq.Entity.MCQ;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

public class MCQWSAdapter {

    public MCQ mcq;


    public static void getAllMCQ(String token, Callback callback){
        HttpUrl url = HttpUrl.parse(APIWSAdapter.BASE_URL+"/mcqs");
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer "+token)
                .build();

        APIWSAdapter.client.newCall(request).enqueue(callback);

    }

    public static void setAnswerMcq(String token, Callback callback, MCQ mcq, ArrayList answers){
        RequestBody param = new FormBody.Builder()
                .add(APIWSAdapter.User.EMAIL_FIELD, token)
                .build();

        HttpUrl url = HttpUrl.parse(APIWSAdapter.BASE_URL+"/user");
        Request request = new Request.Builder()
                .url(url)
                .post(param)
                .addHeader("Authorization", "Bearer "+token)
                .build();

        APIWSAdapter.client.newCall(request).enqueue(callback);
    }


}
