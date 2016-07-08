package fr.pesquer.mymcq.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;

import fr.pesquer.mymcq.R;
import fr.pesquer.mymcq.Utils.Utils;
import fr.pesquer.mymcq.Webservice.UserWSAdapter;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btConnectLogin;
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
        this.btConnectLogin = (Button) findViewById(R.id.bConnectLogin);

        if (this.btConnectLogin != null) {
            this.btConnectLogin.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bConnectLogin:
                String email = etEmailLogin.getText().toString();
                String password = etPasswordLogin.getText().toString();
                if (!email.isEmpty() && !password.isEmpty()) {
                    try {
                        UserWSAdapter.login(email, password, context, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                if (response.isSuccessful() && response.code() == 200){
                                    try {
                                        Utils.saveToken(response, context);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    Intent intent = new Intent(context, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else{
                                   Toast.makeText(context, R.string.empty_field_login, Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(this, R.string.empty_field_login, Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

}
