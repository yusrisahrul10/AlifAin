package com.example.alifain.rest


import com.example.alifain.model.login.LoginRespones
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {

    @FormUrlEncoded
    @POST("User/login")
    fun loginAction(
        @Field("email") email : String,
        @Field("password") password : String) : Call<LoginRespones>

    @FormUrlEncoded
    @POST("User/register")
    fun registerAction(
        @Field("username") username : String,
        @Field("email") email : String,
        @Field("alamat") alamat : String,
        @Field("nohp") nohp : String,
        @Field("password") password : String
    ) : Call<LoginRespones>

}