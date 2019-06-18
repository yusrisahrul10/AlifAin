package com.example.alifain.registerActivity

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
import com.example.alifain.R
import com.example.alifain.loginActivity.LoginActivity
import com.example.alifain.rest.ApiRepository

class RegisterActivity : AppCompatActivity() , RegisterView {


    private lateinit var tvLogin: TextView
    private lateinit var edtUsername : EditText
    private lateinit var edtEmail : EditText
    private lateinit var edtAlamat : EditText
    private lateinit var edtNoHp : EditText
    private lateinit var edtPassword : EditText
    private lateinit var btnRegister : Button
    private lateinit var presenter: RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        edtUsername = findViewById(R.id.username)
        edtEmail = findViewById(R.id.email)
        edtAlamat = findViewById(R.id.alamat)
        edtNoHp = findViewById(R.id.nohp)
        edtPassword = findViewById(R.id.password)
        btnRegister = findViewById(R.id.register)

        tvLogin = findViewById(R.id.tv_login)
        val text = "Already have an account? Log In."
        val ss = SpannableString(text)
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = object : Intent(this@RegisterActivity, LoginActivity::class.java){
                }
                startActivity(intent)
            }
        }
        setUpNetwork()
        RegisterClick()
        ss.setSpan(clickableSpan, 25, 31, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        tvLogin.text = ss
        tvLogin.movementMethod = LinkMovementMethod.getInstance()
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun moveIntentLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
    fun RegisterClick(){

        btnRegister.setOnClickListener {
            var username : String = edtUsername.text.toString()
            var email : String = edtEmail.text.toString()
            var alamat : String = edtAlamat.text.toString()
            var noHp : String = edtNoHp.text.toString()
            var password : String = edtPassword.text.toString()

            if(email.isEmpty() || username.isEmpty()){
                Toast.makeText(this, "FIELD TIDAK BOLEH KOSONG", Toast.LENGTH_SHORT).show()

            }else{
                presenter.registerAction(username,email,alamat,noHp,password)

            }
        }
    }

    fun setUpNetwork(){
        val apiRepository = ApiRepository()
        presenter = RegisterPresenter(this,apiRepository)
        presenter.RegisterPresenter(this)
    }

}
