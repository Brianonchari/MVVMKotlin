package com.studycode.mvvmkotlin.ui.home.profile

import androidx.lifecycle.ViewModel
import com.studycode.mvvmkotlin.data.repositories.UserRepository

class ProfileViewModel(repository: UserRepository) : ViewModel() {

    val user = repository.getUser()
}
