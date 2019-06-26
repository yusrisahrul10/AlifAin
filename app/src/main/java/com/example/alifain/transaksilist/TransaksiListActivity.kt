package com.example.alifain.transaksilist

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.alifain.R
import com.example.alifain.adapter.ListTransaksiAdapter
import com.example.alifain.detailtransaksi.DetailTransaksiActivity
import com.example.alifain.model.listtransaksi.Data
import com.example.alifain.preference.MyPreference
import com.example.alifain.rest.ApiRepository
import com.example.alifain.transaksiActivity.TransactionActivity

class TransaksiListActivity : AppCompatActivity(), TransaksiListView {

    private var items : MutableList<Data> = mutableListOf()
    private lateinit var list: RecyclerView
    private lateinit var presenter: TransaksiListPresenter
    private lateinit var myPreference: MyPreference


    private lateinit var progressBar : ProgressBar
    private lateinit var tvKosong : TextView

//    lateinit var submit : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaksi_list)

        list = findViewById(R.id.rvListTransaksi)
        val apiRepository = ApiRepository()
        myPreference = MyPreference(this)


        progressBar = findViewById(R.id.progress_bar_list_transaksi)
        tvKosong = findViewById(R.id.tv_kosong_list_transaksi)

        presenter = TransaksiListPresenter(this, apiRepository)
        presenter.getListTransaksi(myPreference.getIdUser())


//        submit = intent.getStringExtra("submit_true")
    }


    override fun showListTransaksi(data: List<Data>) {
        items.addAll(data)
        list.adapter = ListTransaksiAdapter(this, items, { itemMatch: Data -> itemClick(itemMatch) })
        list.layoutManager = LinearLayoutManager(this)
        (list.adapter as ListTransaksiAdapter).notifyDataSetChanged()
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

    private fun itemClick(item : Data) {
        val intent = Intent(this, DetailTransaksiActivity::class.java)
        intent.putExtra("id_transaksi", item.id_transaksi)
//        intent.putExtra("bayar_true", submit)
        startActivity(intent)
    }

    override fun showListTransaksiFailed(message: String) {
        val message = "Tidak dapat memproses permintaan Anda karena kesalahan koneksi atau data kosong. Silakan coba lagi"
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        progressBar.visibility = View.GONE
        tvKosong.visibility = View.VISIBLE
        list.visibility = View.GONE
    }
}
