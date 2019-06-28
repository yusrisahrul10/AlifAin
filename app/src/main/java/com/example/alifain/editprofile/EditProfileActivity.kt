package com.example.alifain.editprofile

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.alifain.R
import com.example.alifain.detailtransaksi.DetailTransaksiPresenter
import com.example.alifain.model.profil.Data
import com.example.alifain.preference.MyPreference
import com.example.alifain.rest.ApiRepository

class EditProfileActivity : AppCompatActivity(), EditProfileView {

    private lateinit var edtUsername : EditText
    private lateinit var edtEmail : EditText
    private lateinit var edtAlamat : EditText
    private lateinit var edtNoHp : EditText

    private lateinit var btnSaveProfile : Button

    private lateinit var myPreference: MyPreference
    private lateinit var presenter: EditProfilePresenter

    private lateinit var back : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        edtUsername = findViewById(R.id.edtUsername)
        edtEmail = findViewById(R.id.edtEmail)
        edtAlamat = findViewById(R.id.edtAlamat)
        edtNoHp = findViewById(R.id.edtNoHp)
        btnSaveProfile = findViewById(R.id.btnSaveProfile)

        back = findViewById(R.id.iv_back_edt_profile)

        val apiRepository = ApiRepository()

        myPreference = MyPreference(this)
        presenter = EditProfilePresenter(this, apiRepository)
        presenter.EditProfilePresenter(this)


        btnSaveProfile.setOnClickListener {
            if (edtAlamat.text.isEmpty() || edtUsername.text.isEmpty() || edtEmail.text.isEmpty()
                || edtNoHp.text.isEmpty()
            ) {
                Toast.makeText(this, "SILAHKAN ISI FIELD YANG KOSONG", Toast.LENGTH_SHORT).show()
            } else {
                val alamat = edtAlamat.text.toString()
                val username = edtUsername.text.toString()
                val email = edtEmail.text.toString()
                val nohp = edtNoHp.text.toString()

                presenter.updateUserProfile(myPreference.getIdUser(), username, email, alamat, nohp)
            }

        }

        back.setOnClickListener {
            finishAffinity()
        }
    }
    override fun updateProfile(data: Data) {
        myPreference.setAlamat(data.alamat)
        myPreference.setEmail(data.email)
        myPreference.setNoHp(data.nohp)
        myPreference.setUsername(data.username)
    }
}
