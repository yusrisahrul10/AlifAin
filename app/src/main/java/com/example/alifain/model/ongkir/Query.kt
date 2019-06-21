package com.example.alifain.model.ongkir

data class Query(
    val courier: String,
    val destination: String,
    val origin: String,
    val weight: Int
)