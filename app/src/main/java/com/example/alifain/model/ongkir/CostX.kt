package com.example.alifain.model.ongkir

data class CostX(
    val etd: String,
    val note: String,
    val value: Int
)
{
    private var Cost : Cost? = null

    override fun toString(): String {
        return value.toString()
    }
}