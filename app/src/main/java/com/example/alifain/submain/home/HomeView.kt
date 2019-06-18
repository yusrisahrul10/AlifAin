package com.example.alifain.submain.home

import com.example.alifain.model.barang.BarangResponse
import com.example.alifain.model.barang.Data

interface HomeView {
    fun showBarang(data : List<Data>)
    fun showLoading()
    fun viewLoading()
}