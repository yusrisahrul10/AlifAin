package com.example.alifain.submain.cart

import com.example.alifain.model.cart.Data

interface CartView {
    fun showListCart(data : List<Data>)
    fun showTotalHarga(harga: Int)
    fun showTotalGram(gram : Int)
    fun showLoading()
    fun hideLoading()
}