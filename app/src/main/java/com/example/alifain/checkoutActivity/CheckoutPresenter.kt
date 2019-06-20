package com.example.alifain.checkoutActivity

import android.util.Log
import com.example.alifain.model.kota.RajaOngkir
import com.example.alifain.model.kota.RajaOngkirResponse
import com.example.alifain.model.provinsi.RajaOngkirResponses
import com.example.alifain.model.provinsi.Rajaongkir
import com.example.alifain.model.provinsi.Result
import com.example.alifain.rest.ApiInterface
import com.example.alifain.rest.ApiRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckoutPresenter(private val view: CheckoutView , private val apiRepository: ApiRepository) {

    fun getListProvinsi(){
        view.showLoading()
        val connect : ApiInterface = apiRepository.getRajaOngkir().create(ApiInterface::class.java)
        connect.getProvinsi().enqueue(object : Callback<RajaOngkirResponses>{
            override fun onFailure(call: Call<RajaOngkirResponses>, t: Throwable) {

            }

            override fun onResponse(call: Call<RajaOngkirResponses>, response: Response<RajaOngkirResponses>) {
               val get : Rajaongkir = response.body()!!.rajaongkir
                view.showListProvinsi(get)
                Log.e("tag", "responsennya ${get.results}")

            }

        })
    }

    fun getListKota(province : String) {
        view.showLoading()
        val connect : ApiInterface = apiRepository.getRajaOngkir().create(ApiInterface::class.java)
        connect.getKota(province).enqueue(object : Callback<RajaOngkirResponse>{
            override fun onFailure(call: Call<RajaOngkirResponse>, t: Throwable) {
            }

            override fun onResponse(call: Call<RajaOngkirResponse>, response: Response<RajaOngkirResponse>) {
                val get : RajaOngkir? = response.body()?.rajaOngkir
                view.showListKota(get!!)
            }

        })
    }
}