package com.nc.poetry_app.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public  static Retrofit retrofit=null;
    static  final  String url="http://192.168.43.71:8080/poetry/";


    public  static  Retrofit getClinet(){
        if(retrofit==null) {
            Gson gson = new GsonBuilder().create();
            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
            retrofit = new Retrofit.Builder().baseUrl(url).client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson)).build();
        }
        return retrofit;

    }
}
