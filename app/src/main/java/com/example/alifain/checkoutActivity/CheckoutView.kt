package com.example.alifain.checkoutActivity

import com.example.alifain.model.kota.RajaOngkir
import com.example.alifain.model.provinsi.Rajaongkir
import com.example.alifain.model.provinsi.Result

interface CheckoutView {
    fun showListProvinsi(data : Rajaongkir)
    fun showListKota(data: RajaOngkir)
    fun showLoading()
    fun hideLoading()
}