package com.example.alifain.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.alifain.R
import com.example.alifain.model.listtransaksi.Data

class ListTransaksiAdapter(private val context: Context?, private val items: List<Data>,
                           private val listener: (Data) -> Unit)
    : RecyclerView.Adapter<ListTransaksiAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ListTransaksiAdapter.ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_transaksi, parent, false))

    override fun getItemCount(): Int  = items.size

    override fun onBindViewHolder(holder: ListTransaksiAdapter.ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val status = view.findViewById<TextView>(R.id.tv_status_transaksi)
        private val pesanan = view.findViewById<TextView>(R.id.tvKodePesanan)
        private val tanggal = view.findViewById<TextView>(R.id.tvTglPesanan)
        private val alamat = view.findViewById<TextView>(R.id.tvAlamatPesanan)
        private val total = view.findViewById<TextView>(R.id.tvTotalPesanan)



        fun bindItem(items: Data, listener: (Data) -> Unit) {
            val ttl_pembayaran = items.total_harga + items.ongkir

            var stts = ""
            if (items.konfirmasi == "0") {
                stts = "Belum Dibayar"
            }
            if (items.konfirmasi == "1") {
               stts = "Sedang Dikonfirmasi"
            }
            if (items.konfirmasi == "2") {
                stts = "Sedang Dikirim"
            }
            if (items.konfirmasi == "3") {
                stts = "Pesanan Diterima"
            }

            status.text = stts
            pesanan.text = "Pesanan " + items.kode_transaksi + " >>"
            tanggal.text = items.tgl_transaksi
            alamat.text = items.alamat_penerima
            total.text = "Rp. " + ttl_pembayaran.toString()

            itemView.setOnClickListener {
                listener(items)
            }
        }
    }
}