package com.greenwich.madpropertypal.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.greenwich.madpropertypal.R;
import com.greenwich.madpropertypal.model.Property;
import com.greenwich.madpropertypal.model.Report;
import com.greenwich.madpropertypal.view.adapter.PropertyReportsAdapter;
import com.greenwich.madpropertypal.viewmodel.ReportViewModel;

import java.util.List;

public class PropertyReports extends AppCompatActivity {

    private static final String PROPERTY_EXTRA = "com.greenwich.madpropertypal.view.PROPERTY_EXTRA";

    private ImageView homeIcon;

    private Property property;
    private FloatingActionButton addReportButton;
    private ReportViewModel reportViewModel;
    private TextView tvPropertyName;
    private RecyclerView recyclerView;
    private PropertyReportsAdapter propertyReportsAdapter;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_property_reports);

            assignGlobalVariables();
            initRecyclerView();
            createOnClickListeners();

        }


    private void assignGlobalVariables(){
        homeIcon = findViewById(R.id.homeIcon);

        tvPropertyName = findViewById(R.id.etPropertyName);
        recyclerView = findViewById(R.id.propertyReportsRecyclerView);
        addReportButton = findViewById(R.id.addReportButton);

    }

    private void createOnClickListeners(){

        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PropertyReports.this, MainActivity.class));
            }
        });

        addReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addReportButtonClicked();
            }
        });

    }

    private void initRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        propertyReportsAdapter = new PropertyReportsAdapter();
        recyclerView.setAdapter(propertyReportsAdapter);

        reportViewModel = new ViewModelProvider(this).get(ReportViewModel.class);

        if(getIntent().hasExtra(PROPERTY_EXTRA)){
            property = getIntent().getParcelableExtra(PROPERTY_EXTRA);

            tvPropertyName.setText(property.getName());
            reportViewModel.getPropertyReportsById(property.getId()).observe(this, new Observer<List<Report>>() {


                @Override
                public void onChanged(final List<Report> reports) {
                    //update recyclerview

                    propertyReportsAdapter.setReports(reports);

                }
            });
        }
    }

    private void addReportButtonClicked(){
        Intent intent = new Intent(this, AddReportActivity.class);

        if(getIntent().hasExtra(PROPERTY_EXTRA)){
            property = getIntent().getParcelableExtra(PROPERTY_EXTRA);
        }

        intent.putExtra(PROPERTY_EXTRA, (Parcelable) property);
        startActivity(intent);
    }

}