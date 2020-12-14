package com.greenwich.madpropertypal.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.greenwich.madpropertypal.R;
import com.greenwich.madpropertypal.model.Property;
import com.greenwich.madpropertypal.view.adapter.MyPropertiesAdapter;
import com.greenwich.madpropertypal.viewmodel.PropertyViewModel;
import com.greenwich.madpropertypal.web.ApiRequest;
import com.greenwich.madpropertypal.web.ApiResponse;
import com.greenwich.madpropertypal.web.ApiService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyPropertiesActivity extends AppCompatActivity {

    private ConstraintLayout myPropertiesLayout;
    private ImageView uploadPropertiesButton;
    private ApiService apiService;

    private List<Property> allProperties;
    private ImageView homeIcon;

    private PropertyViewModel propertyViewModel;
    private ImageView advancedSearchImageViewButton;
    private SearchView searchView;
    private MyPropertiesAdapter myPropertiesAdapter;
    private RecyclerView recyclerView;
    private Gson gson;

    private static final String PROPERTY_EXTRA = "com.greenwich.madpropertypal.view.PROPERTY_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_properties);

        assignGlobalVariables();
        createOnClickListeners();

        populateRecyclerView();

        showMatchingProperties();

//        initRetrofit();



    }


    public void uploadProperties(){
        try {
            URL url = new URL("http://gillwindallapp1.appspot.com/madservlet");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            gson = new Gson();


            String jsonString = gson.toJson(new ApiRequest("aa5119a", allProperties));

            JsonThread myTask = new JsonThread( this, con, jsonString);
            Thread t1 = new Thread(myTask, "JSON Thread");
            t1.start();

        } catch (IOException e) { e.printStackTrace(); }
    }

    private void initRetrofit(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gillwindallapp1.appspot.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        apiService = retrofit.create(ApiService.class);
    }


    public void displayApiResponse(ApiResponse response){
//        AlertDialog alertDialog = new AlertDialog.Builder(MyPropertiesActivity.this).create();
//        alertDialog.setTitle("Properties uploaded successfully");
//        alertDialog.setMessage("Upload Response Code: " + response.getUploadResponseCode() +"\n"
//                + "User id: " + response.getUserId() + "\n" +
//                "Properties uploaded: " + response.getNumber() + "\n"+
//                "Property names: " + response.getNames() + "\n" +
//                "Message: " + response.getMessage());
//
//
//
//        alertDialog.show();
//        new java.util.Timer().schedule(
//                new java.util.TimerTask() {
//                    @Override
//                    public void run() {
//                        alertDialog.dismiss();
//                    }
//                },
//                5000
//        );
    }

    class JsonThread implements Runnable
    {
        private AppCompatActivity activity;
        private HttpURLConnection con;
        private String jsonPayLoad;

        public JsonThread( AppCompatActivity activity, HttpURLConnection con, String jsonPayload)
        {
            this.activity = activity;
            this.con = con;
            this.jsonPayLoad = jsonPayload;
        }

        @Override
        public void run()
        {
            String response;
            if (prepareConnection()) {
                response = postJson();
            } else {
                response = "Error preparing the connection";
            }
            showResult(response);
        }


        private void showResult(String responseStr) {

            ApiResponse response = gson.fromJson(responseStr, ApiResponse.class);


            if(response.getUploadResponseCode().toLowerCase().equals("success")){

                activity.runOnUiThread(new Runnable() {
                    public void run() {
                        AlertDialog alertDialog = new AlertDialog.Builder(MyPropertiesActivity.this).create();
                        alertDialog.setTitle("Properties uploaded successfully");
                        alertDialog.setMessage(
                                "\nUpload Response Code: " + response.getUploadResponseCode() +"\n\n" +
                                "User id: " + response.getUserId() + "\n\n" +
                                "Properties uploaded: " + response.getNumber() + "\n\n"+
                                "Property names: " + response.getNames() + "\n\n" +
                                "Message: " + response.getMessage());
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });

                        alertDialog.show();

                    }
                });

            } else{
                Snackbar.make(myPropertiesLayout, "Properties could not be uploaded. Error code: " + response.getMessage(), BaseTransientBottomBar.LENGTH_LONG).show();
            }

            System.out.println(response);

        }

        private String postJson() {
            String response;
            try {
                String postParameters = "jsonpayload=" + URLEncoder.encode(jsonPayLoad, "UTF-8");
                con.setFixedLengthStreamingMode(postParameters.getBytes().length);
                PrintWriter out = new PrintWriter(con.getOutputStream());
                out.print(postParameters);
                out.close();
                int responseCode = con.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    response = readStream(con.getInputStream());
                } else {
                    response = "Error contacting server: " + responseCode;
                }
            } catch (Exception e) {
                response = "Error executing code";
            }
            return response;
        }

        private String readStream(InputStream in) {
            StringBuilder sb = new StringBuilder();
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                String nextLine;
                while ((nextLine = reader.readLine()) != null) {
                    sb.append(nextLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb.toString();
        }



        private boolean prepareConnection() {
            try {
                con.setDoOutput(true);
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                return true;

            } catch (ProtocolException e) {
                e.printStackTrace();
            }
            return false;
        }
    }

//    public void uploadProperties() {
//
//        JsonPayLoad jsonPayLoad = new JsonPayLoad("aa5119a", allProperties);
//
//        JSONObject jsonObject = new JSONObject();
//
//        try {
//            jsonObject.put("userId", "aa5119a");
//            jsonObject.put("detailList", allProperties.get(1));
//
//            Log.println(Log.INFO,"jsonObject", jsonObject.toString());
//
//            System.out.println(jsonObject);
//
//        } catch(JSONException e){
//            e.printStackTrace();
//        }
//
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        String url = "http://gillwindallapp1.appspot.com/madservlet";
////         StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new com.android.volley.Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//
//                Snackbar.make(myPropertiesLayout, response.toString(), BaseTransientBottomBar.LENGTH_INDEFINITE).show();
//
//            }
//        }, new com.android.volley.Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });
//
//        requestQueue.add(jsonObjectRequest);
////        requestQueue.start();
//    }


//    public void uploadProperties() {
//
//
//        Property[] properties = new Property[allProperties.size()];
//        allProperties.toArray(properties);
//
//        ApiRequest apiRequest = new ApiRequest("aa5119a", properties);
//
//
//        Call<ApiResponse> call = apiService.uploadProperties(apiRequest);
//
//        call.enqueue(new Callback<ApiResponse>() {
//            @Override
//            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
//
//                if(!response.isSuccessful()){
//                    Snackbar.make(myPropertiesLayout,  "Code: " + response.code(), BaseTransientBottomBar.LENGTH_LONG).show();
//                    return;
//                }
//
//                String s = response.body().toString();
//                try {
////                    JSONObject jsonObject = new JSONObject(s);
//                    Snackbar.make(myPropertiesLayout, response.body().toString() , BaseTransientBottomBar.LENGTH_LONG).show();
//                    System.out.println(response.body().toString());
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    Snackbar.make(myPropertiesLayout, e.toString(), BaseTransientBottomBar.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ApiResponse> call, Throwable t) {
//                Snackbar.make(myPropertiesLayout, t.getMessage(), BaseTransientBottomBar.LENGTH_LONG).show();
//            }
//        });
//
//    }


    private void createOnClickListeners(){


        uploadPropertiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadProperties();
            }
        });

        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyPropertiesActivity.this, MainActivity.class));
            }
        });

        advancedSearchImageViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdvancedSearchActivity();
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

    }

    private void populateRecyclerView(){
        propertyViewModel = new ViewModelProvider(this).get(PropertyViewModel.class);
        propertyViewModel.getAllProperties().observe(this, new Observer<List<Property>>() {


            @Override
            public void onChanged(final List<Property> properties) {
                //update recyclerview

                myPropertiesAdapter.setProperties(properties);

                allProperties = properties;

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

    private void assignGlobalVariables(){

        myPropertiesLayout = findViewById(R.id.myPropertiesLayout);
        uploadPropertiesButton = findViewById(R.id.uploadPropertiesButton);

        homeIcon = findViewById(R.id.homeIcon);

        searchView = findViewById(R.id.searchViewProperties);
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setFocusable(false);
        searchView.setIconified(false);
        searchView.clearFocus();
        advancedSearchImageViewButton = findViewById(R.id.advancedSettingsImageViewButton);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myPropertiesAdapter = new MyPropertiesAdapter();
        recyclerView.setAdapter(myPropertiesAdapter);

    }

    public void showMatchingProperties(){

        if(getIntent().hasExtra("city") || getIntent().hasExtra("propertyType") || getIntent().hasExtra("bedroomCount")  ){

            String city = getIntent().getStringExtra("city");
            String propertyType = getIntent().getStringExtra("propertyType");
            String bedroomCount = getIntent().getStringExtra("bedroomCount");


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

