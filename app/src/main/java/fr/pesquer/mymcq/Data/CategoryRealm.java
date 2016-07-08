package fr.pesquer.mymcq.Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    public void add(final String name, final int id){
        this.realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Category category = realm.createObject(Category.class);
                category.setName(name);

            }
        });

    }


    public void addFromJson(final JSONArray json){
        this.realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (int i = 0; i < json.length() ; i++) {
                    try {
                        JSONObject jsonCat = json.getJSONObject(i);
                        Category category = realm.createObject(Category.class);
                        category.setName(jsonCat.getString("name"));
                        category.setIdWS(jsonCat.getInt("id"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    public RealmResults<Category> getAll(){
        RealmQuery<Category> query = realm.where(Category.class);
        RealmResults<Category> results = query.findAll();
        return results;
    }

    public void deleteAll() {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Category> results = realm.where(Category.class).findAll();
                results.deleteAllFromRealm();
            }
        });
    }
}
