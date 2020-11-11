package com.greenwich.madpropertypal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.List;

public class AddPropertyActivity extends AppCompatActivity {





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
    private ListView localAmenitiesListView;
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
    private List<String> localAmenities;
    private String description;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_add_property);

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
        localAmenitiesListView = findViewById(R.id.localAmentiesListView);
        descriptionTextView = findViewById(R.id.etDescription);


        cancelButton = findViewById(R.id.cancelButton);
        addPropertyButton = findViewById(R.id.addPropertyButton);

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

    public void backToHomeActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void addPropertyButtonClicked(){
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

        String[] requiredFieldsInput = {propertyName, propertyNumber, propertyType, leaseType, size, street, postcode,city, bedroomCount, bathroomCount, askingPrice};
        EditText[] requiredFields = {propertyNameEditText, propertyNumberEditText,  sizeEditText, streetEditText, postcodeEditText,cityEditText, bedroomCountEditText, bathroomCountEditText, askingPriceEditText}; //,
//
//        for (EditText field : requiredFields) {
//            if(field.getText().toString().matches("")){
//                field.setHintTextColor(Color.RED);
//            }
//        }
//
//        if(propertyTypeSpinner.getSelectedItemPosition() == 0 ){
//            propertyTypeSpinner.setBackgroundColor(Color.RED);
//        }
//        if(leaseTypeSpinner.getSelectedItemPosition() == 0 ){
//            leaseTypeSpinner.setBackgroundColor(Color.RED);
//        }
//        Toast.makeText(this, "Please fill in required fields.", Toast.LENGTH_LONG).show();


        // get input and display in popup
        Intent intent = new Intent(AddPropertyActivity.this, confirmAddPropertyDetailsPopUp.class);

        intent.putExtra("requiredFieldsInput", requiredFieldsInput);
//        intent.putExtra()
        startActivity(intent);

    }
}