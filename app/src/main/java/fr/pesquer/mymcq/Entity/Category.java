package fr.pesquer.mymcq.Entity;


import io.realm.RealmObject;

public class Category extends RealmObject {

    /**
     * Nom de la categorie
     */
    public String name;

    public int idWS;

    public int getIdWS() {
        return idWS;
    }

    public void setIdWS(int idWS) {
        this.idWS = idWS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
