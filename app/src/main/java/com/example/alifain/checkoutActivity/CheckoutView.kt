package com.example.alifain.checkoutActivity

import com.example.alifain.model.provinsi.Result

interface CheckoutView {
    fun showListProvinsi(data : List<Result>)
    fun showLoading()
    fun hideLoading()
}