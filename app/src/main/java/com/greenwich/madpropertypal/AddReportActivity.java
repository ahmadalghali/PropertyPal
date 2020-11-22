package com.greenwich.madpropertypal;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.greenwich.madpropertypal.view.fragment.DatePickerFragment;

import java.text.DateFormat;
import java.util.Calendar;

public class AddReportActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Button chooseDateButton;
    private TextView tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_report);

        chooseDateButton = findViewById(R.id.chooseDateButton);
        tvDate = findViewById(R.id.tvDate);

        chooseDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDateButtonClicked();
            }
        });

        Calendar calendar = Calendar.getInstance();

        String currentDate = DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar.getTime());
        tvDate.setText(currentDate);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String currentDateString = DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar.getTime());

        tvDate.setText(currentDateString);
    }

    public void chooseDateButtonClicked(){
        DialogFragment datePicker = new DatePickerFragment();
        datePicker.show(getSupportFragmentManager(), "date picker");
    }
}