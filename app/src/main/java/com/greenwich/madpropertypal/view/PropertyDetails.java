package com.greenwich.madpropertypal.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.greenwich.madpropertypal.view.dialog.DeleteDialog;
import com.greenwich.madpropertypal.R;
import com.greenwich.madpropertypal.data.PropertyRepository;
import com.greenwich.madpropertypal.model.Property;

public class PropertyDetails extends AppCompatActivity implements DeleteDialog.DeleteDialogListener {


    private static final String PROPERTY_EXTRA = "com.greenwich.madpropertypal.view.PROPERTY_EXTRA";

    private PropertyRepository propertyRepository;

    private Button editButton;
    private Button deleteButton;
    private Button addReportButton;

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

            //Remove title bar
            this.requestWindowFeature(Window.FEATURE_NO_TITLE);
            //Remove notification bar
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.activity_property_details);

//            getSupportActionBar().setTitle(" ");
//            getSupportActionBar().setDisplayShowHomeEnabled(true);


            propertyRepository = new PropertyRepository(this.getApplication());


            editButton = findViewById(R.id.editButton);
            addReportButton = findViewById(R.id.addReportButton);
            deleteButton = findViewById(R.id.deleteButton);


            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editButtonClicked();
                }
            });
            addReportButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addReportButtonClicked();
                }
            });
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteButtonClicked();
                }
            });


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
                askingPrice.setText("£" + property.getAskingPrice());
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



    public void editButtonClicked(){

        Intent intent = new Intent(this, EditPropertyDetails.class);
        intent.putExtra(PROPERTY_EXTRA, (Parcelable) property);
        startActivity(intent);
    }


    public void deleteButtonClicked(){

        DeleteDialog deleteDialog = new DeleteDialog();
        deleteDialog.show(getSupportFragmentManager(),"Delete dialog");
    }

    public void deleteProperty(){
        try{
            // delete from  database
            propertyRepository.delete(property);
            finish();
            Toast.makeText(this, "Property deleted successfully.", Toast.LENGTH_SHORT).show();

        } catch(Exception e){
            Toast.makeText(this, "Error deleting property ", Toast.LENGTH_LONG).show();
        }
    }

    public void addReportButtonClicked(){

    }

    }