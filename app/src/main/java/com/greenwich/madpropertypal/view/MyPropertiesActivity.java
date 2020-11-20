package com.greenwich.madpropertypal.view;

import android.os.Bundle;

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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_properties);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final MyPropertiesAdapter myPropertiesAdapter = new MyPropertiesAdapter();
        recyclerView.setAdapter(myPropertiesAdapter);

        propertyViewModel = new ViewModelProvider(this).get(PropertyViewModel.class);
        propertyViewModel.getAllProperties().observe(this, new Observer<List<Property>>() {


            @Override
            public void onChanged(List<Property> properties) {
                //update recyclerview

                myPropertiesAdapter.setProperties(properties);

            }
        });


    }
}