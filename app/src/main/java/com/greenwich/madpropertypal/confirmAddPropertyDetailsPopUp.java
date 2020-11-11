package com.greenwich.madpropertypal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class confirmAddPropertyDetailsPopUp extends Activity {


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

//Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_confirm_add_property_details_pop_up);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .9), (int) (height * 0.8));



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

        String[] requiredFieldsInput = getIntent().getStringArrayExtra("requiredFieldsInput");

        propertyName.setText(requiredFieldsInput[0]);
        propertyNumber.setText(requiredFieldsInput[1]);
        propertyType.setText(requiredFieldsInput[2]);
        leaseType.setText(requiredFieldsInput[3]);
        size.setText(requiredFieldsInput[4] + " m²");
        street.setText(requiredFieldsInput[5]);
        postcode.setText(requiredFieldsInput[6]);
        city.setText(requiredFieldsInput[7]);
        bedroomCount.setText(requiredFieldsInput[8]);
        bathroomCount.setText(requiredFieldsInput[9]);
        askingPrice.setText("£" + requiredFieldsInput[10]);


    }

    public void editButtonClicked(){
        finish();
    }

    public void confirmButtonClicked(){

        // persist in database
    }
}