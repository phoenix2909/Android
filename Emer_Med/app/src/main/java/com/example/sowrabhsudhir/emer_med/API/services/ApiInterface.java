package com.example.sowrabhsudhir.emer_med.API.services;

import com.example.sowrabhsudhir.emer_med.API.models.responses.LoginRes;
import com.example.sowrabhsudhir.emer_med.API.models.responses.RegisterRes;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login")
    Call<LoginRes> attemptLogin(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("register")
    Call<RegisterRes> registerUser(@Field("name") String name, @Field("email") String email, @Field("emergency") String emergency, @Field("age") String age, @Field("bloodgroup") String bloodgroup, @Field("bloodpressure") String bloodpressure, @Field("diseases") String diseases, @Field("password") String password);

}