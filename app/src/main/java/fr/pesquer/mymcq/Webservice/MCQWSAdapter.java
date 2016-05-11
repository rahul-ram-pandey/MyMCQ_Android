package fr.pesquer.mymcq.Webservice;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class MCQWSAdapter {



    public static void getAllMCQ(int idUser, AsyncHttpResponseHandler responseHandler){
        RequestParams params = new RequestParams();
        params.put(APIWSAdapter.ID_USER_FIELD, idUser);
        //TODO : ajouter la bonne URL
        String URL = APIWSAdapter.BASE_URL;
        APIWSAdapter.client.post(URL, params, responseHandler);
    }
}
