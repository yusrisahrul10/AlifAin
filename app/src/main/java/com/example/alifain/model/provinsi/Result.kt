package com.example.alifain.model.provinsi

data class Result(
    val province: String,
    val province_id: String
) {

    override fun toString(): String {
        return province.toString()
    }
}