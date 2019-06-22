package com.example.alifain.submain.cart

import android.content.Context
import android.util.Log
import com.example.alifain.model.deletekeranjang.DeleteKeranjangResponse
import com.example.alifain.rest.ApiInterface
import com.example.alifain.rest.ApiRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeleteCartPresenter(private val context: Context?, private val apiRepository: ApiRepository) {

    fun deletedCart(id_user: String, id_barang: String) {

        val connect : ApiInterface = apiRepository.getUrl().create(ApiInterface::class.java)
        connect.deleteKeranjang(id_user, id_barang).enqueue(object : Callback<DeleteKeranjangResponse> {
            override fun onFailure(call: Call<DeleteKeranjangResponse>, t: Throwable) {
                Log.e("gagal", t.message)
            }

            override fun onResponse(call: Call<DeleteKeranjangResponse>, response: Response<DeleteKeranjangResponse>) {
                val push : String? = response.body()?.messaage

                Log.e("tag", "RESPONE CART $push")
            }

        })
    }
}