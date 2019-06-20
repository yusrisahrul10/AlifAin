package com.example.alifain.checkoutActivity

import android.util.Log
import com.example.alifain.model.provinsi.RajaOngkirResponses
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
               val get : List<Result> = response.body()!!.rajaongkir.results
                view.showListProvinsi(get)
                Log.e("tag", "responsennya ${get.get(0).province}")

            }

        })
    }
}