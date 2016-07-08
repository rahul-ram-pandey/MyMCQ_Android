package fr.pesquer.mymcq.Webservice;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import fr.pesquer.mymcq.Entity.Category;
import fr.pesquer.mymcq.Entity.MCQ;
import io.realm.Realm;
import io.realm.RealmResults;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

public class AnswerWSAdapter {

    private Realm realm;


    public AnswerWSAdapter() {
        realm = Realm.getDefaultInstance();
    }

    public static void postAnswer(int idWS, String data, String token, Context context, Callback callback){
        RequestBody param = new FormBody.Builder()
                .add(APIWSAdapter.Answer.DATA_FIELD, data)
                .add(APIWSAdapter.Answer.MCQ_FIELD, String.valueOf(idWS))
                .build();

        HttpUrl url = HttpUrl.parse(APIWSAdapter.BASE_URL+"/answers");
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer "+token)
                .post(param)
                .build();

        APIWSAdapter.client.newCall(request).enqueue(callback);
    }

    public void addFromJson(final JSONObject jsonCat, MCQ mcq) throws JSONException {
        RealmResults<Category> resultCat = realm.where(Category.class)
                .equalTo("idWS", jsonCat.getInt("id"))
                .findAll();

        if (resultCat.size() == 0){
            Category category = realm.createObject(Category.class);
            category.setName(jsonCat.getString("name"));
            category.setIdWS(jsonCat.getInt("id"));
            mcq.setCategory(category);
        }else{
            Category category = resultCat.first();
            mcq.setCategory(category);
        }
    }

}
