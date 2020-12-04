package com.greenwich.madpropertypal.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

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

//    private PropertyReports
    private static final String PROPERTY_EXTRA = "com.greenwich.madpropertypal.view.PROPERTY_EXTRA";
    private Property property;

    private FloatingActionButton addReportButton;
    private ReportViewModel reportViewModel;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_reports);

        RecyclerView recyclerView = findViewById(R.id.propertyReportsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final PropertyReportsAdapter propertyReportsAdapter = new PropertyReportsAdapter();
        recyclerView.setAdapter(propertyReportsAdapter);

        reportViewModel = new ViewModelProvider(this).get(ReportViewModel.class);
        reportViewModel.getAllReports().observe(this, new Observer<List<Report>>() {


            @Override
            public void onChanged(final List<Report> reports) {
                //update recyclerview

                propertyReportsAdapter.setReports(reports);

            }
        });



        addReportButton = findViewById(R.id.addReportButton);
        addReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addReportButtonClicked();
            }
        });
    }

    public void addReportButtonClicked(){
        Intent intent = new Intent(this, AddReportActivity.class);

        if(getIntent().hasExtra(PROPERTY_EXTRA)){
            property = getIntent().getParcelableExtra(PROPERTY_EXTRA);
        }

        intent.putExtra(PROPERTY_EXTRA, (Parcelable) property);
        startActivity(intent);
    }

}