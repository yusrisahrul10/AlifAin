package com.example.alifain.model.kotahelsan

data class ResultKota(
    val city_id: String,
    val city_name: String,
    val postal_code: String,
    val province: String,
    var province_id: String,
    val type: String


){

    override fun toString(): String {
        return type.toString()+" "+city_name.toString()
    }
}