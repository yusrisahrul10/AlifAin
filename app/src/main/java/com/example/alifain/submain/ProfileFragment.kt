package com.example.alifain.submain


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.alifain.editprofile.EditProfileActivity

import com.example.alifain.R
import com.example.alifain.preference.MyPreference
import com.example.alifain.transaksilist.TransaksiListActivity

class ProfileFragment : Fragment() {

    private lateinit var btnEdit : Button
    private lateinit var btnList : Button

    private lateinit var tvUsername : TextView
    private lateinit var tvEmail : TextView
    private lateinit var tvPhone : TextView
    private lateinit var tvAddress : TextView

    private lateinit var myPreference: MyPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        btnEdit = view.findViewById(R.id.btnEditProfile)
        btnList = view.findViewById(R.id.btnListTransaksi)

        tvAddress = view.findViewById(R.id.tvAddress)
        tvUsername = view.findViewById(R.id.tvUsername)
        tvEmail = view.findViewById(R.id.tvEmail)
        tvPhone = view.findViewById(R.id.tvPhone)

        myPreference = MyPreference(this.activity!!)



        tvPhone.text = myPreference.getNohp()
        tvAddress.text = myPreference.getAlamat()
        tvUsername.text = myPreference.getUsername()
        tvEmail.text = myPreference.getEmail()


        btnEdit.setOnClickListener {
            val intent = Intent(context, EditProfileActivity::class.java)
            startActivity(intent)
        }

        btnList.setOnClickListener {
            val intent = Intent(context, TransaksiListActivity::class.java)
            startActivity(intent)
        }
        return view
    }


}
