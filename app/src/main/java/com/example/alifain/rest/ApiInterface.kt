package com.example.alifain.rest


import com.example.alifain.model.barang.BarangResponse
import com.example.alifain.model.login.LoginRespones
import retrofit2.Call
import retrofit2.http.*

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

    @GET("Barang")
    fun getBarang() : Call<BarangResponse>

    @GET("Barang")
    fun getDetailBarang(@Query("id_barang") id_barang : String) : Call<BarangResponse>

}