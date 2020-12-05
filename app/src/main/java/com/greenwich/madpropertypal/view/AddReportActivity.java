package com.greenwich.madpropertypal.view;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.greenwich.madpropertypal.R;
import com.greenwich.madpropertypal.data.ReportRepository;
import com.greenwich.madpropertypal.model.Property;
import com.greenwich.madpropertypal.model.Report;

import java.text.DateFormat;
import java.util.Calendar;

public class AddReportActivity extends AppCompatActivity{

    private static final String PROPERTY_EXTRA = "com.greenwich.madpropertypal.view.PROPERTY_EXTRA";

    private ReportRepository reportRepository;

    private Property property;

    private Calendar viewingDate;
    private Calendar offerExpiryDate;

    private TextView activeDateDisplay;
    private Calendar activeDate;

    static final int DATE_DIALOG_ID = 0;

    private Button chooseDateButton;
    private TextView tvViewingDate;
    private TextView tvOfferExpiryDate;
    private Spinner interestSpinner;
    private EditText etOfferPrice;
    private Button chooseExpiryDateButton;
    private EditText etConditionsOfOffer;
    private EditText etViewingComments;
    private Button addReportButton;
    private Button cancelButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_report);



        chooseDateButton = findViewById(R.id.chooseViewingDateButton);
        tvViewingDate = findViewById(R.id.tvViewingDate);
        tvOfferExpiryDate = findViewById(R.id.tvOfferExpiryDate);
        interestSpinner = findViewById(R.id.interestSpinner);
        etOfferPrice = findViewById(R.id.etOfferPrice);
        chooseExpiryDateButton = findViewById(R.id.chooseExpiryDateButton);
        etConditionsOfOffer = findViewById(R.id.etConditionsOfOffer);
        etViewingComments = findViewById(R.id.etViewingComments);
        addReportButton = findViewById(R.id.addReportButton);
        cancelButton = findViewById(R.id.cancelButton);

        reportRepository = new ReportRepository(this.getApplication());

        viewingDate = Calendar.getInstance();

        chooseDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(tvViewingDate, viewingDate);
            }
        });


        offerExpiryDate = Calendar.getInstance();

        chooseExpiryDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(tvOfferExpiryDate, offerExpiryDate);
            }
        });

        updateDisplay(tvViewingDate, viewingDate);
        updateDisplay(tvOfferExpiryDate, offerExpiryDate);

        addReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addReportButtonClicked();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Calendar calendar = Calendar.getInstance();

        String currentDate = DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar.getTime());
        tvViewingDate.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(calendar.get(Calendar.DAY_OF_MONTH)).append("/")
                        .append(calendar.get(Calendar.MONTH) + 1).append("/")
                        .append(calendar.get(Calendar.YEAR)).append(" "));

        tvOfferExpiryDate.setText(new StringBuilder()
                // Month is 0 based so add 1
                .append(calendar.get(Calendar.DAY_OF_MONTH)).append("/")
                .append(calendar.get(Calendar.MONTH) + 1).append("/")
                .append(calendar.get(Calendar.YEAR)).append(" "));
    }
    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            activeDate.set(Calendar.YEAR, year);
            activeDate.set(Calendar.MONTH, month);
            activeDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            updateDisplay(activeDateDisplay, activeDate);
            unregisterDateDisplay();


//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.YEAR, year);
//        calendar.set(Calendar.MONTH, month);
//        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//
//        String currentDateString = DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar.getTime());
//
//        tvViewingDate.setText(currentDateString);
        }
    };

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, dateSetListener, activeDate.get(Calendar.YEAR), activeDate.get(Calendar.MONTH), activeDate.get(Calendar.DAY_OF_MONTH));
        }
        return null;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        switch (id) {
            case DATE_DIALOG_ID:
                ((DatePickerDialog) dialog).updateDate(activeDate.get(Calendar.YEAR), activeDate.get(Calendar.MONTH), activeDate.get(Calendar.DAY_OF_MONTH));
                break;
        }
    }

    private void unregisterDateDisplay(){
        activeDateDisplay = null;
        activeDate = null;
    }



    public void showDateDialog(TextView tvDateField, Calendar date){
        activeDateDisplay = tvDateField;
        activeDate = date;
        showDialog(DATE_DIALOG_ID);
    }

    public Boolean areFieldsEmpty(EditText[] requiredETFields){
        boolean empty = false;

        for (EditText field : requiredETFields) {
            if(field.getText().toString().isEmpty()){
                field.setHintTextColor(Color.RED);
                empty = true;
            }
        }
        if(interestSpinner.getSelectedItemPosition() == 0 ){
            interestSpinner.setBackgroundColor(Color.RED);
            empty = true;
        }


        interestSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0){
                    interestSpinner.setBackgroundColor(Color.RED);

                } else {
                    interestSpinner.setBackgroundColor(Color.WHITE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                interestSpinner.setBackgroundColor(Color.WHITE);
            }
        });

        return empty;
    }


    public void addReportButtonClicked(){

        EditText[] requiredETFields = {etOfferPrice};
        if(areFieldsEmpty(requiredETFields)){

            Toast.makeText(this, "Please fill in required fields", Toast.LENGTH_LONG);
            return;
        }


        String interest = interestSpinner.getSelectedItem().toString();
//        String conditionsOfOffer = "";
//        String viewingcomments = "";
//
//
        try {
//
//            if(etConditionsOfOffer.getText().toString().isEmpty()){
//                conditionsOfOffer = null;
//            }
//            if(etViewingComments.getText().toString().isEmpty()){
//                viewingcomments = null;
//            }


            if(getIntent().hasExtra(PROPERTY_EXTRA)){

                 property = getIntent().getParcelableExtra(PROPERTY_EXTRA);

            }

            Report report = new Report(new java.sql.Date(viewingDate.getTimeInMillis()),interest, Double.parseDouble(etOfferPrice.getText().toString()), new java.sql.Date(offerExpiryDate.getTimeInMillis()), etConditionsOfOffer.getText().toString(),etViewingComments.getText().toString(), property.getId());

            reportRepository.insert(report);

            Toast.makeText(AddReportActivity.this, "Report created", Toast.LENGTH_SHORT);
            finish();
        } catch (Exception e){
            Toast.makeText(AddReportActivity.this, "Adding report unsuccessful", Toast.LENGTH_LONG);

            e.printStackTrace();
        }

    }


    public void updateDisplay(TextView dateField, Calendar date){
        dateField.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(date.get(Calendar.DAY_OF_MONTH)).append("/")
                        .append(date.get(Calendar.MONTH) + 1).append("/")
                        .append(date.get(Calendar.YEAR)).append(" "));
    }
}


//    public void chooseExpiryDateButtonClicked(){
//        DialogFragment expiryDatePicker = new DatePickerFragment();
//        expiryDatePicker.show(getSupportFragmentManager(), "expiry date picker");
//    }
//
//    public void chooseDateButtonClicked(){
//        DialogFragment datePicker = new DatePickerFragment();
//        datePicker.show(getSupportFragmentManager(), "date picker");
//    }