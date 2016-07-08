package fr.pesquer.mymcq.Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.pesquer.mymcq.Entity.Answer;
import fr.pesquer.mymcq.Entity.Category;
import fr.pesquer.mymcq.Entity.MCQ;
import fr.pesquer.mymcq.Entity.Question;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MCQRealm {

    private Realm realm;


    public MCQRealm() {
        realm = Realm.getDefaultInstance();
    }

    /**
     * récupère la liste des questionnaires sur la base.
     *
     * @return Liste des questionnaires
     */
    public RealmResults<MCQ> getAll(){
        RealmQuery<MCQ> query = realm.where(MCQ.class);
        RealmResults<MCQ> results = query.findAll();
        return results;
    }

    public RealmResults<MCQ> getAllWithCategory(Category category){
        RealmQuery<MCQ> query = realm.where(MCQ.class);
        query.equalTo("category.idWS", category.getIdWS());
        RealmResults<MCQ> results = query.findAll();
        return results;
    }


    public void addFromJson(final JSONArray json) {
        this.realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                MCQ mcq;
                for (int i = 0; i < json.length() ; i++) {
                    try {
                        JSONObject jsonMCQ = json.getJSONObject(i);

                        RealmResults<MCQ> result = realm.where(MCQ.class)
                                .equalTo("idWS", jsonMCQ.getInt("id"))
                                .findAll();

                        if (result.size() == 0){
                            mcq = realm.createObject(MCQ.class);
                            mcq.setName(jsonMCQ.getString("name"));
                            mcq.setIdWS(jsonMCQ.getInt("id"));
                        }else{
                           mcq = result.first();
                        }

                        JSONObject jsonCat = jsonMCQ.getJSONObject("category");

                        RealmResults<Category> resultCat = realm.where(Category.class)
                                .equalTo("idWS", jsonCat.getInt("id"))
                                .findAll();

                        if (resultCat.size() == 0){
                            Category category = realm.createObject(Category.class);
                            category.setName(jsonCat.getString("name"));
                            category.setIdWS(jsonCat.getInt("id"));
                            mcq.setCategory(category);
                        }else{
                            Category category = resultCat.first();
                            mcq.setCategory(category);
                        }

                        JSONArray jsonQuestions = jsonMCQ.getJSONArray("question");

                        RealmList<Question> questions = new RealmList<>();
                        for (int j = 0; j < jsonQuestions.length() ; j++) {
                            try {
                                JSONObject jsonQuestion = jsonQuestions.getJSONObject(j);
                                Question question = realm.createObject(Question.class);
                                question.setText(jsonQuestion.getString("text"));
                                question.setIdWS(jsonQuestion.getInt("id"));

                                JSONArray jsonAnswers = jsonQuestion.getJSONArray("answer");

                                RealmList<Answer> answers = new RealmList<>();
                                for (int k= 0; k < jsonAnswers.length(); k++){
                                    JSONObject jsonAnswer = jsonAnswers.getJSONObject(k);
                                    Answer answer = realm.createObject(Answer.class);
                                    answer.setText(jsonAnswer.getString("content"));
                                    answer.setIdWS(jsonAnswer.getInt("id"));
                                    answers.add(answer);
                                }
                                question.setAnswers(answers);
                                questions.add(question);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        mcq.setQuestions(questions);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void deleteAll() {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<MCQ> results = realm.where(MCQ.class).findAll();
                results.deleteAllFromRealm();
            }
        });
    }
}
