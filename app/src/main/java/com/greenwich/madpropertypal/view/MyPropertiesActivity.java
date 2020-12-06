package com.greenwich.madpropertypal.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.SearchView;

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

public class MyPropertiesActivity extends AppCompatActivity {


    private PropertyViewModel propertyViewModel;
    private ImageView advancedSearchImageViewButton;
    private SearchView searchView;

    private  MyPropertiesAdapter myPropertiesAdapter;

    private static final String PROPERTY_EXTRA = "com.greenwich.madpropertypal.view.PROPERTY_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_properties);


        searchView = findViewById(R.id.searchViewProperties);
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setFocusable(false);
        searchView.setIconified(false);
        searchView.clearFocus();

        advancedSearchImageViewButton = findViewById(R.id.advancedSettingsImageViewButton);
        advancedSearchImageViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               openAdvancedSearchActivity();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myPropertiesAdapter = new MyPropertiesAdapter();
        recyclerView.setAdapter(myPropertiesAdapter);

        propertyViewModel = new ViewModelProvider(this).get(PropertyViewModel.class);
        propertyViewModel.getAllProperties().observe(this, new Observer<List<Property>>() {


            @Override
            public void onChanged(final List<Property> properties) {
                //update recyclerview

                myPropertiesAdapter.setProperties(properties);

                myPropertiesAdapter.setOnPropertyClickedListener(new MyPropertiesAdapter.OnPropertyClickedListener() {
                    @Override
                    public void onPropertyClicked(int position) {
                        Property property = properties.get(position);

                        openPropertyDetailsActivity(property);
                    }
                });

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                myPropertiesAdapter.getFilter().filter(newText);
                return false;
            }
        });


        showMatchingProperties();

    }

    public void showMatchingProperties(){

        if(getIntent().hasExtra("city") || getIntent().hasExtra("propertyType") || getIntent().hasExtra("bedroomCount")  ){

            String city = getIntent().getStringExtra("city");
            String propertyType = getIntent().getStringExtra("propertyType");
            int bedroomCount = getIntent().getIntExtra("bedroomCount", 1);

//            LiveData<List<Property>> matchingProperties = propertyViewModel.getMatchingProperties(city, propertyType, bedroomCount);

            propertyViewModel.getMatchingProperties(city, propertyType, bedroomCount).observe(this, new Observer<List<Property>>() {


                @Override
                public void onChanged(final List<Property> properties) {
                    //update recyclerview

                    myPropertiesAdapter.setProperties(properties);

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

        return;
    }


    public void openAdvancedSearchActivity(){

        startActivity(new Intent(this, AdvancedSearchActivity.class));
    }

    public void openPropertyDetailsActivity(Property property){

        try{

            Intent intent = new Intent(this, PropertyDetails.class);

            intent.putExtra(PROPERTY_EXTRA, (Parcelable) property);
            startActivity(intent);
        } catch(Exception e){

            System.out.println("failed to start edit property activity");
        }

    }


}