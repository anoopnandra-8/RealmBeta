package com.example.anoop.newrecyclerrealm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anoop.newrecyclerrealm.adapters.PersonAdapter;
import com.example.anoop.newrecyclerrealm.model.Person;
import com.example.anoop.newrecyclerrealm.realm.RealmController;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by anoop on 12/11/2016.
 */

public class UpdateDeleteDb extends AppCompatActivity {

    private EditText name_update, age_update, profession_update;
    private Button update_btn, delete_btn;
    private long id_db;
    private String name_db, profession_db, age_db;
    private Realm realm;
    private int listPosition;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_list);

        realm = RealmController.getInstance().getRealm();

        name_update = (EditText) findViewById(R.id.update_name);
        age_update = (EditText) findViewById(R.id.update_age);
        profession_update = (EditText) findViewById(R.id.update_profession);
        update_btn = (Button) findViewById(R.id.update_db_btn);
        delete_btn = (Button) findViewById(R.id.delete_db_btn);

        Bundle extras = getIntent().getExtras();
        id_db = extras.getLong("id");
        name_db = extras.getString("name");
        age_db = extras.getString("age");
        profession_db = extras.getString("profession");
        listPosition = extras.getInt("position");

        name_update.setText(name_db);
        age_update.setText(age_db);
        profession_update.setText(profession_db);

        Toast.makeText(UpdateDeleteDb.this, String.valueOf(listPosition), Toast.LENGTH_SHORT).show();

        updateDatabase();
        deleteRow();
    }

    private void updateDatabase(){
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RealmResults<Person> results = realm.where(Person.class).findAll();
                realm.beginTransaction();
                results.get(listPosition).setName(name_update.getText().toString());
                results.get(listPosition).setAge(Integer.parseInt(age_update.getText().toString()));
                results.get(listPosition).setProfession(profession_update.getText().toString());
                realm.commitTransaction();

                Intent intent = new Intent(UpdateDeleteDb.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void deleteRow(){
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RealmResults<Person> results = realm.where(Person.class).findAll();
                realm.beginTransaction();
                results.remove(listPosition);
                realm.commitTransaction();

                Intent intent = new Intent(UpdateDeleteDb.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
