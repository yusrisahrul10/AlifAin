package com.example.alifain

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView

class RegisterActivity : AppCompatActivity() {

    private lateinit var tvLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

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

        ss.setSpan(clickableSpan, 25, 31, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        tvLogin.text = ss
        tvLogin.movementMethod = LinkMovementMethod.getInstance()
    }
}
