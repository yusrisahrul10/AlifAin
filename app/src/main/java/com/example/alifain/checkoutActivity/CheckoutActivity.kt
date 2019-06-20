package com.example.alifain.checkoutActivity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.example.alifain.R
import com.example.alifain.TransactionActivity
import com.example.alifain.model.kota.RajaOngkir
import com.example.alifain.model.kota.ResultKota
import com.example.alifain.model.provinsi.Rajaongkir
import com.example.alifain.model.provinsi.Result
import com.example.alifain.rest.ApiRepository

class CheckoutActivity : AppCompatActivity() , CheckoutView {



    private lateinit var btnPesanan: Button
    private lateinit var presenter: CheckoutPresenter
    private lateinit var spinnerProvinsi: Spinner
    private lateinit var spinnerKota: Spinner
    private lateinit var result: Result
    private  var resultKota: ResultKota? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        btnPesanan = findViewById(R.id.btnPesanan)

        spinnerProvinsi = findViewById(R.id.spinnerProv)
        spinnerKota = findViewById(R.id.spinnerKota)

        val apiRepository = ApiRepository()
        presenter = CheckoutPresenter(this,apiRepository)
        presenter.getListProvinsi()
//        presenter.getListKota("1")
        btnPesanan.setOnClickListener {
            val intent = Intent(this, TransactionActivity::class.java)
            startActivity(intent)
        }
    }
    override fun showListProvinsi(data: Rajaongkir) {
        spinnerProvinsi.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, data.results)
        spinnerProvinsi.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                result = spinnerProvinsi.selectedItem as Result
                Log.e("dapat id bengkulu", result.province_id)
//                resultKota?.province_id = result.province_id
//                Log.e("id result kota", resultKota!!.province_id)
//                presenter.getListKota(resultKota!!.province_id)
            }



        }

    }

    override fun showListKota(data: RajaOngkir) {
        spinnerKota.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, data.results)
        spinnerProvinsi.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                resultKota = spinnerKota.selectedItem as ResultKota
            }

        }
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }
}
