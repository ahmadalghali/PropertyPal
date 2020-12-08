package com.greenwich.madpropertypal.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.greenwich.madpropertypal.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private Button addPropertyButton;
    private Button myPropertiesButton;
    private ImageSlider imageSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initImageSlider();
        initButtons();

    }

    private void initButtons(){
        addPropertyButton = findViewById(R.id.addPropertyButton);

        addPropertyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddPropertyActivity();
            }
        });

        myPropertiesButton = findViewById(R.id.myPropertiesButton);

        myPropertiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMyPropertiesActivity();
            }
        });
    }

    private void initImageSlider(){
        imageSlider = findViewById(R.id.imageSlider);
        List<SlideModel> slideModels = new ArrayList<>();


        slideModels.add(new SlideModel(R.drawable.home1, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.home2, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.home3, ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(slideModels);
    }

    public void openAddPropertyActivity(){
        Intent intent = new Intent(this, AddPropertyActivity.class);
        startActivity(intent);
    }

    public void openMyPropertiesActivity(){
        Intent intent = new Intent(this, MyPropertiesActivity.class);
        startActivity(intent);
    }
}