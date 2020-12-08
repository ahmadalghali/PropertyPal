package com.greenwich.madpropertypal.view.popup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.greenwich.madpropertypal.R;
import com.greenwich.madpropertypal.data.PropertyRepository;
import com.greenwich.madpropertypal.model.Property;
import com.greenwich.madpropertypal.view.MainActivity;

import java.text.NumberFormat;

public class ConfirmEditPropertyDetailsPopUp extends Activity {


    private PropertyRepository propertyRepository;

    private Button editButton;
    private Button confirmButton;

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

    private static final String PROPERTY_EXTRA = "com.greenwich.madpropertypal.view.PROPERTY_EXTRA";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_confirm_edit_property_details_pop_up);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .9), (int) (height * 0.9));




        propertyRepository = new PropertyRepository(this.getApplication());


        editButton = findViewById(R.id.editButton);
        confirmButton = findViewById(R.id.confirmButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editButtonClicked();
            }
        });
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmButtonClicked();
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
        description = findViewById(R.id.description);




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

    public void editButtonClicked(){
        finish();
    }

    public void confirmButtonClicked(){

        try{
            // update property in database

            propertyRepository.update(property);
            Toast.makeText(this, "Property updated.", Toast.LENGTH_LONG).show();

            finish();
            startActivity(new Intent(this, MainActivity.class));

        } catch(Exception e){
            Toast.makeText(this, "Error updating property ", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}