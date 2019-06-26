package com.example.alifain.category

import com.example.alifain.model.barang.Data

interface CategoryView {
    fun getListBarang(data : List<Data>)
    fun showLoading()
    fun hideLoading()
    fun showBarangFailed(message : String)
}