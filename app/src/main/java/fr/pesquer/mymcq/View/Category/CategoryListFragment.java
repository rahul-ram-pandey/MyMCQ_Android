package fr.pesquer.mymcq.View.Category;


import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.pesquer.mymcq.Data.CategoryRealm;
import fr.pesquer.mymcq.R;

public class CategoryListFragment extends ListFragment {


    public CategoryListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categorylist, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        CategoryRealm request = new CategoryRealm();
        CategoryRealmAdapter adapter = new CategoryRealmAdapter(getActivity(), request.getAll(), false);
        setListAdapter(adapter);
    }
}
