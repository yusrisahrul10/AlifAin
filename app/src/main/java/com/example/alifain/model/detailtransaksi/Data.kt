package com.example.alifain.model.detailtransaksi

data class Data(
    val alamat_penerima: String,
    val category: String,
    val deskripsi: String,
    val harga: String,
    val id_transaksi: String,
    val konfirmasi: String,
    val nama_barang: String,
    val no_resi: Any,
    val tgl_transaksi: String,
    val total_harga: String,
    val ukuran: String,
    val nama_gambar: String
)