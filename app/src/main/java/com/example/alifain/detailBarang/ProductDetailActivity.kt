package com.example.alifain.detailBarang

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.*
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

    private lateinit var btnMinus : Button
    private lateinit var btnPlus : Button
    private lateinit var tvQuantity : TextView

    private lateinit var edtDeskripsi : EditText

    var qty = 1
    var ttlPrice: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        imgDetail = findViewById(R.id.imgDetail)
        txtJudul = findViewById(R.id.judulProduk)
        txthargaBarang = findViewById(R.id.hargaBarang)
        txtDesc = findViewById(R.id.descProduk)
        btnCart = findViewById(R.id.btnSaveCart)

        btnMinus = findViewById(R.id.btnMinus)
        btnPlus = findViewById(R.id.btnPlus)
        tvQuantity = findViewById(R.id.tvQuantity)

        edtDeskripsi = findViewById(R.id.edtDeskripsiPesan)

        preference = MyPreference(this)

        val apiRepository = ApiRepository()
        presenter = DetailPresenter(this,apiRepository)
        presenter.DetailPresenter(this)
        id_barang = intent.getStringExtra("id_barang")
        presenter.getDetailBarang(id_barang)

        btnAddCart()

        tvQuantity.text = qty.toString()
    }

    fun btnAddCart() {
        btnCart.setOnClickListener {
            if (edtDeskripsi.text.isEmpty()) {
                Toast.makeText(this, "Masukkan deskripsi barang yang ingin dipesan", Toast.LENGTH_SHORT).show()
            } else {
                presenter.pushCart(preference.getIdUser(),id_barang,qty, edtDeskripsi.text.toString())
            }

        }
    }

    override fun moveToHomeFragment() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun showDetailBarang(data: Data) {

        if (qty == 1) {
            txthargaBarang.text = "Rp. "+ data.harga
        }

            btnPlus.setOnClickListener {
                qty++
                tvQuantity.text = qty.toString()
                ttlPrice = qty * data.harga
                txthargaBarang.text = "Rp. "+ ttlPrice
                Log.e("plus", "plus diklik")
                Log.e("quantity", "qty = " + qty.toString())
            }

            btnMinus.setOnClickListener {
                if (qty > 1) {
                    qty--
                    tvQuantity.text = qty.toString()
                    ttlPrice = qty * data.harga
                    txthargaBarang.text = "Rp. "+ ttlPrice
                    Log.e("minus", "minnus diklik ")
                    Log.e("quantity", "qty = " + qty.toString())
                }

            }


        txtDesc.text = data.deskripsi
        txtJudul.text = data.nama_barang
        data.nama_gambar.let { Picasso.get().load("http://alifain.dscunikom.com/uploads/barang/"+it).into(imgDetail) }
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

//    private fun getQuantity(quantity: Int) : Int {
//        var qty = 0;
//        btnPlus.setOnClickListener {
//            qty++
//        }
//
//        btnMinus.setOnClickListener {
//            qty--
//        }
//        quantity = qty
//        return quantity
//    }

//    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
//        super.onSaveInstanceState(outState, outPersistentState)
//
//        outState?.putInt("savedQty", qty)
//    }
//
//    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
//        super.onRestoreInstanceState(savedInstanceState)
//        val quantity = savedInstanceState?.getInt("savedQty")
//    }
}
