package com.example.alifain.loginActivity

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.util.Log
import android.widget.Toast
import com.example.alifain.MainActivity
import com.example.alifain.model.login.LoginRespones
import com.example.alifain.preference.MyPreference
import com.example.alifain.rest.ApiInterface
import com.example.alifain.rest.ApiRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(private val view: LoginView, private val apiRepository: ApiRepository) {
    private var context: Context? = null
    private var myPreference : MyPreference? = null
//     myPreference = MyPreference(context!!)

    fun LoginPresenter(context: Context , myPreference: MyPreference) {
        this.context = context
        this.myPreference = myPreference
    }


    fun LoginPush(email: String, password: String) {
        view.showLoading()
        val connect: ApiInterface = apiRepository.getUrl().create(ApiInterface::class.java)
        connect.loginAction(email, password).enqueue(object : Callback<LoginRespones> {
            override fun onFailure(call: Call<LoginRespones>, t: Throwable) {

            }

            override fun onResponse(call: Call<LoginRespones>, response: Response<LoginRespones>) {
                view.hideLoading()
                val push: String = response.body()!!.message
                val id_user : String? = response.body()?.data?.id_user
                val username : String? = response.body()?.data?.username
                val emailUser : String? = response.body()?.data?.email
                val alamat : String? = response.body()?.data?.alamat
                val nohp : String? = response.body()?.data?.nohp
                myPreference?.setIdUser(id_user.toString())
                myPreference?.setUsername(username.toString())
                myPreference?.setEmail(emailUser.toString())
                myPreference?.setAlamat(alamat.toString())
                myPreference?.setNoHp(nohp.toString())
                Log.e("tag", "responsennya ${push}")

                if (push.equals("sukses")) {
                    Toast.makeText(context, "Login berhasil ", Toast.LENGTH_SHORT).show()
                    moveIntent()
                } else{
                    Toast.makeText(context, "Username dan Password Salah", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }
     fun moveIntent(){
        view.moveIntent()
    }

}