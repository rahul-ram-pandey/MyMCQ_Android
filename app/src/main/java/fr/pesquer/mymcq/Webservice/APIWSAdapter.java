package fr.pesquer.mymcq.Webservice;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class APIWSAdapter {

    public static final OkHttpClient client = new OkHttpClient.Builder()
                                                    .connectTimeout(60,TimeUnit.SECONDS)
                                                    .writeTimeout(60, TimeUnit.SECONDS)
                                                    .readTimeout(60, TimeUnit.SECONDS)
                                                    .build();


    public static final String BASE_URL = "http://vps279317.ovh.net/app_dev.php/api";
    public static final String GRANT_TYPE = "grant_type";
    public static final String CLIENT_ID = "client_id";
    public static final String CLIENT_PASSWORD = "client_secret";


    class User {
        public static final String PASSWORD_FIELD = "password";
        public static final String EMAIL_FIELD = "username";
        public static final String ID_USER_FIELD = "idUser";
        public static final String TOKEN_REFRESH = "refresh_token";
    }

    class Answer{
        public static final String DATA_FIELD = "data";
        public static final String MCQ_FIELD = "mcq";
    }






}
