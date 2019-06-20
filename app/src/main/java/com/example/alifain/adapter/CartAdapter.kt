package com.example.alifain.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.alifain.R
import com.example.alifain.model.cart.Data
import com.squareup.picasso.Picasso

class CartAdapter(private val context: Context?, private val items: List<Data>,
                  private val listener: (Data) -> Unit)
    : RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CartAdapter.ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name = view.findViewById<TextView>(R.id.judulCart)
        private val image = view.findViewById<ImageView>(R.id.imgCart)
        private val price = view.findViewById<TextView>(R.id.hargaCart)
        private val desc = view.findViewById<TextView>(R.id.descCart)

        fun bindItem(items : Data, listener: (Data) -> Unit) {
            name.text = items.nama_barang
            desc.text = items.deskripsi
            items.nama_gambar?.let { Picasso.get().load("http://alifain.dscunikom.com/uploads/barang/"+it).into(image) }
            price.text = "Rp. "+items.harga

            itemView.setOnClickListener {
                listener(items)
            }
        }
    }
    }