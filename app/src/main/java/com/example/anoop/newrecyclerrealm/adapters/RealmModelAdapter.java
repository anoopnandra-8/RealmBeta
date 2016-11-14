package com.example.anoop.newrecyclerrealm.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import io.realm.RealmBaseAdapter;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by anoop on 08/11/2016.
 */

public class RealmModelAdapter <T extends RealmObject> extends RealmBaseAdapter {

    public RealmModelAdapter(Context context, RealmResults realmResults, boolean automaticUpdate) {
            super(context, realmResults);
        //  super(context, realmResults, automaticUpdate);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
