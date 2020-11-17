package com.greenwich.madpropertypal.repository;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.greenwich.madpropertypal.model.Property;

@Database(entities = {Property.class}, version = 1)
public abstract class PropertyDatabase extends RoomDatabase {



    public abstract PropertyDao propertyDao();
    
}
