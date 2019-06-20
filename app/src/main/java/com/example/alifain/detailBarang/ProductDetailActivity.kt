package com.example.alifain.detailBarang

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.alifain.MainActivity
import com.example.alifain.R
import com.example.alifain.model.barang.Data
import com.example.alifain.preference.MyPreference
import com.example.alifain.rest.ApiRepository
import com.squareup.picasso.Picasso

class ProductDetailActivity : AppCompatActivity() , DetailView {


    private lateinit var presenter: DetailPresenter
    private lateinit var imgDetail : ImageView
    private lateinit var txtJudul : TextView
    private lateinit var txthargaBarang : TextView
    private lateinit var btnCart : Button
    private lateinit var preference: MyPreference
    private lateinit var txtDesc : TextView

    private lateinit var id_barang : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        imgDetail = findViewById(R.id.imgDetail)
        txtJudul = findViewById(R.id.judulProduk)
        txthargaBarang = findViewById(R.id.hargaBarang)
        txtDesc = findViewById(R.id.descProduk)
        btnCart = findViewById(R.id.btnSaveCart)
        preference = MyPreference(this)

        val apiRepository = ApiRepository()
        presenter = DetailPresenter(this,apiRepository)
        presenter.DetailPresenter(this)
        id_barang = intent.getStringExtra("id_barang")
        presenter.getDetailBarang(id_barang)
        btnCart.setOnClickListener {
            presenter.pushCart(preference.getIdUser(),id_barang,"3")
        }
    }

    override fun moveToHomeFragment() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun showDetailBarang(data: Data) {
        txthargaBarang.text = "Rp. "+data.harga
        txtDesc.text = data.deskripsi
        txtJudul.text = data.nama_barang
        data.nama_gambar.let { Picasso.get().load("http://alifain.dscunikom.com/uploads/barang/"+it).into(imgDetail) }
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

}
