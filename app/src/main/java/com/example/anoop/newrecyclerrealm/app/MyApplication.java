package com.example.anoop.newrecyclerrealm.app;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by anoop on 08/11/2016.
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
         //       .deleteRealmIfMigrationNeeded()
                .migration(new MyMigration())
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

    }
}
