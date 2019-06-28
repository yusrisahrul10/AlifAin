package com.example.alifain.preference

import android.content.Context
import android.content.Intent
import com.example.alifain.MainActivity
import com.example.alifain.loginActivity.LoginActivity

class MyPreference(private var context: Context) {
    val PREFERENCE_NAME = "MyPreference"
    val ID_USER = "ID_USER"
    val USERNAME = "USERNAME"
    val EMAIL = "EMAIL"
    val ALAMAT = "ALAMAT"
    val NOHP = "NOHP"
    val IS_LOGIN = "is_login"

//    internal var _context: Context? = null


    val preference = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun getIdUser(): String {
        return preference.getString(ID_USER, "")
    }

    fun logOut() {
        val editor = preference.edit()
        editor.putBoolean(IS_LOGIN, false)
        editor.apply()
    }

    fun setIdUser(idUser: String) {
        val editor = preference.edit()
        editor.putBoolean(IS_LOGIN, true)
        editor.putString(ID_USER, idUser)
        editor.apply()
    }

    fun checkLogin() {
        if (!this.is_login()) {
            val i = Intent(context, LoginActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context!!.startActivity(i)
        }else{
            val i = Intent(context, MainActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context!!.startActivity(i)
        }
    }

    fun is_login(): Boolean {
        return preference.getBoolean(IS_LOGIN, false)
    }

    fun setUsername(username: String) {
        val editor = preference.edit()
        editor.putString(USERNAME, username)
        editor.apply()
    }

    fun getUsername(): String {
        return preference.getString(USERNAME, "")
    }

    fun setEmail(email: String) {
        val editor = preference.edit()
        editor.putString(EMAIL, email)
        editor.apply()
    }

    fun getEmail(): String {
        return preference.getString(EMAIL, "")
    }

    fun setAlamat(alamat: String) {
        val editor = preference.edit()
        editor.putString(ALAMAT, alamat)
        editor.apply()
    }

    fun getAlamat(): String {
        return preference.getString(ALAMAT, "")
    }

    fun setNoHp(noHp: String) {
        val editor = preference.edit()
        editor.putString(NOHP, noHp)
        editor.apply()
    }

    fun getNohp(): String {
        return preference.getString(NOHP, "")
    }
}