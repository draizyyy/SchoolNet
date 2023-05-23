package com.draizyyy.myreportcard.retrofit;

import com.draizyyy.myreportcard.pojos.Day;
import com.draizyyy.myreportcard.pojos.News;
import com.draizyyy.myreportcard.pojos.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    @GET("/days")  // команда на сервере
    Call<List<Day>> getAllDays();
    @GET("/news")
    Call<List<News>> getAllNews();
    @POST("/users")
    Call<User> addNewUser(@Body User user);
    @GET("/users/{mail}")
    Call<User> getUserByMail(@Path("mail") String mail);
}
