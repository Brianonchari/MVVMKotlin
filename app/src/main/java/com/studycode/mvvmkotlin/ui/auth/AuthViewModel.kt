package com.studycode.mvvmkotlin.ui.auth

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.studycode.mvvmkotlin.data.repositories.UserRepository
import com.studycode.mvvmkotlin.utils.ApiException
import com.studycode.mvvmkotlin.utils.Coroutines
import com.studycode.mvvmkotlin.utils.NoInternetException

class AuthViewModel(private val repository: UserRepository) : ViewModel() {

    var name: String? = null
    var email: String? = null
    var password: String? = null
    var passwordconfirm: String? = null
    var authListener: AuthListener? = null

    fun getLoggedinUser() = repository.getUser()

     fun onLoginButtonClick(v: View) {
        authListener?.onStareted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid email or password")
            return
        }

        Coroutines.main {
            try {
                val authResponse = repository.userLogin(email!!, password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)

            } catch (e: ApiException) {
                Log.e("Exception", e.message)
                authListener?.onFailure(e.message!!)
            }catch (e:NoInternetException){
                authListener?.onFailure(e.message!!)
            }
        }
    }

    fun onSignupButtonClicked(v: View) {

        if (name.isNullOrEmpty() || email.isNullOrEmpty() || password.isNullOrEmpty() || passwordconfirm.isNullOrEmpty()) {
            authListener?.onFailure("All Fields are required")
            if (password != passwordconfirm) {
                authListener?.onFailure("Passwords do not match")
            }
        }
        if (name.isNullOrEmpty()) {
            authListener?.onFailure("Name is required")
            return
        }

        if (email.isNullOrEmpty()) {
            authListener?.onFailure("Email is required")
            return
        }

        if (password.isNullOrEmpty()) {
            authListener?.onFailure("Please enter a password")
            return
        }

        if (password != passwordconfirm) {
            authListener?.onFailure("Password did not match")
            return
        }

//        Coroutines.main {
//            val response = repository.userSignUp(email!!, name!!, password!!)
//            if (response.isSuccessful) {
//                authListener?.onSuccess(response.body()?.user!!)
//            }
//        }

    }

}