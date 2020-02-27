package com.studycode.mvvmkotlin.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.studycode.mvvmkotlin.R
import com.studycode.mvvmkotlin.data.db.entities.User
import com.studycode.mvvmkotlin.databinding.ActivityLoginBinding
import com.studycode.mvvmkotlin.utils.hide
import com.studycode.mvvmkotlin.utils.show
import com.studycode.mvvmkotlin.utils.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListener {


    private val  TAG = "LoginActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)

        binding.viewmodel = viewModel
        viewModel.authListener = this
    }

    override fun onStareted() {
        progress_bar.show()

}

    override fun onSuccess(user: User) {
        toast("${user.name} is logged in")
        progress_bar.hide()
    }


    override fun onFailure(message: String) {
        progress_bar.hide()
        toast(message)
    }
}
