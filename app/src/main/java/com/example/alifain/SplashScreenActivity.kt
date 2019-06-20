package com.example.alifain

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.alifain.loginActivity.LoginActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var logo: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

//        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS)
        logo = findViewById(R.id.imageView)

        Handler().postDelayed({
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
            finish()
        }, splashTimeOut.toLong())

        val myanim = AnimationUtils.loadAnimation(this, R.anim.splashanimation)
        logo.startAnimation(myanim)
    }

    companion object {
        internal var splashTimeOut = 3000
    }

}
