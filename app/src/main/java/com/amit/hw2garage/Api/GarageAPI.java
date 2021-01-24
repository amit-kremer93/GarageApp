package com.amit.hw2garage.Api;

import com.amit.hw2garage.Model.GarageModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GarageAPI {
    @GET("WypPzJCt")
    Call<GarageModel> loadGarage();
}
