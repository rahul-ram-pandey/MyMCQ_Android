package fr.pesquer.mymcq;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import fr.pesquer.mymcq.View.Category.CategoryListFragment;
import fr.pesquer.mymcq.View.MCQ.MCQListFragment;
import fr.pesquer.mymcq.View.Other.RuleFragment;

public class MainActivity extends AppCompatActivity implements OnMenuTabClickListener {

    private BottomBar bottomBar;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isLogin();

        fragmentManager = getFragmentManager();

        bottomBar = BottomBar.attach(this, savedInstanceState);
        bottomBar.setItemsFromMenu(R.menu.bottombar_menu, this);
    }


    public void isLogin(){
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.prefs_file_key),this.MODE_PRIVATE);
        String userId = sharedPref.getString(getString(R.string.user_id_save),null);

        if(userId == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
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
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onMenuTabSelected(@IdRes int menuItemId) {
        switch (menuItemId){
            case R.id.bbRecent:
                fragmentTransaction = fragmentManager.beginTransaction();
                MCQListFragment fragment = new MCQListFragment();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
                break;
            case R.id.bbCategory:
                fragmentTransaction = fragmentManager.beginTransaction();
                CategoryListFragment categoryListFragment = new CategoryListFragment();
                fragmentTransaction.replace(R.id.fragment_container, categoryListFragment);
                fragmentTransaction.commit();
                break;
            case R.id.bbHelp:
                fragmentTransaction = fragmentManager.beginTransaction();
                RuleFragment helpFragment = new RuleFragment();
                fragmentTransaction.replace(R.id.fragment_container, helpFragment);
                fragmentTransaction.commit();
                break;
            default:
                break;
        }
    }

    @Override
    public void onMenuTabReSelected(@IdRes int menuItemId) {

    }
}
