package com.example.alifain.submain


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.alifain.EditProfileActivity

import com.example.alifain.R
import com.example.alifain.checkoutActivity.CheckoutActivity

class ProfileFragment : Fragment() {

    private lateinit var btnEdit : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        btnEdit = view.findViewById(R.id.btnEditProfile)

        btnEdit.setOnClickListener {
            val intent = Intent(context, EditProfileActivity::class.java)
            startActivity(intent)
        }

        return view
    }


}
