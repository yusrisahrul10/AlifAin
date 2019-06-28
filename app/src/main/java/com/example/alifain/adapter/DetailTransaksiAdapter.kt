package com.example.alifain.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.alifain.R
import com.example.alifain.model.detailtransaksi.Data
import com.squareup.picasso.Picasso

class DetailTransaksiAdapter(private val context: Context?, private val items: List<Data>)
    :RecyclerView.Adapter<DetailTransaksiAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): DetailTransaksiAdapter.ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_detail_transaksi, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: DetailTransaksiAdapter.ViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nama = view.findViewById<TextView>(R.id.tvNamaBarang)
        private val kategori = view.findViewById<TextView>(R.id.tvKategori)
        private val harga = view.findViewById<TextView>(R.id.tvHargaSatuan)
        private val qty = view.findViewById<TextView>(R.id.tvQty)
        private val gambar = view.findViewById<ImageView>(R.id.imgDetaiTransaksi)

        fun bindItem(items : Data) {
            nama.text = items.nama_barang
            kategori.text = items.category
            harga.text = "Rp. " + items.harga
            qty.text = "Jumlah : " +items.qty + " buah"

            items.nama_gambar.let { Picasso.get().load("http://alifain.dscunikom.com/uploads/barang/"+it).into(gambar) }
        }
    }

}