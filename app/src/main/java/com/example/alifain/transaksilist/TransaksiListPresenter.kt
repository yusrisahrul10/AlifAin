package com.example.alifain.transaksilist

import com.example.alifain.model.listtransaksi.Data
import com.example.alifain.model.listtransaksi.ListTransaksiResponse
import com.example.alifain.rest.ApiInterface
import com.example.alifain.rest.ApiRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransaksiListPresenter(private val view: TransaksiListView, private val apiRepository: ApiRepository) {

    fun getListTransaksi(id_user : String) {
        view.showLoading()
        val connect: ApiInterface = apiRepository.getUrl().create(ApiInterface::class.java)
        connect.getListTransaksi(id_user).enqueue(object : Callback<ListTransaksiResponse>{
            override fun onFailure(call: Call<ListTransaksiResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<ListTransaksiResponse>, response: Response<ListTransaksiResponse>) {
                val get : List<Data>? = response.body()?.data
                view.showListTransaksi(get!!)

                view.hideLoading()
            }

        })
    }
}