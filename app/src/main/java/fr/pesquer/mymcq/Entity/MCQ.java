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
    public int idWS;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(RealmList<Question> questions) {
        this.questions = questions;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public RealmList<User> getUsers() {
        return users;
    }

    public void setUsers(RealmList<User> users) {
        this.users = users;
    }

    public int getIdWS() {
        return idWS;
    }

    public void setIdWS(int idWS) {
        this.idWS = idWS;
    }
}
