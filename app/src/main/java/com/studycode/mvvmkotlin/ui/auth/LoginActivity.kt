package com.studycode.mvvmkotlin.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.studycode.mvvmkotlin.R
import com.studycode.mvvmkotlin.data.db.AppDatabase
import com.studycode.mvvmkotlin.data.db.entities.User
import com.studycode.mvvmkotlin.data.network.MyApi
import com.studycode.mvvmkotlin.data.network.NetworkConnectionInterceptor
import com.studycode.mvvmkotlin.data.repositories.UserRepository
import com.studycode.mvvmkotlin.databinding.ActivityLoginBinding
import com.studycode.mvvmkotlin.ui.home.HomeActivity
import com.studycode.mvvmkotlin.utils.hide
import com.studycode.mvvmkotlin.utils.show
import com.studycode.mvvmkotlin.utils.snackBar
import com.studycode.mvvmkotlin.utils.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListener {


    private val  TAG = "LoginActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        val api = MyApi(networkConnectionInterceptor)
        val db = AppDatabase(this)
        val repository = UserRepository(api, db)
        val factory = AuthViewModelFactory(repository)

        val binding:ActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this,factory).get(AuthViewModel::class.java)
        binding.myviewmodel = viewModel
        viewModel.authListener = this
        viewModel.getLoggedinUser().observe(this, Observer { user->
            if(user!=null){
                Intent(this, HomeActivity::class.java).also {
                    it.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })
    }

    override fun onStareted() {
        progress_bar.show()
    }

    override fun onSuccess(user: User) {
//        root_layout.snackBar( "${user.name} is logged in")
        progress_bar.hide()
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        root_layout.snackBar(message)
    }
}
