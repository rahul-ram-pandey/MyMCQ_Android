package fr.pesquer.mymcq.View.MCQ;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import fr.pesquer.mymcq.Entity.MCQ;
import fr.pesquer.mymcq.View.MainActivity;
import fr.pesquer.mymcq.R;
import fr.pesquer.mymcq.View.Question.QuestionFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MCQFragment extends Fragment implements View.OnClickListener {

    public MCQ currentMCQ;
    private TextView tvTitleMCQ;
    private TextView tvCategoryMCQ;


    private Button btStartMCQ;

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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tvTitleMCQ = (TextView) getView().findViewById(R.id.textView2);
        tvTitleMCQ.setText(currentMCQ.getName());

        btStartMCQ = (Button) getView().findViewById(R.id.btStartMCQ);

        btStartMCQ.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btStartMCQ:
                new AlertDialog.Builder(getActivity())
                        .setTitle("Début questionnaire")
                        .setMessage("Voulez vous commencer le questionnaire?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                QuestionFragment fragment = new QuestionFragment();
                                fragment.mcq = currentMCQ;
                                fragment.test = currentMCQ.getQuestions();
                                ((MainActivity)getActivity()).pushFragment(fragment);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .show();
                break;
        }
    }

    public void sendAnswers(){
        
    }

}
