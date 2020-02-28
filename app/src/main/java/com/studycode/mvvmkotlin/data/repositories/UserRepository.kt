package com.studycode.mvvmkotlin.data.repositories

import com.studycode.mvvmkotlin.data.db.AppDatabase
import com.studycode.mvvmkotlin.data.db.entities.User
import com.studycode.mvvmkotlin.data.network.MyApi
import com.studycode.mvvmkotlin.data.network.SafeApiRequest
import com.studycode.mvvmkotlin.data.network.responses.AuthResponse
import retrofit2.Response

class UserRepository(private val api: MyApi,
                     private val db: AppDatabase): SafeApiRequest() {

    suspend fun userLogin(email:String, password:String):AuthResponse{
        return apiRequest {
            api.userLogin(email, password)
        }
    }

    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)
    fun getUser() = db.getUserDao().getuser()


//    fun userSignUp(email: String,name:String,password: String):Response<AuthResponse>{
//        return MyApi().userSignup(name, email, password)
//    }
}