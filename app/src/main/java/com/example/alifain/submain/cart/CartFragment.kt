package com.example.alifain.submain.cart


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.alifain.checkoutActivity.CheckoutActivity

import com.example.alifain.R
import com.example.alifain.adapter.CartAdapter
import com.example.alifain.model.cart.Data
import com.example.alifain.preference.MyPreference
import com.example.alifain.rest.ApiRepository

class CartFragment : Fragment() , CartView {


    private lateinit var btnCheckout: Button
    private var items: MutableList<Data> = mutableListOf()
    private lateinit var list: RecyclerView
    private lateinit var presenter: CartPresenter
    private lateinit var myPreference: MyPreference

    private lateinit var tvTotalHarga: TextView
    var totalgram : Int = 0
    var totalHarga : Int = 0

    private lateinit var progressBar : ProgressBar
    private lateinit var tvKosong : TextView

    private lateinit var textTtlItems : TextView
    private lateinit var textViewTotal : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cart, container, false)
        btnCheckout = view.findViewById(R.id.btnCheckout)
        list = view.findViewById(R.id.rvListCart)

        progressBar =view.findViewById(R.id.progress_bar_cart)
        tvKosong = view.findViewById(R.id.tv_kosong_cart)

        tvTotalHarga = view.findViewById(R.id.totalHarga)

        textTtlItems = view.findViewById(R.id.textTtlItems)
        textViewTotal = view.findViewById(R.id.textViewTotal)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiRepository = ApiRepository()
        presenter = CartPresenter(this,apiRepository)
        myPreference = MyPreference(this.activity!!)


        presenter.getListCart(myPreference.getIdUser())

    }

    private fun itemClick(item : Data){
        Toast.makeText(context, "Klik Cart Testing ${item.nama_barang}", Toast.LENGTH_SHORT).show()
    }



    override fun showListCart(data: List<Data>) {
        btnCheckout.setOnClickListener {
            if (data.size == 0) {
                Toast.makeText(activity, "Keranjang Anda kosong. Silakan pilih barang yang ingin dibeli", Toast.LENGTH_SHORT).show()
            } else{
                val intent = Intent(context, CheckoutActivity::class.java)
                intent.putExtra("total_gram",totalgram)
                intent.putExtra("total_harga",totalHarga)
                startActivity(intent)
            }
        }
        items.addAll(data)
        list.adapter = CartAdapter(context, items, { itemMatch: Data -> itemClick(itemMatch) }, tvTotalHarga)
        list.layoutManager = LinearLayoutManager(context)
        (list.adapter as CartAdapter).notifyDataSetChanged()
        Log.e("SIZE CART", data.size.toString())
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
        tvKosong.visibility = View.GONE
        list.visibility = View.GONE
        textTtlItems.visibility = View.GONE
        textViewTotal.visibility = View.GONE
        tvTotalHarga.visibility = View.GONE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
        tvKosong.visibility = View.GONE
        list.visibility = View.VISIBLE
        textTtlItems.visibility = View.VISIBLE
        textViewTotal.visibility = View.VISIBLE
        tvTotalHarga.visibility = View.VISIBLE
    }

    override fun showTotalHarga(harga: Int) {
        tvTotalHarga.text = "Rp. " + harga
        totalHarga = harga
    }
    override fun showTotalGram(gram: Int) {
        totalgram = gram
    }

    override fun showCartFailed(message: String) {
        val message = "Tidak dapat memproses permintaan Anda karena kesalahan koneksi atau data kosong. Silakan coba lagi"
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        progressBar.visibility = View.GONE
        tvKosong.visibility = View.VISIBLE
        list.visibility = View.GONE
        textTtlItems.visibility = View.GONE
        textViewTotal.visibility = View.GONE
        tvTotalHarga.visibility = View.GONE
    }

}
