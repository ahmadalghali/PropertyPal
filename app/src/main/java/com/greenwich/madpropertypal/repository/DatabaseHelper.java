package com.greenwich.madpropertypal.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.greenwich.madpropertypal.model.Property;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String PROPERTY_TABLE = "property";


    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String NUMBER = "number";
    private static final String TYPE = "type";
    private static final String LEASE_TYPE = "lease_" + TYPE;
    private static final String SIZE = "size";
    private static final String STREET = "street";
    private static final String POSTCODE = "postcode";
    private static final String CITY = "city";
    private static final String BEDROOM_COUNT = "bedroom_count";
    private static final String BATHROOM_COUNT = "bathroom_count";
    private static final String ASKING_PRICE = "asking_price";
    private static final String DESCRIPTION = "description";


    public DatabaseHelper(@Nullable Context context) {
        super(context,   "madPropertyPal.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + PROPERTY_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT," +
                " " + NUMBER + " TEXT, " + TYPE + " TEXT, " + LEASE_TYPE + " TEXT, " + SIZE + " INTEGER, " + STREET + " TEXT, " + POSTCODE + " TEXT, " + CITY + " TEXT, " + BEDROOM_COUNT + " INTEGER, " + BATHROOM_COUNT + " INTEGER, " +
                ASKING_PRICE + " REAL, " + DESCRIPTION + " TEXT )";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertProperty(Property property){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME, property.getName());
        contentValues.put(NUMBER, property.getNumber());
        contentValues.put(TYPE, property.getType());
        contentValues.put(LEASE_TYPE, property.getLeaseType());
        contentValues.put(SIZE, property.getSize());
        contentValues.put(STREET, property.getStreet());
        contentValues.put(POSTCODE, property.getPostcode());
        contentValues.put(CITY, property.getCity());
        contentValues.put(BEDROOM_COUNT, property.getBedroomCount());
        contentValues.put(BATHROOM_COUNT, property.getBathroomCount());
        contentValues.put(ASKING_PRICE, property.getAskingPrice());
        contentValues.put(DESCRIPTION, property.getDescription());

        long insert = db.insert(PROPERTY_TABLE, null, contentValues);

        if(insert == -1){
            return false;
        } else{
            return true;
        }
    }
}
