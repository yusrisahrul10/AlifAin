package com.example.alifain.model.kota

data class ResultKota(
    val province: String,
    var province_id: String,
    val city_name: String,
    val city_id: String,
    val type: String,
    val postal_code: String
    ) {
    override fun toString(): String {
        return city_name
    }
}