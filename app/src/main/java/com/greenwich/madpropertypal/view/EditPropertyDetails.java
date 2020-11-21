package com.greenwich.madpropertypal.view;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;
import com.greenwich.madpropertypal.R;
import com.greenwich.madpropertypal.data.PropertyRepository;
import com.greenwich.madpropertypal.model.Property;

import java.util.ArrayList;

public class EditPropertyDetails extends AppCompatActivity {

    private static final String PROPERTY_EXTRA = "com.greenwich.madpropertypal.view.PROPERTY_EXTRA";


    private PropertyRepository propertyRepository;


    private Property property;

    private EditText propertyNameEditText;
    private EditText propertyNumberEditText;
    private Spinner propertyTypeSpinner;
    private Spinner leaseTypeSpinner;
    private EditText sizeEditText;
    private EditText streetEditText;
    private EditText postcodeEditText;
    private EditText cityEditText;
    private EditText bedroomCountEditText;
    private EditText bathroomCountEditText;
    private EditText askingPriceEditText;
    private TextView descriptionTextView;
    private Button discardChangesButton;
    private Button saveButton;


    private String propertyName;
    private String propertyNumber;
    private String propertyType;
    private String leaseType;
    private String size;
    private String street;
    private String postcode;
    private String city;
    private String bedroomCount;
    private String bathroomCount;
    private String askingPrice;
    private String description;

