package com.example.alifain.checkoutActivity

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.alifain.model.cekout.CekoutResponses
import com.example.alifain.model.kotahelsan.KotaResponses
import com.example.alifain.model.kotahelsan.Rajaongkir
import com.example.alifain.model.kotahelsan.ResultKota
import com.example.alifain.model.ongkir.Cost
import com.example.alifain.model.ongkir.OngkirResponses
import com.example.alifain.model.ongkir.RajaongkirCost
import com.example.alifain.model.provinsi.RajaOngkirResponses
import com.example.alifain.model.provinsi.RajaongkirProvinsi
import com.example.alifain.rest.ApiInterface
import com.example.alifain.rest.ApiRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckoutPresenter(private val view: CheckoutView, private val apiRepository: ApiRepository) {


    private var context: Context? = null

    fun CheckoutPresenter(context: Context) {
        this.context = context
    }

    fun getListProvinsi() {
        view.showLoading()
        val connect: ApiInterface = apiRepository.getRajaOngkir().create(ApiInterface::class.java)
        connect.getProvinsi().enqueue(object : Callback<RajaOngkirResponses> {
            override fun onFailure(call: Call<RajaOngkirResponses>, t: Throwable) {
                view.showListFailed(t.localizedMessage)
            }

            override fun onResponse(call: Call<RajaOngkirResponses>, response: Response<RajaOngkirResponses>) {
                val get: RajaongkirProvinsi = response.body()!!.rajaongkir
                view.showListProvinsi(get)

            }

        })
    }

    fun getListKota(province: String) {
        view.showLoading()
        val connect: ApiInterface = apiRepository.getRajaOngkir().create(ApiInterface::class.java)
        connect.getKota(province).enqueue(object : Callback<KotaResponses> {
            override fun onFailure(call: Call<KotaResponses>, t: Throwable) {
                view.showListFailed(t.localizedMessage)
            }

            override fun onResponse(call: Call<KotaResponses>, response: Response<KotaResponses>) {
                val get: Rajaongkir = response.body()!!.rajaongkir
//                Log.e("tag", "RESPONE KOTA ${get!!.results}")
                view.showListKota(get)
            }

        })
    }

    fun cekoutPost(id_user: Int, ongkir: Int, totalharga: Int, alamat: String) {
        view.showLoading()
        val connect: ApiInterface = apiRepository.getUrl().create(ApiInterface::class.java)
        connect.cekoutAction(id_user, ongkir, totalharga, alamat).enqueue(object : Callback<CekoutResponses> {
            override fun onFailure(call: Call<CekoutResponses>, t: Throwable) {
                view.showListFailed(t.localizedMessage)
            }

            override fun onResponse(call: Call<CekoutResponses>, response: Response<CekoutResponses>) {
                val get: String? = response.body()?.message
//                Log.e("tag", "RESOPONE CEKOUT ${response.body()?.toString()}")
                val id_transaksi : Int? = response.body()?.id_transaksi

                if (get.equals("BERHASIL")) {
                    moveIntent(id_transaksi!!)
                } else {
                    Toast.makeText(context, "Tidak Berhasil Membuat Pesanan", Toast.LENGTH_SHORT).show()
//                    Log.e("tag", "RESOPONE CEKOUT ${get}")

                }
            }

        })
    }

    fun getCost(kota_asal: String, kota_tujuan: String, berat: String, kurir: String) {
        view.showLoading()
        val connect: ApiInterface = apiRepository.getRajaOngkir().create(ApiInterface::class.java)
        connect.getCost(kota_asal, kota_tujuan, berat, kurir).enqueue(object : Callback<OngkirResponses> {
            override fun onFailure(call: Call<OngkirResponses>, t: Throwable) {
                view.showListFailed(t.localizedMessage)
            }

            override fun onResponse(call: Call<OngkirResponses>, response: Response<OngkirResponses>) {


                val get: Int = response.body()!!.rajaongkir.results.get(0).costs.get(0).cost.get(0).value
                view.showCost(get)
                Log.e("tag", "RESPONE BAYAR ${get}")


            }

        })
    }

    fun moveIntent(id_transaksi : Int) {
        view.moveIntent(id_transaksi)
    }
}