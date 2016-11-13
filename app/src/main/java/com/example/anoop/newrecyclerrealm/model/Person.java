package com.example.anoop.newrecyclerrealm.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by anoop on 08/11/2016.
 */

public class Person extends RealmObject {
    @PrimaryKey
    private long id;
    private String name;
    private int age;
    private String profession;
    private int subID;

    public void setId(long id){
        this.id=id;
    }

    public long getId(){
        return id;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public void setAge(int age){
        this.age=age;
    }

    public int getAge(){
        return age;
    }

    public void setProfession(String profession){
        this.profession=profession;
    }

    public String getProfession(){
        return profession;
    }

    public void setSubID(int subID){
        this.subID=subID;
    }

    public int getSubID(){
        return subID;
    }
}
