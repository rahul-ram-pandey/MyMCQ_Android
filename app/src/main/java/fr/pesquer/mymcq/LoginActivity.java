package fr.pesquer.mymcq;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bConnectLogin;
    private EditText etEmailLogin;
    private EditText etPasswordLogin;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.context = this;
        this.etEmailLogin = (EditText) findViewById(R.id.etEmailLogin);
        this.etPasswordLogin = (EditText) findViewById(R.id.etPasswordLogin);
        this.bConnectLogin = (Button) findViewById(R.id.bConnectLogin);

        if (this.bConnectLogin != null) {
            this.bConnectLogin.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bConnectLogin:
                String email = etEmailLogin.getText().toString();
                String password = etPasswordLogin.getText().toString();
                if (!email.isEmpty() && !password.isEmpty()){

                    test();
                    /*APIWSAdapter.login(email,password, new JsonHttpResponseHandler(){
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {


                            SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.prefs_file_key), MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString(getString(R.string.user_id_save), "te");
                            editor.apply();

                            Intent intent = new Intent(context, MainActivity.class);

                            startActivity(intent);

                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                            Toast.makeText(context, R.string.error_field_login, Toast.LENGTH_LONG).show();
                        }
                    });*/
                }else{
                    Toast.makeText(this, R.string.empty_field_login, Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    //TODO : A SUPPRIMER LORS DE LA CONNEXION AVEC LE WEBSERVICE
    public void test() {
        SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.prefs_file_key), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.user_id_save), "1");
        editor.apply();

        Intent intent = new Intent(context, MainActivity.class);

        startActivity(intent);
        finish();
    }

}
