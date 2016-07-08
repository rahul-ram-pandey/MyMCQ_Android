package fr.pesquer.mymcq.Entity;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Question extends RealmObject {

    /**
     * Texte/URL de question en fonction du media
     */
    public String text;
    /**
     * Type de media utilisé : Text,Image,Sound,Video
     */
    public String media;
    /**
     * liste des reponses
     */
    public RealmList<Answer> answers;

    public int idWS;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public RealmList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(RealmList<Answer> answers) {
        this.answers = answers;
    }

    public int getIdWS() {
        return idWS;
    }

    public void setIdWS(int idWS) {
        this.idWS = idWS;
    }
}