    private Button addAmenityButton;
    private EditText amenityEditText;
    private ListView localAmenitiesListView;
    private ArrayList<String> amenitiesList;
    private ArrayAdapter<String> arrayAdapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_edit_property_details);


        discardChangesButton = findViewById(R.id.discardChangesButton);
        saveButton = findViewById(R.id.saveButton);
        propertyNameEditText   = findViewById(R.id.etPropertyName);
        propertyNumberEditText  = findViewById(R.id.etPropertyNumber);
        propertyTypeSpinner    = findViewById(R.id.propertyTypeSpinner);
        leaseTypeSpinner        = findViewById(R.id.leaseTypeSpinner);
        sizeEditText           = findViewById(R.id.etPropertySizeMetersSquared);
        streetEditText         = findViewById(R.id.etStreet);
        postcodeEditText         = findViewById(R.id.etPostcode);
        cityEditText            = findViewById(R.id.etCity);
        bedroomCountEditText     = findViewById(R.id.etBedroomCount);
        bathroomCountEditText   = findViewById(R.id.etBathroomCount);
        askingPriceEditText      = findViewById(R.id.etAskingPrice);
        localAmenitiesListView    = findViewById(R.id.localAmenitiesListView);
        descriptionTextView     = findViewById(R.id.etDescription);

        amenityEditText = findViewById(R.id.etAmenity);
        localAmenitiesListView = findViewById(R.id.localAmenitiesListView);

        setPropertyDetails();

        amenitiesList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,amenitiesList);

        localAmenitiesListView.setAdapter(arrayAdapter);

        localAmenitiesListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                new AlertDialog.Builder(com.greenwich.madpropertypal.view.EditPropertyDetails.this)
                        .setTitle("Delete item?")
                        .setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                amenitiesList.remove(position);
                                arrayAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();

                return true;
            }
        });


        addAmenityButton = findViewById(R.id.addAmenityButton);

        addAmenityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAmenityButtonClicked();
            }
        });




        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveButtonClicked();
            }
        });

        discardChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }

    public void setPropertyDetails(){
            if(getIntent().hasExtra(PROPERTY_EXTRA)){

                property = getIntent().getParcelableExtra(PROPERTY_EXTRA);

                propertyNameEditText.setText(property.getName());
                propertyNumberEditText.setText(property.getNumber());
                propertyTypeSpinner.setSelection(0);
                leaseTypeSpinner.setSelection(0);
                sizeEditText.setText(String.valueOf((int) property.getSize()));
                streetEditText.setText(property.getStreet());
                postcodeEditText.setText(property.getPostcode());
                cityEditText.setText(property.getCity());
                bedroomCountEditText.setText(String.valueOf(property.getBedroomCount()));
                bathroomCountEditText.setText(String.valueOf(property.getBathroomCount()));
                askingPriceEditText.setText(String.valueOf((int) property.getAskingPrice() + "0"));
//            localAmenitiesListView
                descriptionTextView.setText(property.getDescription());
            } else{
                System.out.println("property doesnt exist in intent, its not being passed");
            }
    }


    public void saveButtonClicked(){
        propertyName = propertyNameEditText.getText().toString();
        propertyNumber = propertyNumberEditText.getText().toString();
        propertyType = propertyTypeSpinner.getSelectedItem().toString();
        leaseType = leaseTypeSpinner.getSelectedItem().toString();
        size = sizeEditText.getText().toString();
        street = streetEditText.getText().toString();
        postcode = postcodeEditText.getText().toString();
        city = cityEditText.getText().toString();
        bedroomCount = bedroomCountEditText.getText().toString();
        bathroomCount = bathroomCountEditText.getText().toString();
        askingPrice = askingPriceEditText.getText().toString();
        description = descriptionTextView.getText().toString();



//        String[] requiredFieldsInput = {propertyName, propertyNumber, propertyType, leaseType, size, street, postcode,city, bedroomCount, bathroomCount, askingPrice};
        EditText[] requiredETFields = {propertyNameEditText, propertyNumberEditText,  sizeEditText, streetEditText, postcodeEditText,cityEditText, bedroomCountEditText, bathroomCountEditText, askingPriceEditText}; //,

        if(areFieldsEmpty(requiredETFields)){

            ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);

            Snackbar.make(constraintLayout,"Please fill in required fields.", Snackbar.LENGTH_LONG).show();

        } else{

            // get input and display in popup

            Intent intent = new Intent(EditPropertyDetails.this, ConfirmEditPropertyDetailsPopUp.class);

//
//            Property property = new Property(propertyName,propertyNumber,propertyType,leaseType,Integer.parseInt(size),street,postcode,city,Integer.parseInt(bedroomCount),Integer.parseInt(bathroomCount),Double.parseDouble(askingPrice), description);

            property.setName(propertyName);
            property.setNumber(propertyNumber);
            property.setType(propertyType);
            property.setLeaseType(leaseType);
            property.setSize(Integer.parseInt(size));
            property.setStreet(street);
            property.setPostcode(postcode);
            property.setCity(city);
            property.setBedroomCount(Integer.parseInt(bedroomCount));
            property.setBathroomCount(Integer.parseInt(bathroomCount));
            property.setAskingPrice(Double.parseDouble(askingPrice));
            property.setDescription(description);


            intent.putExtra(PROPERTY_EXTRA, (Parcelable) property);

            startActivity(intent);
        }

    }

    public Boolean areFieldsEmpty(EditText[] requiredETFields){
        boolean empty = false;

        for (EditText field : requiredETFields) {
            if(field.getText().toString().isEmpty()){
                field.setHintTextColor(Color.RED);
                empty = true;
            }
        }
        if(propertyTypeSpinner.getSelectedItemPosition() == 0 ){
            propertyTypeSpinner.setBackgroundColor(Color.RED);
            empty = true;
        }
        if(leaseTypeSpinner.getSelectedItemPosition() == 0 ){
            leaseTypeSpinner.setBackgroundColor(Color.RED);
            empty = true;
        }

        propertyTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0){
                    propertyTypeSpinner.setBackgroundColor(Color.RED);

                } else {
                    propertyTypeSpinner.setBackgroundColor(Color.WHITE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                propertyTypeSpinner.setBackgroundColor(Color.WHITE);
            }
        });

        leaseTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0){
                    leaseTypeSpinner.setBackgroundColor(Color.RED);

                } else {
                    leaseTypeSpinner.setBackgroundColor(Color.WHITE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                propertyTypeSpinner.setBackgroundColor(Color.WHITE);
            }
        });


        return empty;
    }


    public void addAmenityButtonClicked(){

        String amenity = amenityEditText.getText().toString();

        if(amenity.isEmpty()){
            return;
        } else {
            amenitiesList.add(amenity);
            arrayAdapter.notifyDataSetChanged();
            amenityEditText.setText("");
        }
    }


    public void confirmButtonClicked(){

        try{
            // persist in database


            propertyRepository.update(property);
            finish();
            startActivity(new Intent(this, MainActivity.class));
            Toast.makeText(this, "Property saved.", Toast.LENGTH_LONG).show();


//            DatabaseHelper databaseHelper = new DatabaseHelper(confirmAddPropertyDetailsPopUp.this);
//
//            databaseHelper.insertProperty(property);
        } catch(Exception e){
            Toast.makeText(this, "Error saving property ", Toast.LENGTH_LONG).show();
        }


    }
}