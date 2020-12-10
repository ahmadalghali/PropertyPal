package com.greenwich.madpropertypal.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.greenwich.madpropertypal.R;
import com.greenwich.madpropertypal.model.Property;
import com.greenwich.madpropertypal.view.adapter.MyPropertiesAdapter;
import com.greenwich.madpropertypal.viewmodel.PropertyViewModel;

import java.util.List;

public class AdvancedSearchActivity extends AppCompatActivity {

    private ImageView homeIcon;

    private Spinner spinnerCity;
    private Spinner spinnerPropertyType;
    private NumberPicker numberPickerBedrooms;
    private Button buttonAdvancedSearch;
    private TextView etMatchingProperties;
    private PropertyViewModel propertyViewModel;
    private MyPropertiesAdapter myPropertiesAdapter;
    private RecyclerView recyclerView;
    private CheckBox cbBedroomCountAny;
    private static final String PROPERTY_EXTRA = "com.greenwich.madpropertypal.view.PROPERTY_EXTRA";




    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_search);


        assignGlobalVariables();
        createOnClickListeners();


    }

    private void createOnClickListeners(){

        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdvancedSearchActivity.this, MainActivity.class));
            }
        });



        buttonAdvancedSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchButtonClicked();
            }
        });

        cbBedroomCountAny.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(cbBedroomCountAny.isChecked()){
                    numberPickerBedrooms.setEnabled(false);
                    numberPickerBedrooms.setTextColor(Color.DKGRAY);

                } else {
                    numberPickerBedrooms.setEnabled(true);
                    numberPickerBedrooms.setTextColor(Color.WHITE);

                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void assignGlobalVariables(){
        homeIcon = findViewById(R.id.homeIcon);

        spinnerCity = findViewById(R.id.spinnerCity);
        spinnerPropertyType = findViewById(R.id.spinnerPropertyType);
        numberPickerBedrooms = findViewById(R.id.numberPickerBedrooms);
        numberPickerBedrooms.setMinValue(1);
        numberPickerBedrooms.setMaxValue(5);
        numberPickerBedrooms.setTextColor(Color.WHITE);
        etMatchingProperties= findViewById(R.id.etMatchingProperties);
        cbBedroomCountAny = findViewById(R.id.cbBedroomCountAny);


        recyclerView = findViewById(R.id.matchingPropertiesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myPropertiesAdapter = new MyPropertiesAdapter();
        recyclerView.setAdapter(myPropertiesAdapter);

        propertyViewModel = new ViewModelProvider(this).get(PropertyViewModel.class);

        buttonAdvancedSearch = findViewById(R.id.buttonAdvancedSearch);

        cbBedroomCountAny.setChecked(true);
        numberPickerBedrooms.setEnabled(false);
        numberPickerBedrooms.setTextColor(Color.DKGRAY);


    }


    public void searchButtonClicked(){

        String city = spinnerCity.getSelectedItem().toString();
        String propertyType = spinnerPropertyType.getSelectedItem().toString();
        String bedroomCount = "";

        if(spinnerCity.getSelectedItemPosition() == 0 ){
            city = "%";
        }


        if(spinnerPropertyType.getSelectedItemPosition() == 0 ){
            propertyType = "%";
        }

        if(cbBedroomCountAny.isChecked()){
            bedroomCount = "%";
        } else {
            bedroomCount = Integer.toString(numberPickerBedrooms.getValue());
        }

        propertyViewModel.getMatchingProperties(city, propertyType, bedroomCount).observe(this, new Observer<List<Property>>() {


            @Override
            public void onChanged(final List<Property> properties) {
                //update recyclerview

                myPropertiesAdapter.setProperties(properties);
                etMatchingProperties.setText("Showing " + properties.size() + " matching properties");

                myPropertiesAdapter.setOnPropertyClickedListener(new MyPropertiesAdapter.OnPropertyClickedListener() {
                    @Override
                    public void onPropertyClicked(int position) {
                        Property property = properties.get(position);

                        openPropertyDetailsActivity(property);
                    }
                });

            }
        });

    }

    public void openPropertyDetailsActivity(Property property){

        try{

            Intent intent = new Intent(this, PropertyDetails.class);
            intent.putExtra(PROPERTY_EXTRA, (Parcelable) property);
            startActivity(intent);
        } catch(Exception e){

        }

    }

}