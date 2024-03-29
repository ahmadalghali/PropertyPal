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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;
import com.greenwich.madpropertypal.R;
import com.greenwich.madpropertypal.model.Property;
import com.greenwich.madpropertypal.view.popup.ConfirmAddPropertyDetailsPopUp;

import java.util.ArrayList;

public class AddPropertyActivity extends AppCompatActivity {



    private ImageView homeIcon;
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
    private Button cancelButton;
    private Button addPropertyButton;


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

        setContentView(R.layout.activity_add_property);

        assignGlobalVariables();
        createOnClickListeners();
    }


    private void assignGlobalVariables(){
        homeIcon = findViewById(R.id.homeIcon);
        propertyNameEditText = findViewById(R.id.etPropertyName);
        propertyNumberEditText = findViewById(R.id.etPropertyNumber);
        propertyTypeSpinner = findViewById(R.id.propertyTypeSpinner);
        leaseTypeSpinner = findViewById(R.id.leaseTypeSpinner);
        sizeEditText = findViewById(R.id.etPropertySizeMetersSquared);
        streetEditText = findViewById(R.id.etStreet);
        postcodeEditText = findViewById(R.id.etPostcode);
        cityEditText = findViewById(R.id.etCity);
        bedroomCountEditText = findViewById(R.id.etBedroomCount);
        bathroomCountEditText = findViewById(R.id.etBathroomCount);
        askingPriceEditText = findViewById(R.id.etAskingPrice);
        localAmenitiesListView = findViewById(R.id.localAmenitiesListView);
        descriptionTextView = findViewById(R.id.etDescription);

        amenityEditText = findViewById(R.id.etAmenity);
        localAmenitiesListView = findViewById(R.id.localAmenitiesListView);


        propertyTypeSpinner.setSelection(1);
        leaseTypeSpinner.setSelection(1);


        amenitiesList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,amenitiesList);

        cancelButton = findViewById(R.id.cancelButton);
        addPropertyButton = findViewById(R.id.addPropertyButton);

        addAmenityButton = findViewById(R.id.addAmenityButton);


        localAmenitiesListView.setAdapter(arrayAdapter);

    }

    private void createOnClickListeners(){


        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddPropertyActivity.this, MainActivity.class));
            }
        });

        localAmenitiesListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
//                final int itemSelected = position;

                new AlertDialog.Builder(AddPropertyActivity.this)
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



        addAmenityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAmenityButtonClicked();
            }
        });



        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToHomeActivity();
            }
        });

        addPropertyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPropertyButtonClicked();
            }
        });
    }


    private void backToHomeActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void getUserInput(){
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

    }

    private Boolean inputIsValid(){

        if(areFieldsEmpty()){
            return false;
        }
        return true;
    }

    private void showRequiredFieldsAlert(){
        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);

        Snackbar.make(constraintLayout,"Please fill in required fields.", Snackbar.LENGTH_LONG).show();
    }

    public void addPropertyButtonClicked(){

        getUserInput();

        if(!inputIsValid()){
            showRequiredFieldsAlert();
        } else{
            showConfirmationPopup();
        }
    }

    private void showConfirmationPopup(){
        Intent intent = new Intent(AddPropertyActivity.this, ConfirmAddPropertyDetailsPopUp.class);

        Property property = new Property(propertyName,propertyNumber,propertyType,leaseType,Integer.parseInt(size),street,postcode,city,Integer.parseInt(bedroomCount),Integer.parseInt(bathroomCount),Double.parseDouble(askingPrice), description);
        intent.putExtra("property", (Parcelable) property);

        startActivity(intent);
    }


    private Boolean areFieldsEmpty(){

        EditText[] requiredETFields = {propertyNameEditText, propertyNumberEditText,  sizeEditText, streetEditText, postcodeEditText,cityEditText, bedroomCountEditText, bathroomCountEditText, askingPriceEditText}; //,


        boolean empty = false;


        for (EditText field : requiredETFields) {
            //Checking if each required field is empty
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
}