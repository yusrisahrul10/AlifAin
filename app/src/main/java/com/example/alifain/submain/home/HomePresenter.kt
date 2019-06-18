package com.example.alifain.submain.home

import android.util.Log
import com.example.alifain.model.barang.BarangResponse
import com.example.alifain.model.barang.Data
import com.example.alifain.rest.ApiInterface
import com.example.alifain.rest.ApiRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePresenter(private val view: HomeView, private val apiRepository: ApiRepository) {

    fun getBarangList() {
        view.showLoading()
        val connect: ApiInterface = apiRepository.getUrl().create(ApiInterface::class.java)
        connect.getBarang().enqueue(object : Callback<BarangResponse> {
            override fun onFailure(call: Call<BarangResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<BarangResponse>, response: Response<BarangResponse>) {
                val list : List<Data>? = response.body()?.data
                view.showBarang(list!!)
                Log.e("tag", "responsennya ${list.get(0).nama_barang}")

            }

        })
    }
}