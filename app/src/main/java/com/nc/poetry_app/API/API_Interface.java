package com.nc.poetry_app.API;

import com.nc.poetry_app.model.Poetry_Model;
import com.nc.poetry_app.model.Response.DeletePoetry;
import com.nc.poetry_app.model.Response.GetPoetryResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface API_Interface {


    @GET("getpoetry.php/")
    Call<GetPoetryResponse> getpoetry();

    @FormUrlEncoded
    @POST("deletepoetry.php/")
    Call<DeletePoetry> deletePoetry(@Field("poetry_id") String poetry_id);


    @FormUrlEncoded
    @POST("addPoety.php/")
    Call<DeletePoetry> addpoetry(@Field("poetry_name") String poet_name, @Field("poetry_data") String poetry_data);

    @FormUrlEncoded
    @POST("update_poetry.php/")
    Call<DeletePoetry> updatePoetry(@Field("id") String id, @Field("poetry_data") String poetry_data);

}
