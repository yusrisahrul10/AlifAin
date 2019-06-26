package com.example.alifain.detailBarang

import com.example.alifain.model.barang.Data

interface DetailView {
    fun showDetailBarang(data : Data)
    fun showLoading()
    fun hideLoading()
    fun moveToHomeFragment()
    fun showDetailBarangFailed(message : String)
    fun addCartFailed(message: String)
}