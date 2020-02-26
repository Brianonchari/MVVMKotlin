package com.studycode.mvvmkotlin.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {
    var email: String? = null
    var password : String? = null


    fun onLoginButtonClicked(v : View){

        if(email.isNullOrEmpty()|| password.isNullOrEmpty()){
            //Throw Error

            return
        }


        //Success
    }

}