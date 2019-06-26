package com.example.alifain.loginActivity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.alifain.MainActivity
import com.example.alifain.R
import com.example.alifain.registerActivity.RegisterActivity
import com.example.alifain.preference.MyPreference
import com.example.alifain.rest.ApiRepository

class LoginActivity : AppCompatActivity() , LoginView {

    private lateinit var btnLogin: Button
    private lateinit var tvSignUp: TextView
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword : EditText
    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin = findViewById(R.id.btn_login)
        tvSignUp = findViewById(R.id.tv_signup)
        edtEmail = findViewById(R.id.edtTextMail)
        edtPassword = findViewById(R.id.edtPassword)


        getData()
        btnLogin.setOnClickListener {
            presenter.LoginPush(edtEmail.text.toString(), edtPassword.text.toString())

        }

        signUpClick()
    }
    override fun moveIntent() {
        val intent = Intent(this, MainActivity::class.java)
        finishAffinity()
        startActivity(intent)

    }
    private fun signUpClick() {
        val text = "Don't have an account? Sign Up."
        val ss = SpannableString(text)
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = object : Intent(this@LoginActivity, RegisterActivity::class.java){
                }

                finishAffinity()
                startActivity(intent)
            }
        }
        ss.setSpan(clickableSpan, 23, 30, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        tvSignUp.text = ss
        tvSignUp.movementMethod = LinkMovementMethod.getInstance()
    }


    override fun showLoading() {

    }

    override fun hideLoading() {

    }
    fun getData(){
        val apiRepository = ApiRepository()
        val myPreference = MyPreference(this)
        presenter = LoginPresenter(this,apiRepository)
        presenter.LoginPresenter(this,myPreference)


    }

    override fun failedLogin(message: String) {
        val message = "Tidak dapat memproses permintaan Anda karena kesalahan koneksi. Silakan coba lagi"
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}
