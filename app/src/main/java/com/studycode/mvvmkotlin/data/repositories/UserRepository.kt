package com.studycode.mvvmkotlin.data.repositories

import com.studycode.mvvmkotlin.data.network.MyApi
import com.studycode.mvvmkotlin.data.network.responses.AuthResponse
import retrofit2.Response

class UserRepository {

    suspend fun userLogin(email:String, password:String):Response<AuthResponse>{

        return MyApi().userLogin(email, password)
    }

    fun userSignUp(email: String,name:String,password: String):Response<AuthResponse>{
        return MyApi().userSignup(name, email, password)
    }
}