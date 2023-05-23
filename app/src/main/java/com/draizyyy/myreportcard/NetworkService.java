package com.draizyyy.myreportcard;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService mInstance;
    APIService apiService;
    private static final String BASE_URL = "http://10.0.2.2:8080/";
    private final Retrofit mRetrofit;

    public NetworkService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkService();
        }
        return mInstance;
    }

    public APIService getJSONApi() {
        return mRetrofit.create(APIService.class);
    }
    public List<Day> getDays() {
        List<Day> days = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService apiService = retrofit.create(APIService.class);

        Call<List<Day>> call = apiService.getAllDays();

        Log.v("MY APP", call.toString());

        try
        {
            Response<List<Day>> response = call.execute();
            days = response.body();

            Log.d("MY APP", "days.size(): " + String.valueOf(days.size()));
            return days;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

//        call.enqueue(new Callback<List<Day>>() {
//            @Override
//            public void onResponse(Call<List<Day>> call,
//                                   Response<List<Day>> response) {
//
//                if (!response.isSuccessful()) {
//                    Log.e("MY APP", "not successfull response!");
//                    try {
//                        Log.e("MY APP", response.errorBody().string());
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                    return;
//                }
//
//                days.addAll(response.body());
//                days.get(0).toString();
//                Log.v("MY APP", "1 responce.body elem: " + days.get(0).toString());
//            }
//
//            @Override
//            public void onFailure(Call<List<Day>> call,
//                                  Throwable t) {
//                Log.e("MY APP", t.getMessage());
//            }
//        });
        return new ArrayList<Day>();
    }
}
