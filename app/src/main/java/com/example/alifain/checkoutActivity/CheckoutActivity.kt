package com.example.alifain.checkoutActivity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.alifain.R
import com.example.alifain.TransactionActivity
import com.example.alifain.model.provinsi.Result
import com.example.alifain.rest.ApiRepository

class CheckoutActivity : AppCompatActivity() , CheckoutView {


    private lateinit var btnPesanan: Button
    private lateinit var presenter: CheckoutPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        btnPesanan = findViewById(R.id.btnBuatPesanan)
        val apiRepository = ApiRepository()
        presenter = CheckoutPresenter(this,apiRepository)
        presenter.getListProvinsi()
        btnPesanan.setOnClickListener {
            val intent = Intent(this, TransactionActivity::class.java)
            startActivity(intent)
        }
    }
    override fun showListProvinsi(data: List<Result>) {

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }
}
