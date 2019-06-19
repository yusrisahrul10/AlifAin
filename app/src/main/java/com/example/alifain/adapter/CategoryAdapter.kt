package com.example.alifain.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.alifain.R
import com.example.alifain.model.CategoryModel
import com.squareup.picasso.Picasso

class CategoryAdapter(private val context: Context?, private val items: List<CategoryModel>,
                      private val listener: (CategoryModel) -> Unit)
    : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_category, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    class ViewHolder(view: View) :RecyclerView.ViewHolder(view) {
        private val name = view.findViewById<TextView>(R.id.name)
        private val image = view.findViewById<ImageView>(R.id.image)

        fun bindItem(items: CategoryModel, listener: (CategoryModel) -> Unit) {
            name.text = items.name
            items.image?.let { Picasso.get().load(it).into(image) }

            itemView.setOnClickListener {
                listener(items)
            }
        }
    }
}