package fr.pesquer.mymcq.Entity;

import io.realm.RealmList;
import io.realm.RealmObject;

public class MCQ extends RealmObject {

    public String name;
    public RealmList<Question> questions;
    public Category category;
    public RealmList<User> users;
    public int idSource;
}
