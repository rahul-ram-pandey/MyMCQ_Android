package fr.pesquer.mymcq.Data;

import io.realm.Realm;

public class RealmHelper {
    public static Realm realm;

    public RealmHelper() {
        realm = Realm.getDefaultInstance();
    }
}
