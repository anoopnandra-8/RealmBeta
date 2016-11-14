package com.example.anoop.newrecyclerrealm.app;

import com.example.anoop.newrecyclerrealm.model.Person;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;
import io.realm.internal.Table;

/**
 * Created by anoop on 13/11/2016.
 */

/*
public class MyMigration implements RealmMigration {
    @Override
    public long execute(Realm realm, long version) {
        if(version == 0){
            Table table = realm.getTable(Person.class);
            table.addColumn(ColumnType.INTEGER, "subID");
        }
        return version++;
    }
}
*/

public class MyMigration implements RealmMigration{

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();
        if(oldVersion == 0){
            schema.create("Person")
                    .addField("subID", int.class);
            oldVersion++;
        }
    }
}