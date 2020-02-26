package com.studycode.mvvmkotlin.ui.auth

interface AuthListener {
    fun onStareted()
    fun onSuccess()
    fun onFailure(message: String)

}