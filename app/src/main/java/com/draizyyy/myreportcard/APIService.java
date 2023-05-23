package com.draizyyy.myreportcard;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {
    @GET("/days")  // команда на сервере
    Call<List<Day>> getAllDays();
}
