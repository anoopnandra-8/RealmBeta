package com.example.anoop.newrecyclerrealm;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anoop.newrecyclerrealm.adapters.PersonAdapter;
import com.example.anoop.newrecyclerrealm.adapters.RealmPersonAdpater;
import com.example.anoop.newrecyclerrealm.model.Person;
import com.example.anoop.newrecyclerrealm.realm.RealmController;

import java.util.Random;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private PersonAdapter adapter;
    private Realm realm;
    EditText name, age, profession;
    Button saveData, listResults, deleteData;
    private RealmChangeListener realmChangeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.realm = RealmController.with(this).getRealm();

        name = (EditText) findViewById(R.id.name_et);
        age = (EditText) findViewById(R.id.age_et);
        profession = (EditText) findViewById(R.id.profession_et);
        saveData = (Button) findViewById(R.id.saveInformation_btn);
        listResults = (Button) findViewById(R.id.dbList_btn);
        deleteData = (Button) findViewById(R.id.delete_btn);

        Log.i("DBStatus", RealmController.with(this).getPerson().toString());

       // RealmController.with(this).refresh();

        realmChangeListener = new RealmChangeListener() {
            @Override
            public void onChange(Object element) {
                    RealmController.with(MainActivity.this).refresh();
            }
        };

        activateSave();
        activateListActivity();
        activateDelete();

    }

    public void activateSave(){
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Person person = new Person();
                boolean check = false;

                while (check == false){
                    check=true;
                    Random random = new Random();
                    long idRange = random.nextLong();

                    if(RealmController.getInstance().checkPersonID(idRange) == true){
                        check=false;
                    }else {
                        person.setId(idRange);
                    }
                }

                person.setName(name.getText().toString());
                person.setAge(Integer.parseInt(age.getText().toString()));
                person.setProfession(profession.getText().toString());
                person.setSubID(1);

                realm.beginTransaction();
                realm.copyToRealm(person);
                realm.commitTransaction();

                name.setHint("Name (Unique");
                age.setHint("Age");
                profession.setHint("Profession");

                Toast.makeText(MainActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();

                Log.i("DBSAVED_ID", String.valueOf(person.getId()));
                Log.i("DBSAVED_NAME", person.getName());
                Log.i("DBSAVED_AGE", String.valueOf(person.getAge()));
                Log.i("DBSAVED_PROF", person.getProfession());
            }
        });
    }

    private void activateListActivity() {
        listResults.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListDatabaseResults.class);
                startActivity(intent);
            }
        });

    }

    private void activateDelete(){
        deleteData.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                RealmController.with(MainActivity.this).clearAll();
            }
        });
    }
    }
