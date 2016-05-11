package fr.pesquer.mymcq.Webservice;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class UserWSAdapter {

    public static void login (String email, String password, AsyncHttpResponseHandler responseHandler){
        RequestParams params = new RequestParams();
        params.put(APIWSAdapter.EMAIL_FIELD, email);
        params.put(APIWSAdapter.PASSWORD_FIELD, password);
        String URL = APIWSAdapter.BASE_URL+"login";
        APIWSAdapter.client.post(URL, params, responseHandler);
    }
}
