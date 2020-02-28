package com.studycode.mvvmkotlin.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProviders
import com.studycode.mvvmkotlin.R
import com.studycode.mvvmkotlin.data.db.entities.User
import com.studycode.mvvmkotlin.databinding.ActivitySignUpBinding
import com.studycode.mvvmkotlin.utils.hide
import com.studycode.mvvmkotlin.utils.show
import com.studycode.mvvmkotlin.utils.toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.progress_bar

class SignUpActivity : AppCompatActivity() , AuthListener{



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivitySignUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
    }

    override fun onStareted() {
        progress_bar.show()
    }

    override fun onSuccess(user: User) {
        toast("${user.name} is signed up")
        progress_bar.hide()

    }


    override fun onFailure(message: String) {
        progress_bar.hide()

    }
}
