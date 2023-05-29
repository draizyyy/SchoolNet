package com.draizyyy.myreportcard.retrofit;

import android.util.Log;

import com.draizyyy.myreportcard.pojos.Day;
import com.draizyyy.myreportcard.pojos.Message;
import com.draizyyy.myreportcard.pojos.News;
import com.draizyyy.myreportcard.pojos.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService mInstance;
    private final APIService apiService;

//    private static final String BASE_URL = "http://10.0.2.2:8080/";
    private static final String BASE_URL = "http://192.168.0.102:4521";
    private final Retrofit mRetrofit;

    public NetworkService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = mRetrofit.create(APIService.class);
    }

    public static NetworkService getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkService();
        }
        return mInstance;
    }

    public void sendMessage(String message) {
        Call<String> call = apiService.sendMessageToServer(message);
        try {
            call.execute();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public APIService getJSONApi() {
        return mRetrofit.create(APIService.class);
    }
    public boolean addUser(User user) {
        Call<User> call = apiService.addNewUser(user);
        try {
            call.execute();
            return true;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return false;
    }
    public boolean isServerAccessible() {
        Call<Message> call = apiService.ping();
        try {
            Log.v("MY APP", "ping response");
            Response<Message> messageResponse = call.execute();
            Message message = messageResponse.body();
            Log.v("MY APP", "message: " + message.getMessage());
            return true;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return false;
    }
    public User getUserByMail(String mail) {
        Call<User> call = apiService.getUserByMail(mail);
        User user;
        try
        {
            Response<User> response = call.execute();
            user = response.body();
            if (user.name != null) {
                Log.d("MY APP", "user Response: user name: " + user.name + "user.surname: " + user.surname);
            }
            return user;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return new User("", "", "");
    }
    public List<News> getNews() {
        List<News> news;
        Call<List<News>> call = apiService.getAllNews();

        Log.v("MY APP", call.toString());

        try
        {
            Response<List<News>> response = call.execute();
            news = response.body();

            Log.d("MY APP", "mews.size(): " + news.size());
            return news;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }
    public List<Day> getDays() {
        List<Day> days;
        Call<List<Day>> call = apiService.getAllDays();

        Log.v("MY APP", call.toString());

        try
        {
            Response<List<Day>> response = call.execute();
            days = response.body();

            Log.d("MY APP", "days.size(): " + days.size());
            return days;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }
}
