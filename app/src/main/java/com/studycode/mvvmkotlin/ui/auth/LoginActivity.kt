package com.studycode.mvvmkotlin.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.studycode.mvvmkotlin.R

class LoginActivity : AppCompatActivity(), AuthListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onStareted() {
    }

    override fun onSuccess() {
    }

    override fun onFailure(message: String) {
    }
}
