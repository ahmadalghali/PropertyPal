package com.greenwich.madpropertypal.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.greenwich.madpropertypal.model.Property;
import com.greenwich.madpropertypal.model.Report;

@Database(entities = {Property.class, Report.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class PropertyDatabase extends RoomDatabase {



    public abstract PropertyDao propertyDao();
    public abstract ReportDao reportDao();

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
