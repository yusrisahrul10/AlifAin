package com.example.alifain.model.cart

data class CartResponses(
    val `data`: List<Data>,
    val total_harga: Int,
    val total_gram : Int
)