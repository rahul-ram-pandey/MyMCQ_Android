package fr.pesquer.mymcq.View.Question;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;

import fr.pesquer.mymcq.Entity.Answer;
import fr.pesquer.mymcq.Entity.MCQ;
import fr.pesquer.mymcq.Entity.Question;
import fr.pesquer.mymcq.R;
import fr.pesquer.mymcq.View.MCQ.MCQListFragment;
import fr.pesquer.mymcq.View.MainActivity;
import fr.pesquer.mymcq.Webservice.AnswerWSAdapter;
import io.realm.RealmList;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class QuestionFragment extends Fragment implements View.OnClickListener {

    public MCQ mcq;
    private int current;
    private RealmList<Question> questions;
    public RealmList<Question> test;
    private RealmList<Answer> answers;
    private Question question;
    private ArrayList<ArrayList<Integer>> userAnswers;

    private ArrayList<CheckBox> checkboxList;

    private Button btNextQuestion;
    private Button btBackQuestion;
    private LinearLayout llCheckBox;
    private TextView tvQuestionNumber;
    private TextView tvTitleMCQ;

    public QuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        current = 0;
        questions = mcq.getQuestions();
        userAnswers = new ArrayList<>();
        tvQuestionNumber = (TextView) getView().findViewById(R.id.tvQuestionNumber);
        tvTitleMCQ = (TextView) getView().findViewById(R.id.tvTitleMCQ);
        btNextQuestion = (Button) getView().findViewById(R.id.btNextQuestion);
        btNextQuestion.setOnClickListener(this);
        btBackQuestion = (Button) getView().findViewById(R.id.btBackQuestion);
        btBackQuestion.setOnClickListener(this);
        llCheckBox = (LinearLayout) getView().findViewById(R.id.llCheckBox);
        checkboxList = new ArrayList<>();

        loadData();
    }

    private void loadData() {
        if (current+1 == questions.size()){
            btNextQuestion.setText("Fin");
        }else{
            btNextQuestion.setText("Suivant");
        }

        question = questions.get(current);
        answers = question.getAnswers();

        tvQuestionNumber.setText(String.format("%s %s", getString(R.string.questionText), current+1));
        tvTitleMCQ.setText(question.getText());

        if (current == 0){
            btBackQuestion.setVisibility(View.GONE);
        }else{
            btBackQuestion.setVisibility(View.VISIBLE);
        }

        for (int j=0; j<checkboxList.size(); j++){
            llCheckBox.removeView(checkboxList.get(j));
        }
        checkboxList.clear();
        for (int i=0; i<answers.size(); i++){
            CheckBox cb = new CheckBox(getActivity());
            Answer answer = answers.get(i);
            cb.setText(answer.getText());
            cb.setTag(answer);
            llCheckBox.addView(cb);
            checkboxList.add(cb);
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btNextQuestion :
                saveAnswer();
                if (current+1 == questions.size()){
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Fin questionnaire")
                            .setMessage("Voulez vous finir le questionnaire?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    MCQListFragment fragment = new MCQListFragment();
                                    ((MainActivity)getActivity()).pushFragment(fragment);
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .show();
                }else{
                    current++;
                    loadData();
                }
                break;
            case R.id.btBackQuestion :
                saveAnswer();
                current--;
                loadData();
                break;
        }
    }

    private void saveAnswer(){
        ArrayList<Integer> currentAnswer = new ArrayList<>();
        for (int i=0; i<answers.size(); i++){
            CheckBox currentCB = (CheckBox) llCheckBox.getChildAt(i);
            if(currentCB.isChecked()){
                Answer answer = (Answer) currentCB.getTag();
                currentAnswer.add(answer.getIdWS());
            }
        }
            userAnswers.add(current, currentAnswer);

    }

    @Override
    public void onStop() {
        super.onStop();
        saveData();
    }

    private void saveData() {
        SharedPreferences sharedPref = getActivity().getSharedPreferences(getString(R.string.prefs_file_key), Context.MODE_PRIVATE);
        String token = sharedPref.getString(getString(R.string.user_token), null);
        JSONArray json = new JSONArray(userAnswers);
        AnswerWSAdapter.postAnswer(mcq.getIdWS(),json.toString(), token, getActivity() ,  new Callback(){

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }
}
