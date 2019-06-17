package com.example.alifain.preference

import android.content.Context

class MyPreference(context: Context) {
    val PREFERENCE_NAME = "MyPreference"
    val ID_USER = "ID_USER"
    val USERNAME = "USERNAME"
    val EMAIL = "EMAIL"
    val ALAMAT = "ALAMAT"
    val NOHP = "NOHP"

    val preference = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun getIdUser() : String {
        return preference.getString(ID_USER,"")
    }

    fun setIdUser(idUser : String){
        val editor = preference.edit()
        editor.putString(ID_USER,idUser)
        editor.apply()
    }

    fun setUsername(username : String){
        val editor = preference.edit()
        editor.putString(USERNAME,username)
        editor.apply()
    }
    fun getUsername(): String{
        return preference.getString(USERNAME,"")
    }
    fun setEmail(email : String){
        val editor = preference.edit()
        editor.putString(EMAIL,email)
        editor.apply()
    }
    fun getEmail(): String{
        return preference.getString(EMAIL,"")
    }
    fun setAlamat(alamat : String){
        val editor = preference.edit()
        editor.putString(ALAMAT,alamat)
        editor.apply()
    }
    fun getAlamat(): String{
        return preference.getString(ALAMAT,"")
    }
    fun setNoHp(noHp : String){
        val editor = preference.edit()
        editor.putString(NOHP,noHp)
        editor.apply()
    }
    fun getNohp(): String{
        return preference.getString(NOHP,"")
    }
}