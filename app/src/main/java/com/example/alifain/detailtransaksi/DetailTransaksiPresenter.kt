package com.example.alifain.detailtransaksi

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.alifain.model.detailtransaksi.Data
import com.example.alifain.model.detailtransaksi.DetailTransaksiResponse
import com.example.alifain.model.detailtransaksi.Total
import com.example.alifain.model.konfirmasi.KonfirmasiResponses
import com.example.alifain.model.konfirmasipesanan.KonfirmasiResponse
import com.example.alifain.rest.ApiInterface
import com.example.alifain.rest.ApiRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailTransaksiPresenter(private val view: DetailTransaksiView, private val apiRepository: ApiRepository) {

    private var context : Context? = null

    fun DetailTransaksiPresenter(context: Context){
        this.context = context
    }

    fun getDetailTransaksi(id_user : String, id_transaksi: String) {
        view.showLoading()
        val connect: ApiInterface = apiRepository.getUrl().create(ApiInterface::class.java)
        connect.getDetailTransaksi(id_user, id_transaksi).enqueue(object : Callback<DetailTransaksiResponse>{
            override fun onFailure(call: Call<DetailTransaksiResponse>, t: Throwable) {
                view.showDetailTransaksiFailed(t.localizedMessage)
            }

            override fun onResponse(call: Call<DetailTransaksiResponse>, response: Response<DetailTransaksiResponse>) {
                val get : List<Data>? = response.body()?.data
                val total: Total? = response.body()?.total

                view.getDetailBarang(get!!)
                view.getTotalPembayaran(total!!)

                view.hideLoading()
            }
        })
    }

    fun postTransaksi(atas_nama : String,nama_bank : String,nominal : String,tgl_tf : String,id_transaksi : String,
                      konfirmasi : String){
        val connect : ApiInterface = apiRepository.getUrl().create(ApiInterface::class.java)
        connect.konfirmasiAction(atas_nama,nama_bank,nominal,tgl_tf,id_transaksi, konfirmasi).enqueue(object : Callback<KonfirmasiResponses>{
            override fun onFailure(call: Call<KonfirmasiResponses>, t: Throwable) {
                view.responseFailed(t.localizedMessage)
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

    fun updateTransaksi(id_transaksi: String, konfirmasi: String) {
        val connect : ApiInterface = apiRepository.getUrl().create(ApiInterface::class.java)
        connect.updateKonfirmasi(id_transaksi, konfirmasi).enqueue(object : Callback<KonfirmasiResponse>{
            override fun onFailure(call: Call<KonfirmasiResponse>, t: Throwable) {
                view.responseFailed(t.localizedMessage)
            }

            override fun onResponse(call: Call<KonfirmasiResponse>, response: Response<KonfirmasiResponse>) {
                val message : String? = response.body()?.message
                Toast.makeText(context, "Pesanan diterima. Terima kasih telah berbelanja!", Toast.LENGTH_SHORT).show()
                Log.e("PESANAN DITERIMA", message)
            }

        })
    }

    fun moveIntent(){
        view.moveIntent()
    }
}