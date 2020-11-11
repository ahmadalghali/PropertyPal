package com.greenwich.madpropertypal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    Button addPropertyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

//Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);









        ImageSlider imageSlider = findViewById(R.id.imageSlider);
        List<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.home1, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.home2, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.home3, ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(slideModels);

        addPropertyButton = findViewById(R.id.addPropertyButton);

        addPropertyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               openAddPropertyActivity();
            }
        });
    }


    public void openAddPropertyActivity(){
        Intent intent = new Intent(this, AddPropertyActivity.class);
        startActivity(intent);
    }
}