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

}
