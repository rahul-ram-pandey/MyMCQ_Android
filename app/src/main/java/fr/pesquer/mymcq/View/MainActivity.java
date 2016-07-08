package fr.pesquer.mymcq.View;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import fr.pesquer.mymcq.Data.MCQRealm;
import fr.pesquer.mymcq.R;
import fr.pesquer.mymcq.Utils.Utils;
import fr.pesquer.mymcq.View.Category.CategoryListFragment;
import fr.pesquer.mymcq.View.MCQ.MCQListFragment;
import fr.pesquer.mymcq.View.Other.RuleFragment;
import fr.pesquer.mymcq.View.Question.QuestionFragment;
import fr.pesquer.mymcq.Webservice.UserWSAdapter;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements OnMenuTabClickListener {

    private BottomBar bottomBar;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Context context;

    public ArrayList<Fragment> arrayFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        arrayFragments = new ArrayList<>();
        setContentView(R.layout.activity_main);
        isLogin();
        loadDateFromWS();
        fragmentManager = getFragmentManager();


        bottomBar = BottomBar.attach(this, savedInstanceState);
        bottomBar.setItemsFromMenu(R.menu.bottombar_menu, this);
    }


    public void isLogin(){
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.prefs_file_key),this.MODE_PRIVATE);
        String userId = sharedPref.getString(getString(R.string.user_token),null);

        if(userId == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        bottomBar.onSaveInstanceState(outState);
    }

    /**
     * Method qui permet de passer d'un fragment à un autre
     * @param fragment Fragment que l'on veut
     */

    public void pushFragment(Fragment fragment) {
        fragmentTransaction = fragmentManager.beginTransaction();
        arrayFragments.add(fragment);
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    private void popFragment() {
        arrayFragments.remove(arrayFragments.size()-1);
        Fragment fragment  = arrayFragments.get(arrayFragments.size()-1);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onMenuTabSelected(@IdRes int menuItemId) {
        switch (menuItemId){
            case R.id.bbRecent:
                MCQListFragment mcqListFragment = new MCQListFragment();
                arrayFragments.clear();
                pushFragment(mcqListFragment);
                break;
            case R.id.bbCategory:
                CategoryListFragment categoryListFragment = new CategoryListFragment();
                arrayFragments.clear();
                pushFragment(categoryListFragment);
                break;
            case R.id.bbHelp:
                RuleFragment helpFragment = new RuleFragment();
                arrayFragments.clear();
                pushFragment(helpFragment);
                break;
            default:
                break;
        }
    }

    @Override
    public void onMenuTabReSelected(@IdRes int menuItemId) {

    }


    private void loadDateFromWS() {
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.prefs_file_key),Context.MODE_PRIVATE);
        String token = sharedPref.getString(getString(R.string.user_token), null);
        UserWSAdapter.getInfo(token, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful() && response.code() == 200) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        saveData(jsonObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    newToken();
                }
            }
        });
    }

    private void newToken() {
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.prefs_file_key),Context.MODE_PRIVATE);
        String resetToken = sharedPref.getString(getString(R.string.user_token_reset), null);
        if (resetToken == null){
            Intent intent = new Intent(context, LoginActivity.class);
            startActivity(intent);
            finish();
        }else{
            UserWSAdapter.getNewToken(resetToken, context, new Callback() {
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
                        loadDateFromWS();
                    }else{
                        Intent intent = new Intent(context, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if(arrayFragments.size() >= 2){
            final Fragment fragment = arrayFragments.get(arrayFragments.size()-1);
            if (fragment instanceof QuestionFragment){
                new AlertDialog.Builder(this)
                        .setTitle("Fin questionnaire")
                        .setMessage("Voulez vous finir le questionnaire?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                MCQListFragment mcqListFragment = new MCQListFragment();
                                pushFragment(mcqListFragment);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();
            }else{
                popFragment();
            }
        }else{
            super.onBackPressed();
        }
    }

    private void saveData(JSONObject jsonObject) throws JSONException {
        JSONArray jsonMCQ = jsonObject.getJSONArray("MCQ");
        MCQRealm mcqRealm = new MCQRealm();
        mcqRealm.deleteAll();
        mcqRealm.addFromJson(jsonMCQ);
    }
}
