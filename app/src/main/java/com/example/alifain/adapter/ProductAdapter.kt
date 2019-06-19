package com.example.alifain.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.alifain.R
import com.example.alifain.model.ProductModel
import com.example.alifain.model.barang.Data
import com.squareup.picasso.Picasso

class ProductAdapter(private val context: Context?, private val items: List<ProductModel>,
                        private val listener: (ProductModel) -> Unit)
    : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_new_product, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name = view.findViewById<TextView>(R.id.tv_name_new_product)
        private val image = view.findViewById<ImageView>(R.id.image_new_product)
        private val price = view.findViewById<TextView>(R.id.tv_price_new_product)

        fun bindItem(items : ProductModel, listener: (ProductModel) -> Unit) {
            name.text = items.name
            items.image?.let { Picasso.get().load(it).into(image) }
            price.text = items.price.toString()

            itemView.setOnClickListener {
                listener(items)
            }
        }
    }
}