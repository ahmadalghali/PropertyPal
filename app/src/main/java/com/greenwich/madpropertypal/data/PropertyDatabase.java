package com.greenwich.madpropertypal.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.greenwich.madpropertypal.model.Property;

@Database(entities = {Property.class}, version = 1)
public abstract class PropertyDatabase extends RoomDatabase {



    public abstract PropertyDao propertyDao();

    private static PropertyDatabase propertyDatabaseInstance;

    public static synchronized PropertyDatabase getPropertyDatabaseInstance(Context context){
        if(propertyDatabaseInstance == null){
            propertyDatabaseInstance = Room.databaseBuilder(context.getApplicationContext(),PropertyDatabase.class,
                    "madPropertyPal")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return propertyDatabaseInstance;
    }
    
}
