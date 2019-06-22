package com.example.alifain.rest


import com.example.alifain.model.barang.BarangResponse
import com.example.alifain.model.cart.CartResponses
import com.example.alifain.model.cekout.CekoutResponses
import com.example.alifain.model.deletekeranjang.DeleteKeranjangResponse
import com.example.alifain.model.keranjang.KeranjangResponses
import com.example.alifain.model.konfirmasi.KonfirmasiResponses
import com.example.alifain.model.kotahelsan.KotaResponses
import com.example.alifain.model.login.LoginRespones
import com.example.alifain.model.ongkir.Cost
import com.example.alifain.model.ongkir.OngkirResponses
import com.example.alifain.model.ongkir.RajaongkirCost
import com.example.alifain.model.provinsi.RajaOngkirResponses
import com.example.alifain.model.transaksi.TransaksiResponses
import retrofit2.Call
import retrofit2.http.*
import javax.xml.transform.OutputKeys.METHOD

interface ApiInterface {

    @FormUrlEncoded
    @POST("User/login")
    fun loginAction(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginRespones>

    @FormUrlEncoded
    @POST("User/register")
    fun registerAction(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("alamat") alamat: String,
        @Field("nohp") nohp: String,
        @Field("password") password: String
    ): Call<LoginRespones>

    @GET("Barang")
    fun getBarang(): Call<BarangResponse>

    @GET("Barang")
    fun getDetailBarang(@Query("id_barang") id_barang: String): Call<BarangResponse>

    @GET("Barang/categorybarang")
    fun getListFromCategory(@Query("category") category: String): Call<BarangResponse>

    @GET("Barang/KeranjangList")
    fun getListCart(@Query("id_user") id_user: String): Call<CartResponses>

    @GET("Transaksi/showkonfirmasi")
    fun getKonfirmasiData(@Query("id_transaksi") id_transaksi : String) : Call<TransaksiResponses>


    @FormUrlEncoded
    @POST("Barang/keranjang")
    fun keranjangAction(
        @Field("id_user") id_user: String,
        @Field("id_barang") id_barang: String,
        @Field("qty") qty: Int
    ): Call<KeranjangResponses>

    @Headers("key:ee7be574f8c38cfef3420c8634acea41")
    @GET("province")
    fun getProvinsi(): Call<RajaOngkirResponses>

    @Headers("key:ee7be574f8c38cfef3420c8634acea41")
    @GET("city")
    fun getKota(@Query("province") province : String) : Call<RajaOngkirResponse>

}