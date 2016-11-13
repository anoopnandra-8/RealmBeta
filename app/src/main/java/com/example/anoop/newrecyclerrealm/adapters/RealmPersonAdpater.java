package com.example.anoop.newrecyclerrealm.adapters;

import android.content.Context;

import com.example.anoop.newrecyclerrealm.adapters.RealmModelAdapter;
import com.example.anoop.newrecyclerrealm.model.Person;

import io.realm.RealmResults;

/**
 * Created by anoop on 08/11/2016.
 */

public class RealmPersonAdpater extends RealmModelAdapter<Person> {
    public RealmPersonAdpater(Context context, RealmResults realmResults, boolean automaticUpdate) {
        super(context, realmResults, automaticUpdate);
    }
}
