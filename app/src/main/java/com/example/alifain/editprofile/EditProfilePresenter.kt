package com.example.alifain.editprofile

import android.content.Context
import android.widget.Toast
import com.example.alifain.model.profil.Data
import com.example.alifain.model.profil.ProfileResponse
import com.example.alifain.rest.ApiInterface
import com.example.alifain.rest.ApiRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfilePresenter(private val view: EditProfileView, private val apiRepository: ApiRepository) {
    private var context : Context? = null

    fun EditProfilePresenter(context: Context){
        this.context = context
    }

    fun updateUserProfile(id_user : String, username : String, email : String, alammat : String, nohp : String) {
        val connect: ApiInterface = apiRepository.getUrl().create(ApiInterface::class.java)
        connect.updateUser(id_user, username, email, alammat, nohp).enqueue(object : Callback<ProfileResponse>{
            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
                val get : String? = response.body()?.message
                val data : Data? = response.body()?.data
                if(get.equals("sukses")){
                    Toast.makeText(context, "Insert berhasil ", Toast.LENGTH_SHORT).show()
                    view.updateProfile(data!!)
                }else{
                    Toast.makeText(context, "Insert Gagal ", Toast.LENGTH_SHORT).show()

                }
            }

        })
    }
}