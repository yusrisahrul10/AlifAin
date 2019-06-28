package com.example.alifain.category

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.alifain.detailBarang.ProductDetailActivity
import com.example.alifain.R
import com.example.alifain.adapter.AllProductAdapter
import com.example.alifain.model.CategoryModel
import com.example.alifain.model.barang.Data
import com.example.alifain.rest.ApiRepository


class DetailCategoryActivity : AppCompatActivity() , CategoryView {


    private var items: MutableList<Data> = mutableListOf()
    private lateinit var presenter: CategoryPresenter
    private lateinit var list : RecyclerView
    private val ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"

    private lateinit var progressBar : ProgressBar
    private lateinit var tvKosong : TextView
    private lateinit var category : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_category)

       list = findViewById<RecyclerView>(R.id.rv_category_product_list)

        progressBar = findViewById(R.id.progress_bar_detail_category)
        tvKosong = findViewById(R.id.tv_kosong_detail_category)
        category = findViewById(R.id.nama_category)

        val getItem = intent.getParcelableExtra<CategoryModel>("id")
        val apiRepository = ApiRepository()
        presenter = CategoryPresenter(this,apiRepository)
        presenter.getListBarang(getItem.name.toString())

        category.text = getItem.name

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
        startActivityForResult(intent, 102)
    }

    override fun getListBarang(data: List<Data>) {
        items.addAll(data)
        list.adapter = AllProductAdapter(this, items , { itemMatch: Data -> itemClick(itemMatch) })
        list.layoutManager = GridLayoutManager(this, 2)
        (list.adapter as AllProductAdapter).notifyDataSetChanged()

    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
        tvKosong.visibility = View.GONE
        list.visibility = View.GONE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
        tvKosong.visibility = View.GONE
        list.visibility = View.VISIBLE
    }

    override fun showBarangFailed(message: String) {
        val message = "Tidak dapat memproses permintaan Anda karena kesalahan koneksi atau data kosong. Silakan coba lagi"
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        progressBar.visibility = View.GONE
        tvKosong.visibility = View.VISIBLE
        list.visibility = View.GONE

    }

}
