package fr.pesquer.mymcq.View.Other;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.pesquer.mymcq.Data.CategoryRealm;
import fr.pesquer.mymcq.R;


public class RuleFragment extends Fragment {


    public RuleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rule, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        CategoryRealm request = new CategoryRealm();
        request.add("test");
    }

}
