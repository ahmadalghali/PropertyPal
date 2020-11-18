package com.greenwich.madpropertypal.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.greenwich.madpropertypal.model.Property;

import java.util.List;

public class PropertyRepository {


    private PropertyDao propertyDao;
    private LiveData<List<Property>> allProperties;

    private String DATABASE_NAME = "property.db";


    public PropertyRepository(Application application){
        PropertyDatabase propertyDatabase =  PropertyDatabase.getPropertyDatabaseInstance(application);

        propertyDao = propertyDatabase.propertyDao();

        allProperties = propertyDao.getAllProperties();
    }

}
