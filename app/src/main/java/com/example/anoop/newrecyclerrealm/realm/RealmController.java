package com.example.anoop.newrecyclerrealm.realm;


import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.util.Log;

import com.example.anoop.newrecyclerrealm.model.Person;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by anoop on 08/11/2016.
 */

public class RealmController {
    private static RealmController instance;
    private final Realm realm;

    public RealmController(Application application){
        realm=Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment){
        if(instance==null){
            instance = new RealmController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static RealmController with(Activity activity){
        if(instance==null) {
            instance = new RealmController(activity.getApplication());
        }
        return instance;
    }

    public static RealmController with(Application application){
        if(instance==null){
            instance = new RealmController(application);
        }
        return instance;
    }

    public static RealmController getInstance(){
        return instance;
    }

    public Realm getRealm(){
        return realm;
    }

    public void refresh(){
        realm.refresh();
    }

    //Clear DB
    public void clearAll(){
        realm.beginTransaction();
        realm.clear(Person.class);
        realm.commitTransaction();
    }

    //Return all DB information
    public RealmResults<Person> getPerson(){
        return realm.where(Person.class).findAll();
    }

    //Return result based on id, String?
    public Person getPerson(long id){
        return realm.where(Person.class).equalTo("id", id).findFirst();
    }

    public boolean hasPerson(){
        return !realm.allObjects(Person.class).isEmpty();
    }

    public boolean checkPersonID(long id){
        RealmResults<Person> query = realm.where(Person.class).equalTo("id", id).findAll();
        if(query.size()==0){
            Log.i("CHECK", "FALSE");
            return false;
        }

        Log.i("CHECK", "TRUE");
        return true;
    }
}
