package fr.pesquer.mymcq.Webservice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.pesquer.mymcq.Entity.MCQ;
import fr.pesquer.mymcq.Entity.Question;
import io.realm.Realm;
import io.realm.RealmList;

public class QuestionWSAdapter {

    private Realm realm;


    public QuestionWSAdapter() {
        realm = Realm.getDefaultInstance();
    }

    public void addFromJson(final JSONArray jsonQuestions, MCQ mcq) throws JSONException {
        RealmList<Question> questions = new RealmList<>();
        for (int i = 0; i < jsonQuestions.length() ; i++) {
            try {
                JSONObject jsonQuestion = jsonQuestions.getJSONObject(i);
                Question question = realm.createObject(Question.class);
                question.setText(jsonQuestion.getString("text"));
                question.setIdWS(jsonQuestion.getInt("id"));

                questions.add(question);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        mcq.setQuestions(questions);
    }
}
