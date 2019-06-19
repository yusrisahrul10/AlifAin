package com.example.alifain.category

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.alifain.ProductDetailActivity
import com.example.alifain.R
import com.example.alifain.adapter.NewProductAdapter
import com.example.alifain.model.ProductModel

class DetailCategoryActivity : AppCompatActivity() {

    private var items: MutableList<ProductModel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_category)

        val list = findViewById<RecyclerView>(R.id.new_product_list)
        initData()

        list.layoutManager = GridLayoutManager(this, 2)
        list.adapter = NewProductAdapter(this, items) {
            val intent = Intent(this, ProductDetailActivity::class.java)
            startActivity(intent)
        }

    }

    private fun initData() {
        val name = resources.getStringArray(R.array.name_new_product)
        val image =  resources.obtainTypedArray(R.array.image_new_product)
        val price = resources.getIntArray(R.array.price_new_product)

        items.clear()
        for (i in name.indices) {
            items.add(
                ProductModel(name[i],
                    image.getResourceId(i, 0),
                    price[i])
            )
        }

        //Recycle the typed array
        image.recycle()
    }
}
