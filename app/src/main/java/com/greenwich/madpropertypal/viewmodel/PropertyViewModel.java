package com.greenwich.madpropertypal.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.greenwich.madpropertypal.data.PropertyRepository;
import com.greenwich.madpropertypal.model.Property;

import java.util.List;

public class PropertyViewModel extends AndroidViewModel {

    private PropertyRepository propertyRepository;
    private LiveData<List<Property>> allProperties;


    public PropertyViewModel(@NonNull Application application) {
        super(application);

        propertyRepository = new PropertyRepository(application);

        allProperties = propertyRepository.getAllProperties();
    }


    public void insert(Property property){
        propertyRepository.insert(property);
    }

    public void update(Property property){
        propertyRepository.update(property);
    }

    public void delete(Property property){
        propertyRepository.delete(property);
    }

    public void deleteAll(){
        propertyRepository.deleteAll();
    }

    public LiveData<List<Property>> getAllProperties(){
        return  allProperties;
    }
}
