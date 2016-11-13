package com.example.anoop.newrecyclerrealm.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import io.realm.RealmBaseAdapter;
import io.realm.RealmObject;

/**
 * Created by anoop on 08/11/2016.
 */

//An abstract class that simulates a RecyclerView Adaper, this will be used in PersonAdapter

public abstract  class RealmRecyclerViewAdapter<T extends RealmObject> extends RecyclerView.Adapter{
   private RealmBaseAdapter<T> realmBaseAdapter;

    public T getItem(int positon){
        return realmBaseAdapter.getItem(positon);
    }

    public RealmBaseAdapter<T> getRealmAdapter(){
        return realmBaseAdapter;
    }

    public void setRealmAdaper(RealmBaseAdapter<T> realmAdaper){
        realmBaseAdapter = realmAdaper;
    }

}
