package com.example.alifain.detailBarang

import com.example.alifain.model.barang.BarangResponse
import com.example.alifain.model.barang.Data
import com.example.alifain.rest.ApiInterface
import com.example.alifain.rest.ApiRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPresenter(private val view: DetailView, private val apiRepository: ApiRepository) {

    fun getDetailBarang(id_barang: String) {
        view.showLoading()
        val connect: ApiInterface = apiRepository.getUrl().create(ApiInterface::class.java)
        connect.getDetailBarang(id_barang).enqueue(object : Callback<BarangResponse> {
            override fun onFailure(call: Call<BarangResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<BarangResponse>, response: Response<BarangResponse>) {
               view.hideLoading()
                val get : Data? = response.body()?.data?.get(0)
                view.showDetailBarang(get!!)
            }

        })
    }
}