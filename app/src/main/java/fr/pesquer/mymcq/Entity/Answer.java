package fr.pesquer.mymcq.Entity;

import io.realm.RealmObject;

public class Answer extends RealmObject {

    /**
     * Texte de la reponse
     */
    public String text;

    public int idWS;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIdWS() {
        return idWS;
    }

    public void setIdWS(int idWS) {
        this.idWS = idWS;
    }
}
