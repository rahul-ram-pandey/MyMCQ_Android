package fr.pesquer.mymcq.View.MCQ;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.pesquer.mymcq.Entity.MCQ;
import fr.pesquer.mymcq.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MCQFragment extends Fragment implements View.OnClickListener {

    public MCQ currentMCQ;

    public MCQFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mcq, container, false);



    }

    @Override
    public void onClick(View v) {

    }
}
