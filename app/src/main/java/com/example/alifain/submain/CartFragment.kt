package com.example.alifain.submain


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.alifain.CheckoutActivity

import com.example.alifain.R

class CartFragment : Fragment() {

    private lateinit var btnCheckout: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cart, container, false)
        btnCheckout = view.findViewById(R.id.btnCheckout)

        btnCheckout.setOnClickListener {
            val intent = Intent(context, CheckoutActivity::class.java)
            startActivity(intent)
        }
        return view
    }


}
