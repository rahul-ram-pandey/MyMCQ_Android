package fr.pesquer.mymcq.Entity;

import io.realm.RealmList;
import io.realm.RealmObject;

public class MCQ extends RealmObject {

    /**
     * nom du questionnaire
     */
    public String name;

    /**
     * Liste des question du questionnaire
     */
    public RealmList<Question> questions;
    /**
     * Categorie du questionnaire
     */
    public Category category;
    /**
     * Liste des utilisateurs du questionnaire
     */
    public RealmList<User> users;
    /**
     * id du questionnaire sur le webservice
     */
    public int idSource;
}
