package com.example.alifain.submain.cart


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.alifain.CheckoutActivity

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cart, container, false)
        btnCheckout = view.findViewById(R.id.btnCheckout)
        list = view.findViewById(R.id.rvListCart)
        val apiRepository = ApiRepository()
        presenter = CartPresenter(this,apiRepository)
        myPreference = MyPreference(this.activity!!)
        presenter.getListCart(myPreference.getIdUser())
        btnCheckout.setOnClickListener {
            val intent = Intent(context, CheckoutActivity::class.java)
            startActivity(intent)
        }
        return view
    }

    private fun itemClick(item : Data){
        Toast.makeText(context, "Klik Cart Testing ${item.nama_barang}", Toast.LENGTH_SHORT).show()

    }

    override fun showListCart(data: List<Data>) {
        items.addAll(data)
        list.adapter = CartAdapter(context, items, { itemMatch: Data -> itemClick(itemMatch) })
        list.layoutManager = LinearLayoutManager(context)
        (list.adapter as CartAdapter).notifyDataSetChanged()
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }


}
