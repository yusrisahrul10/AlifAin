package com.example.alifain.transaksilist

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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

//    lateinit var submit : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaksi_list)

        list = findViewById(R.id.rvListTransaksi)
        val apiRepository = ApiRepository()
        myPreference = MyPreference(this)
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

    }

    override fun hideLoading() {

    }

    private fun itemClick(item : Data) {
        val intent = Intent(this, DetailTransaksiActivity::class.java)
        intent.putExtra("id_transaksi", item.id_transaksi)
//        intent.putExtra("bayar_true", submit)
        startActivity(intent)
    }

}
