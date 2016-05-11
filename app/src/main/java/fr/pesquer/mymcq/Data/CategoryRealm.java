package fr.pesquer.mymcq.Data;

import fr.pesquer.mymcq.Entity.Category;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class CategoryRealm {

    public Realm realm;

    public CategoryRealm() {
        this.realm = Realm.getDefaultInstance();
    }


    /**
     * Ajoute une categorie en Base de données
     * @param name nom de la categorie.
     */
    public void add(final String name){
        this.realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Category category = realm.createObject(Category.class);
                category.setName(name);
            }
        });

    }

    public  RealmResults<Category> getAll(){
        RealmQuery<Category> query = realm.where(Category.class);
        RealmResults<Category> results = query.findAll();
        return results;
    }
}
