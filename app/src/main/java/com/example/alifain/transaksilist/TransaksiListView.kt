package com.example.alifain.transaksilist

import com.example.alifain.model.listtransaksi.Data

interface TransaksiListView {
    fun showListTransaksi(data: List<Data>)
    fun showLoading()
    fun hideLoading()
    fun showListTransaksiFailed(message : String)
    //fun moveIntent()
}