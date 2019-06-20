package com.example.alifain.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.alifain.R
import com.example.alifain.model.cart.Data
import com.squareup.picasso.Picasso

class CartAdapter(private val context: Context?, private var items: MutableList<Data> = mutableListOf(),
                  private val listener: (Data) -> Unit)
    : RecyclerView.Adapter<CartAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CartAdapter.ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
        holder.remove.setOnClickListener {
            items.removeAt(position)
            notifyDataSetChanged()
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name = view.findViewById<TextView>(R.id.judulCart)
        private val image = view.findViewById<ImageView>(R.id.imgCart)
        private val price = view.findViewById<TextView>(R.id.hargaCart)
        private val desc = view.findViewById<TextView>(R.id.descCart)
        private val qty = view.findViewById<TextView>(R.id.qtyCart)
        val remove = view.findViewById<ImageButton>(R.id.btnRemove)

        fun bindItem(items : Data, listener: (Data) -> Unit) {
            name.text = items.nama_barang
            desc.text = items.deskripsi
            qty.text = "Jumlah " + items.qty.toString()
            items.nama_gambar?.let { Picasso.get().load("http://alifain.dscunikom.com/uploads/barang/"+it).into(image) }
            price.text = "Subtotal: Rp. "+ items.subtotal



            itemView.setOnClickListener {
                listener(items)
            }
        }
    }

    }