package fr.pesquer.mymcq.Entity;

import io.realm.RealmObject;

public class Category extends RealmObject {

    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
