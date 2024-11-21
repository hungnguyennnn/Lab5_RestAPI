package com.example.lab5;

import com.example.lab5.Model.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("v3.1/all")
    Call<List<Country>> getAllCountries();
}
