package fr.pesquer.mymcq.Webservice;

import org.json.JSONException;
import org.json.JSONObject;

import fr.pesquer.mymcq.Entity.MCQ;
import io.realm.Realm;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Request;

public class CategoryWSAdapter {

    private Realm realm;


    public CategoryWSAdapter() {
        realm = Realm.getDefaultInstance();
    }

    public static void getAll(String token, Callback callback){

        HttpUrl url = HttpUrl.parse(APIWSAdapter.BASE_URL+"/categories");
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer "+token)
                .build();

        APIWSAdapter.client.newCall(request).enqueue(callback);
    }


    public void addFromJson(final JSONObject jsonCat, MCQ mcq) throws JSONException {

    }

}
