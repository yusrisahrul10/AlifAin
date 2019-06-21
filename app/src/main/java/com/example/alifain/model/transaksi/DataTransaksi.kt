package com.example.alifain.model.transaksi

data class DataTransaksi(
    val alamat_penerima: String,
    val id_transaksi: String,
    val id_user: String,
    val kode_transaksi: String,
    val konfirmasi: String,
    val no_resi: Any,
    val ongkir: Int,
    val tgl_transaksi: String,
    val total_harga: Int
)