package com.example.alifain.detailBarang

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.alifain.model.barang.BarangResponse
import com.example.alifain.model.barang.Data
import com.example.alifain.model.keranjang.KeranjangResponses
import com.example.alifain.rest.ApiInterface
import com.example.alifain.rest.ApiRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPresenter(private val view: DetailView, private val apiRepository: ApiRepository) {

    private var context: Context? = null

    fun DetailPresenter(context: Context) {
        this.context = context

    }
    fun getDetailBarang(id_barang: String) {
        view.showLoading()
        val connect: ApiInterface = apiRepository.getUrl().create(ApiInterface::class.java)
        connect.getDetailBarang(id_barang).enqueue(object : Callback<BarangResponse> {
            override fun onFailure(call: Call<BarangResponse>, t: Throwable) {
                Log.e("tag", "RESPONE FAILED ${t.message}")
                view.showDetailBarangFailed(t.localizedMessage)
            }

            override fun onResponse(call: Call<BarangResponse>, response: Response<BarangResponse>) {

                val get : Data = response.body()!!.data.get(0)
                view.showDetailBarang(get)
                view.hideLoading()
            }

        })
    }



    fun pushCart(id_user : String,id_barang: String , qty : Int, deskripsi : String){
        val connect : ApiInterface = apiRepository.getUrl().create(ApiInterface::class.java)
        connect.keranjangAction(id_user,id_barang,qty, deskripsi).enqueue(object : Callback<KeranjangResponses>{
            override fun onFailure(call: Call<KeranjangResponses>, t: Throwable) {
                view.addCartFailed(t.localizedMessage)
            }

            override fun onResponse(call: Call<KeranjangResponses>, response: Response<KeranjangResponses>) {
                val push : String? = response.body()?.message
                if(push.equals("InsertBerhasil")){
                    moveFragment()
                }else{
                    Toast.makeText(context, "Update Berhasil", Toast.LENGTH_SHORT).show()
                    moveFragment()
                }
                Log.e("tag", "RESPONE CART ${push}")
            }

        })
    }



    fun moveFragment(){
        view.moveToHomeFragment()
    }
}