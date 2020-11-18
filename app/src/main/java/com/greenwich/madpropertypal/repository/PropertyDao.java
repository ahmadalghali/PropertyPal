package com.greenwich.madpropertypal.repository;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.greenwich.madpropertypal.model.Property;

import java.util.List;

public interface PropertyDao{


    String PROPERTY_TABLE = "property";

    @Insert
    void insertProperty(Property property);


    @Update
    void updateProperty(Property... property);

    @Query("SELECT * FROM " + PROPERTY_TABLE)
    LiveData<List<Property>> getAllProperties();

    @Query("SELECT * FROM " + PROPERTY_TABLE + " WHERE property.id = :propertyId" )
    Property findPropertyById(int propertyId);

    @Query("DELETE FROM " + PROPERTY_TABLE + " WHERE property.id = :propertyId" )
    void deletePropertyById(int propertyId);
}
