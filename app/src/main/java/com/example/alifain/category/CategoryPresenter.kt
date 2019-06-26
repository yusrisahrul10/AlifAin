package com.example.alifain.category

import com.example.alifain.model.barang.BarangResponse
import com.example.alifain.model.barang.Data
import com.example.alifain.rest.ApiInterface
import com.example.alifain.rest.ApiRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryPresenter(private val view: CategoryView , private val apiRepository: ApiRepository) {

    fun getListBarang(category : String){
        view.showLoading()
        val connect : ApiInterface = apiRepository.getUrl().create(ApiInterface::class.java)
        connect.getListFromCategory(category).enqueue(object : Callback<BarangResponse>{
            override fun onFailure(call: Call<BarangResponse>, t: Throwable) {
                view.showBarangFailed(t.localizedMessage)
            }

            override fun onResponse(call: Call<BarangResponse>, response: Response<BarangResponse>) {
               val get : List<Data>? = response.body()?.data
                view.getListBarang(get!!)
                view.hideLoading()
            }

        })
    }
}