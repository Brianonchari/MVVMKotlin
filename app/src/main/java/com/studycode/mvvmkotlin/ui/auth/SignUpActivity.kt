package com.studycode.mvvmkotlin.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.studycode.mvvmkotlin.R
import com.studycode.mvvmkotlin.data.db.entities.User
import com.studycode.mvvmkotlin.databinding.ActivityLoginBinding
import com.studycode.mvvmkotlin.databinding.ActivitySignUpBinding
import com.studycode.mvvmkotlin.ui.home.HomeActivity
import com.studycode.mvvmkotlin.utils.hide
import com.studycode.mvvmkotlin.utils.show
import com.studycode.mvvmkotlin.utils.toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.progress_bar
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignUpActivity : AppCompatActivity() , AuthListener,KodeinAware{


    override val kodein by kodein()
    private val factory :AuthViewModelFactory by instance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivitySignUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        val viewModel = ViewModelProviders.of(this,factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this

        viewModel.getLoggedinUser().observe(this, Observer { user ->
            if (user != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })
    }

    override fun onStareted() {
        progress_bar.show()
    }

    override fun onSuccess(user: User) {
         progress_bar.hide()

    }

    override fun onFailure(message: String) {
        progress_bar.hide()

    }
}
