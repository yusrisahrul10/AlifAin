package com.example.alifain.registerActivity

interface RegisterView {
    fun showLoading()
    fun hideLoading()
    fun moveIntentLogin()
    fun registerFailed(message : String)
}