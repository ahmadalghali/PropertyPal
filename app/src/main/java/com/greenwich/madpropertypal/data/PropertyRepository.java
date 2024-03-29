package com.greenwich.madpropertypal.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.greenwich.madpropertypal.model.Property;

import java.util.ArrayList;
import java.util.List;

public class PropertyRepository {


    private PropertyDao propertyDao;
    private LiveData<List<Property>> allProperties;
    private PropertyDatabase propertyDatabase;
    private List<Property> allActualProperties;


    public PropertyRepository(Application application){

        propertyDatabase =  PropertyDatabase.getPropertyDatabaseInstance(application);

        propertyDao = propertyDatabase.propertyDao();

        allProperties = propertyDao.getAllProperties();

//        allActualProperties = propertyDao.getAllActualProperties();
    }

    public void insert(Property property){
        new InsertPropertyAsyncTask(propertyDao).execute(property);
    }

    public void update(Property property){
        new UpdatePropertyAsyncTask(propertyDao).execute(property);
    }

    public void delete(Property property){
        new DeletePropertyAsyncTask(propertyDao).execute(property);
    }



    public void deleteAll(){
        new DeleteAllPropertiesAsyncTask(propertyDao).execute();
    }

    public LiveData<List<Property>> getAllProperties(){
        return allProperties;
    }

    public List<Property> getAllActualProperties(){

       new GetAllActualPropertiesAsyncTask(propertyDao).execute();
       return allActualProperties;
    }

    public LiveData<List<Property>> getMatchingProperties(String city, String propertyType, String bedroomCount){
        return propertyDao.getMatchingProperties( city,  propertyType,  bedroomCount);
    }

    public static class GetAllActualPropertiesAsyncTask extends AsyncTask<Void, Void, Void>{

        private PropertyDao propertyDao;
        private List<Property> allActualProperties;

        private GetAllActualPropertiesAsyncTask(PropertyDao propertyDao){
            this.propertyDao = propertyDao;
            allActualProperties = new ArrayList<>();
        }

        @Override
        protected Void doInBackground(Void... voids) {
             allActualProperties = propertyDao.getAllActualProperties();
             return null;
        }
    }

    public static class InsertPropertyAsyncTask extends AsyncTask<Property, Void, Void>{

        private PropertyDao propertyDao;

        private InsertPropertyAsyncTask(PropertyDao propertyDao){
            this.propertyDao = propertyDao;

        }

        @Override
        protected Void doInBackground(Property... properties) {
            propertyDao.insertProperty(properties[0]);
            return null;
        }
    }

    public static class UpdatePropertyAsyncTask extends AsyncTask<Property, Void, Void>{

        private PropertyDao propertyDao;

        private UpdatePropertyAsyncTask(PropertyDao propertyDao){
            this.propertyDao = propertyDao;

        }

        @Override
        protected Void doInBackground(Property... properties) {
            propertyDao.updateProperty(properties[0]);
            return null;
        }
    }

    public static class DeletePropertyAsyncTask extends AsyncTask<Property, Void, Void>{

        private PropertyDao propertyDao;

        private DeletePropertyAsyncTask(PropertyDao propertyDao){
            this.propertyDao = propertyDao;

        }

        @Override
        protected Void doInBackground(Property... properties) {
            propertyDao.deletePropertyById(properties[0].getId());
            return null;
        }
    }

    public static class DeleteAllPropertiesAsyncTask extends AsyncTask<Void, Void, Void>{

        private PropertyDao propertyDao;

        private DeleteAllPropertiesAsyncTask(PropertyDao propertyDao){
            this.propertyDao = propertyDao;

        }

        @Override
        protected Void doInBackground(Void... voids) {
            propertyDao.deleteAllProperties();
            return null;
        }
    }
}
