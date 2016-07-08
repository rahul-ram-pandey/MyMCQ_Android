package fr.pesquer.mymcq.Data;

import io.realm.Realm;

public class QuestionRealm {
    private Realm realm;


    public QuestionRealm() {
        realm = Realm.getDefaultInstance();
    }


    /*public RealmResults<Question> getAllWithMCQ(MCQ mcq){
        RealmQuery<Question> query = realm.where(Question.class);
        query.equalTo("category.idWS", category.getIdWS());
        RealmResults<MCQ> results = query.findAll();
        return results;
    }*/
}
