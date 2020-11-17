package com.greenwich.madpropertypal.repository;

import android.content.Context;

import androidx.room.Room;

public class PropertyRepository {

    private String DATABASE_NAME = "property.db";


    private PropertyDatabase propertyDatabase;

    public PropertyRepository(Context context){
        propertyDatabase = Room.databaseBuilder(context,PropertyDatabase.class, DATABASE_NAME).build();
    }

}
