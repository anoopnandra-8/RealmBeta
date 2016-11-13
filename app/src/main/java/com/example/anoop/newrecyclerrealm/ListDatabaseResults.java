package com.example.anoop.newrecyclerrealm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.example.anoop.newrecyclerrealm.adapters.PersonAdapter;
import com.example.anoop.newrecyclerrealm.adapters.RealmPersonAdpater;
import com.example.anoop.newrecyclerrealm.model.Person;
import com.example.anoop.newrecyclerrealm.realm.RealmController;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by anoop on 08/11/2016.
 */

public class ListDatabaseResults extends AppCompatActivity{

    private PersonAdapter adapter;
    private Realm realm;
    private LayoutInflater inflater;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_content_recycler);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        this.realm = RealmController.with(this).getRealm();

        setUpRecycler();

        RealmController.with(this).refresh();

        setUpRealmAdapter(RealmController.with(this).getPerson());

    }

    private void setUpRealmAdapter(RealmResults<Person> person) {
        RealmPersonAdpater realmAdapter = new RealmPersonAdpater(this.getApplicationContext(), person, true);
        adapter.setRealmAdaper(realmAdapter);
        adapter.notifyDataSetChanged();
    }

    private void setUpRecycler() {
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PersonAdapter(this);
        recyclerView.setAdapter(adapter);
    }



}
