package com.example.alifain.transaksiActivity

import com.example.alifain.model.transaksi.DataTransaksi

interface TransaksiView {
    fun showDataTransaksi(data : DataTransaksi)
    fun showLoading()
    fun hideLoading()
    fun moveIntent()
    fun responseFailed(message : String)
}