package com.example.alifain.checkoutActivity


import com.example.alifain.model.kotahelsan.Rajaongkir
import com.example.alifain.model.kotahelsan.ResultKota
import com.example.alifain.model.ongkir.Cost
import com.example.alifain.model.ongkir.OngkirResponses
import com.example.alifain.model.ongkir.RajaongkirCost
import com.example.alifain.model.provinsi.RajaongkirProvinsi

interface CheckoutView {
    fun showListProvinsi(data : RajaongkirProvinsi)
    fun showListKota(data: Rajaongkir)
    fun showCost(data : Int)
    fun moveIntent(id_transaksi : Int)
    fun showLoading()
    fun hideLoading()
}