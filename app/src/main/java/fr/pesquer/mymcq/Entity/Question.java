package fr.pesquer.mymcq.Entity;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Question extends RealmObject {

    public String text;
    public String media;
    public RealmList<Answer> answers;

}
