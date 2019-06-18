package com.example.alifain.registerActivity

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.alifain.model.login.LoginRespones
import com.example.alifain.rest.ApiInterface
import com.example.alifain.rest.ApiRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterPresenter(private val view : RegisterView , private val apiRepository: ApiRepository) {

    private var context: Context? = null

    fun RegisterPresenter(context: Context ) {
        this.context = context
    }

    fun registerAction(username : String, email : String,alamat : String,nohp : String,password : String){
        view.showLoading()
        val connect : ApiInterface = apiRepository.getUrl().create(ApiInterface::class.java)
        connect.registerAction(username,email,alamat,nohp,password).enqueue(object : Callback<LoginRespones>{
            override fun onFailure(call: Call<LoginRespones>, t: Throwable) {

            }

            override fun onResponse(call: Call<LoginRespones>, response: Response<LoginRespones>) {
               view.hideLoading()
                val message : String? = response.body()?.message
                Log.e("tag", "responsennya ${message}")

                if(message.equals("sukses")){
                    Toast.makeText(context, "Register berhasil ", Toast.LENGTH_SHORT).show()
                    moveIntent()
                }else if(message == null){
                    Log.e("tag", "responsennya ${message}")
                    Toast.makeText(context, "Akun dengan Email tersebut sudah ada", Toast.LENGTH_SHORT).show()
                }
            }

        })

    }

    fun moveIntent(){
        view.moveIntentLogin()
    }
}