package com.example.feitinsta.Service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GService {
    public static Retrofit retrofitClient;

    public static GithubService service;

    public static void initalizeRetrofit()
    {
        retrofitClient = new Retrofit.Builder()
                .baseUrl("http://5ddfd7adbb46ce001434bca8.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofitClient.create(GithubService.class);
    }
}