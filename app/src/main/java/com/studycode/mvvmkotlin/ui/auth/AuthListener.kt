package com.studycode.mvvmkotlin.ui.auth

import androidx.lifecycle.LiveData
import com.studycode.mvvmkotlin.data.db.entities.User

interface AuthListener {
    fun onStareted()
    fun onSuccess(user: User)
    fun onFailure(message: String)

}