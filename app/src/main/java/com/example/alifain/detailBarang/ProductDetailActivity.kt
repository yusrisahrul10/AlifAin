package com.example.alifain.detailBarang

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.alifain.R
import com.example.alifain.model.barang.Data
import com.example.alifain.rest.ApiRepository
import com.squareup.picasso.Picasso

class ProductDetailActivity : AppCompatActivity() , DetailView {

    private lateinit var presenter: DetailPresenter
    private lateinit var imgDetail : ImageView
    private lateinit var txtTotal : TextView
    private lateinit var id_barang : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        imgDetail = findViewById(R.id.imgDetail)
        txtTotal = findViewById(R.id.hargaBarang)
        val apiRepository = ApiRepository()
        presenter = DetailPresenter(this,apiRepository)
        id_barang = intent.getStringExtra("id_barang")
        presenter.getDetailBarang(id_barang)
    }

    override fun showDetailBarang(data: Data) {
        txtTotal.text = data.harga

        data.nama_gambar?.let { Picasso.get().load("http://alifain.dscunikom.com/uploads/barang/"+it).into(imgDetail) }
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

}
