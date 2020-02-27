package com.studycode.mvvmkotlin.data.network.responses

import com.studycode.mvvmkotlin.data.db.entities.User

data class AuthResponse(
    val isSuccessful : Boolean?,
    val message: String?,
    val user: User?
)

