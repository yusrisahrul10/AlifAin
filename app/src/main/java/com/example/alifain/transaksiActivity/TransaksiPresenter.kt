package com.example.alifain.transaksiActivity

import android.content.Context
import android.widget.Toast
import com.example.alifain.model.konfirmasi.KonfirmasiResponses
import com.example.alifain.model.transaksi.DataTransaksi
import com.example.alifain.model.transaksi.TransaksiResponses
import com.example.alifain.rest.ApiInterface
import com.example.alifain.rest.ApiRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransaksiPresenter(private val view: TransaksiView,private val apiRepository: ApiRepository) {

    private var context : Context? = null

    fun TransaksiPresenter(context: Context){
        this.context = context
    }

    fun getTransaksi(id_transaksi : String){
        view.hideLoading()
        val connect : ApiInterface = apiRepository.getUrl().create(ApiInterface::class.java)
        connect.getKonfirmasiData(id_transaksi).enqueue(object : Callback<TransaksiResponses>{
            override fun onFailure(call: Call<TransaksiResponses>, t: Throwable) {

            }

            override fun onResponse(call: Call<TransaksiResponses>, response: Response<TransaksiResponses>) {
                val get : DataTransaksi = response.body()!!.data.get(0)
                view.showDataTransaksi(get)
            }

        })
    }

    fun postTransaksi(atas_nama : String,nama_bank : String,nominal : String,tgl_tf : String,id_transaksi : String){
        view.showLoading()
        val connect : ApiInterface = apiRepository.getUrl().create(ApiInterface::class.java)
        connect.konfirmasiAction(atas_nama,nama_bank,nominal,tgl_tf,id_transaksi).enqueue(object : Callback<KonfirmasiResponses>{
            override fun onFailure(call: Call<KonfirmasiResponses>, t: Throwable) {

            }

            override fun onResponse(call: Call<KonfirmasiResponses>, response: Response<KonfirmasiResponses>) {
               val get : String? = response.body()?.message
                if(get.equals("sukses")){
                    Toast.makeText(context, "Insert berhasil ", Toast.LENGTH_SHORT).show()
                    moveIntent()
                }else{
                    Toast.makeText(context, "Insert Gagal ", Toast.LENGTH_SHORT).show()

                }
            }

        })
    }

    fun moveIntent(){
        view.moveIntent()
    }

}