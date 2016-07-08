package fr.pesquer.mymcq.View.Category;


import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import fr.pesquer.mymcq.Data.CategoryRealm;
import fr.pesquer.mymcq.Entity.Category;
import fr.pesquer.mymcq.View.MainActivity;
import fr.pesquer.mymcq.R;
import fr.pesquer.mymcq.View.MCQ.MCQListFragment;

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
        CategoryRealmAdapter adapter = new CategoryRealmAdapter(getActivity(), request.getAll());
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Category category = (Category) l.getItemAtPosition(position);
        MCQListFragment fragment = new MCQListFragment();
        fragment.category = category;

        ((MainActivity)getActivity()).pushFragment(fragment);
    }
}
