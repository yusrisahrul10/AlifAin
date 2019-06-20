package com.example.alifain.submain.cart

import com.example.alifain.model.cart.Data

interface CartView {
    fun showListCart(data : List<Data>)
    fun showLoading()
    fun hideLoading()
}