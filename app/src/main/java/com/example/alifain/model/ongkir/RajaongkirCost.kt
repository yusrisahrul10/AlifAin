package com.example.alifain.model.ongkir

data class RajaongkirCost(
    val destination_details: DestinationDetails,
    val origin_details: OriginDetails,
    val query: Query,
    val results: List<Result>,
    val status: Status
)