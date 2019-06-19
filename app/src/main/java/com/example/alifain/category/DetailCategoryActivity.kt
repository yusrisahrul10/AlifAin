package com.example.alifain.category

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.alifain.detailBarang.ProductDetailActivity
import com.example.alifain.R
import com.example.alifain.adapter.NewProductAdapter
import com.example.alifain.adapter.ProductAdapter
import com.example.alifain.model.CategoryModel
import com.example.alifain.model.ProductModel
import com.example.alifain.model.barang.Data
import com.example.alifain.rest.ApiRepository
import com.example.alifain.submain.CategoryFragment






class DetailCategoryActivity : AppCompatActivity() , CategoryView {


    private var items: MutableList<Data> = mutableListOf()
    private lateinit var presenter: CategoryPresenter
    private lateinit var list : RecyclerView
    private val ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_category)

       list = findViewById<RecyclerView>(R.id.rv_category_product_list)
        val getItem = intent.getParcelableExtra<CategoryModel>("id")
        val apiRepository = ApiRepository()
        presenter = CategoryPresenter(this,apiRepository)
        presenter.getListBarang(getItem.name.toString())
        Log.e("tag", "RANDOM STRING  ${getRandomString(30)}")


    }
    fun getRandomString(length: Int) : String {
        val allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }



    private fun itemClick(item : Data){
        val intent = Intent(this, ProductDetailActivity::class.java)
        intent.putExtra("id_barang",item.id_barang)
        startActivity(intent)
    }

    override fun getListBarang(data: List<Data>) {
        items.addAll(data)
        list.adapter = NewProductAdapter(this, items , { itemMatch: Data -> itemClick(itemMatch) })
        list.layoutManager = GridLayoutManager(this, 2)
        (list.adapter as NewProductAdapter).notifyDataSetChanged()

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

}
