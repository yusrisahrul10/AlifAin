package com.example.alifain.detailtransaksi

import com.example.alifain.model.detailtransaksi.Data
import com.example.alifain.model.detailtransaksi.Total

interface DetailTransaksiView {
    fun showLoading()
    fun hideLoading()
    fun getDetailBarang(data: List<Data>)
    fun getTotalPembayaran(total : Total)
    fun moveIntent()
    fun showDetailTransaksiFailed(message : String)
    fun responseFailed(message : String)
}