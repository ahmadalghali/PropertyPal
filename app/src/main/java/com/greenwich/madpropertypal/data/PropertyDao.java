package com.greenwich.madpropertypal.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.greenwich.madpropertypal.model.Property;

import java.util.List;

@Dao
public interface PropertyDao{


    String PROPERTY_TABLE = "property";

    @Insert
    void insertProperty(Property property);


    @Update
    void updateProperty(Property... property);

    @Query("SELECT * FROM " + PROPERTY_TABLE + " ORDER BY name DESC")
    LiveData<List<Property>> getAllProperties();

    @Query("SELECT * FROM " + PROPERTY_TABLE + " WHERE property.id = :propertyId" )
    Property findPropertyById(int propertyId);

    @Query("DELETE FROM " + PROPERTY_TABLE + " WHERE property.id = :propertyId" )
    void deletePropertyById(int propertyId);

    @Query("DELETE FROM " + PROPERTY_TABLE)
    void deleteAllProperties();

    @Query("SELECT * FROM " + PROPERTY_TABLE + " WHERE city LIKE :city AND type LIKE :propertyType AND bedroomCount LIKE :bedroomCount")
    LiveData<List<Property>> getMatchingProperties(String city, String propertyType, String bedroomCount);

}
