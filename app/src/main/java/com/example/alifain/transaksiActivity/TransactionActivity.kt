package com.example.alifain.transaksiActivity

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.alifain.MainActivity
import com.example.alifain.R
import com.example.alifain.model.transaksi.DataTransaksi
import com.example.alifain.rest.ApiRepository

class TransactionActivity : AppCompatActivity(), TransaksiView {



    private lateinit var presenter: TransaksiPresenter
    private lateinit var txtHarga: TextView
    private lateinit var txtOngkir: TextView
    private lateinit var txtTotalHarga: TextView
    private lateinit var txtTanggal: TextView
    private lateinit var txtAlamat: TextView
    private lateinit var btnSubmit : Button
    private lateinit var edtNama : EditText
    private lateinit var edtBank : EditText
    private lateinit var edtTransfer : EditText
    private lateinit var edtTglTf : EditText

    var id_transaksi: Int? = 0
    var total_harga: Int? = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)
        id_transaksi = intent.getIntExtra("id_transaksi", 0)
        txtHarga = findViewById(R.id.tv_total_barang)
        txtOngkir = findViewById(R.id.tv_total_ongkir)
        txtTotalHarga = findViewById(R.id.tv_total_bayar)
        txtTanggal = findViewById(R.id.txtTglTransaksi)
        txtAlamat = findViewById(R.id.txtAlamatTransaksi)
        btnSubmit = findViewById(R.id.buttonSubmit)
        edtNama = findViewById(R.id.editTextNama)
        edtBank = findViewById(R.id.editTextBank)
        edtTransfer = findViewById(R.id.editTextTransfer)
        edtTglTf = findViewById(R.id.editTextTgl)
        val apiRepository = ApiRepository()
        presenter = TransaksiPresenter(this, apiRepository)
        presenter.TransaksiPresenter(this)
        presenter.getTransaksi(id_transaksi.toString())
        Log.e("id_transaksi ", id_transaksi.toString())

        btnKlik()
//        Log.e("Total Harga  ", total_harga.toString())


    }


    override fun moveIntent() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun btnKlik(){
        btnSubmit.setOnClickListener {
            if(edtNama.text.isEmpty() || edtBank.text.isEmpty()){
                Toast.makeText(this, "SILAHKAN ISI FIELD YANG KOSONG", Toast.LENGTH_SHORT).show()
            }else{
                presenter.postTransaksi(
                    edtNama.text.toString(),
                    edtBank.text.toString(),
                    edtTransfer.text.toString(),
                    edtTglTf.text.toString(),
                    id_transaksi.toString()
                )
            }
        }
    }


    override fun showDataTransaksi(data: DataTransaksi) {
        total_harga = data.ongkir + data.total_harga
        txtHarga.text = data.total_harga.toString()
        txtOngkir.text = data.ongkir.toString()
        txtTotalHarga.text = total_harga.toString()
        txtTanggal.text = data.tgl_transaksi
        txtAlamat.text = data.alamat_penerima
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }
}
