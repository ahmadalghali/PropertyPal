package com.greenwich.madpropertypal.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.greenwich.madpropertypal.R;
import com.greenwich.madpropertypal.model.Property;

import java.text.NumberFormat;

public class PropertyDetails extends AppCompatActivity {


    private static final String PROPERTY_EXTRA = "com.greenwich.madpropertypal.view.PROPERTY_EXTRA";

    private ImageView homeIcon;

    private Button editButton;
    private Button reportsButton;

    private TextView propertyName;
    private TextView propertyNumber;
    private TextView propertyType;
    private TextView leaseType;
    private TextView size;
    private TextView street;
    private TextView postcode;
    private TextView city;
    private TextView bedroomCount;
    private TextView bathroomCount;
    private TextView askingPrice;
    private TextView localAmenities;
    private TextView description;

    private Property property;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_property_details);

        assignGlobalVariables();

        createOnClickListeners();

        displayPropertyDetails();
    }


    private void displayPropertyDetails(){
        if(getIntent().hasExtra(PROPERTY_EXTRA)){

            property = getIntent().getParcelableExtra(PROPERTY_EXTRA);


            propertyName.setText(property.getName());
            propertyNumber.setText(property.getNumber());
            propertyType.setText(property.getType());
            leaseType.setText(property.getLeaseType());
            size.setText(property.getSize() + " m²");
            street.setText(property.getStreet());
            postcode.setText(property.getPostcode());
            city.setText(property.getCity());
            bedroomCount.setText("" + property.getBedroomCount());
            bathroomCount.setText("" + property.getBathroomCount());
            askingPrice.setText("£" + NumberFormat.getInstance().format(property.getAskingPrice()));
            description.setText(property.getDescription());


//            if(property.getLocalAmenities().size() > 0){
//                String amenitiesFormatted = "";
//                for(String amenity : property.getLocalAmenities()){
//                    amenitiesFormatted += (amenity + ", ");
//                }
//                System.out.println("amenities  list" + property.getLocalAmenities());
//                System.out.println("formatted amenities " + amenitiesFormatted);
//                localAmenities.setText(amenitiesFormatted.substring(0,amenitiesFormatted.length()-2));
//            }

        }

    }

    private void createOnClickListeners(){

        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PropertyDetails.this, MainActivity.class));
            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editButtonClicked();
            }
        });
        reportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reportsButtonClicked();
            }
        });

    }

    private void assignGlobalVariables(){
        homeIcon = findViewById(R.id.homeIcon);

        editButton = findViewById(R.id.editButton);
        reportsButton = findViewById(R.id.reportsButton);

        propertyName = findViewById(R.id.propertyName);
        propertyNumber = findViewById(R.id.propertyNumber);
        propertyType = findViewById(R.id.propertyType);
        leaseType = findViewById(R.id.leaseType);
        size  = findViewById(R.id.size);
        street  = findViewById(R.id.street);
        postcode = findViewById(R.id.postcode);
        city = findViewById(R.id.city);
        bedroomCount = findViewById(R.id.bedroomCount);
        bathroomCount = findViewById(R.id.bathroomCount);
        askingPrice = findViewById(R.id.askingPrice);
        localAmenities = findViewById(R.id.localAmenities);
        description = findViewById(R.id.tvDescription);

    }

    private void editButtonClicked(){

        Intent intent = new Intent(this, EditPropertyDetails.class);
        intent.putExtra(PROPERTY_EXTRA, (Parcelable) property);
        startActivity(intent);
    }



    private void reportsButtonClicked(){

        Intent intent = new Intent(this, PropertyReports.class);
        intent.putExtra(PROPERTY_EXTRA, (Parcelable) property);
        startActivity(intent);

    }

}