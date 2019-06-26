package com.example.alifain.checkoutActivity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.alifain.R
import com.example.alifain.transaksiActivity.TransactionActivity
import com.example.alifain.model.kotahelsan.Rajaongkir

import com.example.alifain.model.kotahelsan.ResultKota
import com.example.alifain.model.ongkir.CostX
import com.example.alifain.model.provinsi.RajaongkirProvinsi
import com.example.alifain.model.provinsi.Result
import com.example.alifain.preference.MyPreference
import com.example.alifain.rest.ApiRepository

class CheckoutActivity : AppCompatActivity(), CheckoutView {

    private lateinit var btnPesanan: Button
    private lateinit var presenter: CheckoutPresenter
    private lateinit var spinnerProvinsi: Spinner
    private lateinit var spinnerKota: Spinner
    private lateinit var spinnerJne : Spinner
    private lateinit var result: Result
    private lateinit var resultKota: ResultKota
    private lateinit var resultCost : CostX
    private lateinit var txtSubTotal : TextView
    private lateinit var txtOngkir : TextView
    private lateinit var txtTotalBayar : TextView
    private lateinit var edtAlamat : EditText
    private lateinit var myPreference: MyPreference


    var totalgram: Int = 0
    var kotaTujuan: String = ""
    var totalOngkir : Int = 0
    var totalHarga : Int = 0
    var totalPembayaran : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        btnPesanan = findViewById(R.id.btnPesanan)

        spinnerProvinsi = findViewById(R.id.spinnerProv)
        spinnerKota = findViewById(R.id.spinnerKota)
        txtOngkir = findViewById(R.id.txtOngkir)
        txtSubTotal = findViewById(R.id.txtSubTotal)
        txtTotalBayar = findViewById(R.id.txtTotalBayar)
        edtAlamat = findViewById(R.id.edtAlamatCekout)
//        spinnerJne = findViewById(R.id.spinnerJne)
        totalgram = intent.getIntExtra("total_gram", 0)

//        Log.e("TOTAL GRAM", totalgram.toString())
        val apiRepository = ApiRepository()
        myPreference = MyPreference(this)
        presenter = CheckoutPresenter(this, apiRepository)
        presenter.CheckoutPresenter(this)
        presenter.getListProvinsi()
        btnPesanan.setOnClickListener {
            if(edtAlamat.text.isEmpty()){
                Toast.makeText(this, "Silahkan Isikan Alamat Lengkap Anda", Toast.LENGTH_SHORT).show()

            }else{
//        Log.e("RESPONE CEKOUT BUTTON ", myPreference.getIdUser()+" "+totalOngkir+" "+totalHarga+" "+edtAlamat.text.toString())

                presenter.cekoutPost(myPreference.getIdUser().toInt(),totalOngkir,totalHarga,edtAlamat.text.toString())

            }
        }

    }
    override fun moveIntent(id_transaksi : Int) {
        val intent = Intent(this, TransactionActivity::class.java)
        intent.putExtra("id_transaksi",id_transaksi)
        intent.putExtra("total_harga",totalHarga)
        finishAffinity()
        startActivity(intent)
    }
    override fun showListProvinsi(data: RajaongkirProvinsi) {


        spinnerProvinsi.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, data.results)
        spinnerProvinsi.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                result = spinnerProvinsi.selectedItem as Result
//                resultKota?.province_id = result.province_id
//                Log.e("id result kota", resultKota!!.province_id)
                Log.e("DATA KE 0", spinnerProvinsi.getItemAtPosition(0).toString())
                presenter.getListKota(result.province_id)
                totalPembayaran = 0
            }


        }

    }

    override fun showListKota(data: Rajaongkir) {
        spinnerKota.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, data.results)
        spinnerKota.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                totalPembayaran = 0
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                resultKota = spinnerKota.selectedItem as ResultKota
                kotaTujuan = resultKota.city_id
                presenter.getCost("23", kotaTujuan, totalgram.toString(), "jne")
                totalPembayaran()
            }

        }
    }

    override fun showCost(data: Int) {
        totalOngkir = data
        totalPembayaran = totalOngkir + totalHarga
        Log.e("TOTAL PEMBAYARAN", totalPembayaran.toString())
        Log.e("TOTAL ONGKIR", totalOngkir.toString())
        Log.e("TOTAL HARGA", totalHarga.toString())
        txtOngkir.text = "Rp. "+totalOngkir.toString()

        totalPembayaran()
    }
    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showListFailed(message: String) {
        val message = "Tidak dapat memproses permintaan Anda karena kesalahan koneksi. Silakan coba lagi"
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun totalPembayaran(){

        totalHarga = intent.getIntExtra("total_harga",0)
        txtSubTotal.text = "Rp. "+totalHarga.toString()
        txtTotalBayar.text = "Rp. "+totalPembayaran.toString()
    }
}
