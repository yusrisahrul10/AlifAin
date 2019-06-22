package com.example.alifain.submain.cart

import android.util.Log
import com.example.alifain.model.cart.CartResponses
import com.example.alifain.model.cart.Data
import com.example.alifain.model.deletekeranjang.DeleteKeranjangResponse
import com.example.alifain.rest.ApiInterface
import com.example.alifain.rest.ApiRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartPresenter(private val view: CartView, private val apiRepository: ApiRepository) {

    fun getListCart(id_user: String) {
        view.showLoading()
        val connect: ApiInterface = apiRepository.getUrl().create(ApiInterface::class.java)
        connect.getListCart(id_user).enqueue(object : Callback<CartResponses> {
            override fun onFailure(call: Call<CartResponses>, t: Throwable) {

            }

            override fun onResponse(call: Call<CartResponses>, response: Response<CartResponses>) {
                val get : List<Data>? = response.body()?.data
                val harga: Int? = response.body()?.total_harga
                val gram : Int? = response.body()?.total_gram
                view.showListCart(get!!)
                view.showTotalHarga(harga!!)
                view.showTotalGram(gram!!)
            }

        })
    }

    fun deleteCart(id_user: String, id_barang: String) {
        val connect : ApiInterface = apiRepository.getUrl().create(ApiInterface::class.java)
        connect.deleteKeranjang(id_user, id_barang).enqueue(object : Callback<DeleteKeranjangResponse>{
            override fun onFailure(call: Call<DeleteKeranjangResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<DeleteKeranjangResponse>, response: Response<DeleteKeranjangResponse>) {
                val push : String? = response.body()?.messaage

                Log.e("tag", "RESPONE CART ${push}")
            }

        })
    }

    fun deleteCart(id_user: String, id_barang: String) {
        val connect : ApiInterface = apiRepository.getUrl().create(ApiInterface::class.java)
        connect.deleteKeranjang(id_user, id_barang).enqueue(object : Callback<DeleteKeranjangResponse>{
            override fun onFailure(call: Call<DeleteKeranjangResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<DeleteKeranjangResponse>, response: Response<DeleteKeranjangResponse>) {
                val push : String? = response.body()?.messaage

                Log.e("tag", "RESPONE CART ${push}")
            }

        })
    }
}