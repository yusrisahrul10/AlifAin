package com.example.alifain.detailBarang

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
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
    private lateinit var quantity : TextView
    private lateinit var tvDeskripsi : TextView
    private lateinit var tvTotal : TextView

    private lateinit var back : ImageView

    private lateinit var edtDeskripsi : EditText

    private lateinit var progressBar: ProgressBar

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
        back = findViewById(R.id.iv_back_product)

        quantity = findViewById(R.id.quantitiy)
        tvDeskripsi = findViewById(R.id.tvDeskripsi)
        tvTotal = findViewById(R.id.total1)

        edtDeskripsi = findViewById(R.id.edtDeskripsiPesan)

        progressBar = findViewById(R.id.progress_bar_detail_barang)

        preference = MyPreference(this)

        val apiRepository = ApiRepository()
        presenter = DetailPresenter(this,apiRepository)
        presenter.DetailPresenter(this)
        id_barang = intent.getStringExtra("id_barang")
        presenter.getDetailBarang(id_barang)

        btnAddCart()

        tvQuantity.text = qty.toString()

        back.setOnClickListener {
            finishAffinity()
        }
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
        finishAffinity()
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
        progressBar.visibility = View.VISIBLE
        txtJudul.visibility = View.GONE
        txtDesc.visibility = View.GONE
        txthargaBarang.visibility = View.GONE
        quantity.visibility = View.GONE
        btnMinus.visibility = View.GONE
        btnPlus.visibility = View.GONE
        tvQuantity.visibility = View.GONE
        tvDeskripsi.visibility = View.GONE
        imgDetail.visibility = View.GONE
        btnCart.visibility = View.GONE
        edtDeskripsi.visibility = View.GONE
        tvTotal.visibility = View.GONE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
        txtJudul.visibility = View.VISIBLE
        txtDesc.visibility = View.VISIBLE
        txthargaBarang.visibility = View.VISIBLE
        quantity.visibility = View.VISIBLE
        btnMinus.visibility = View.VISIBLE
        btnPlus.visibility = View.VISIBLE
        tvQuantity.visibility = View.VISIBLE
        tvDeskripsi.visibility = View.VISIBLE
        imgDetail.visibility = View.VISIBLE
        btnCart.visibility = View.VISIBLE
        edtDeskripsi.visibility = View.VISIBLE
        tvTotal.visibility = View.VISIBLE
    }

    override fun showDetailBarangFailed(message: String) {
        val message = "Tidak dapat memproses permintaan Anda karena kesalahan koneksi atau data kosong. Silakan coba lagi"
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        progressBar.visibility = View.GONE
        txtJudul.visibility = View.VISIBLE
        imgDetail.visibility = View.VISIBLE
        txtDesc.visibility = View.GONE
        txthargaBarang.visibility = View.GONE
        quantity.visibility = View.GONE
        btnMinus.visibility = View.GONE
        btnPlus.visibility = View.GONE
        tvQuantity.visibility = View.GONE
        tvDeskripsi.visibility = View.GONE
        btnCart.visibility = View.GONE
        edtDeskripsi.visibility = View.GONE
        tvTotal.visibility = View.GONE

        txtJudul.text = "Tidak Ada Data"
        imgDetail.setImageResource(R.drawable.empty)
    }

    override fun addCartFailed(message: String) {
        val message = "Tidak dapat memproses permintaan Anda karena kesalahan koneksi. Silakan coba lagi"
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
