package fr.pesquer.mymcq.Data;

import fr.pesquer.mymcq.Entity.MCQ;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MCQRealm {

    private Realm realm;


    public MCQRealm() {
        realm = Realm.getDefaultInstance();
    }

    public RealmResults<MCQ> getAll(){
        RealmQuery<MCQ> query = realm.where(MCQ.class);
        RealmResults<MCQ> results = query.findAll();
        return results;
    }
}
