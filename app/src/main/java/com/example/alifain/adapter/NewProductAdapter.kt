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
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_new_product.view.*

class NewProductAdapter(private val context: Context?, private val items: List<ProductModel>)
    : RecyclerView.Adapter<NewProductAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_new_product, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: NewProductAdapter.ViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name = view.findViewById<TextView>(R.id.tv_name_new_product)
        private val image = view.findViewById<ImageView>(R.id.image_new_product)
        private val price = view.findViewById<TextView>(R.id.tv_price_new_product)

        fun bindItem(items : ProductModel) {
            name.text = items.name
            items.image?.let { Picasso.get().load(it).into(image) }
            price.text = items.price.toString()
        }
    }
}