package com.greenwich.madpropertypal.web;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("madservlet")
    Call<ApiResponse> uploadProperties(@Body ApiRequest apiRequest);
}
