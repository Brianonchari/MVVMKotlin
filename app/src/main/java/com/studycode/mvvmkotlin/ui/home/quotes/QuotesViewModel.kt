package com.studycode.mvvmkotlin.ui.home.quotes

import androidx.lifecycle.ViewModel
import com.studycode.mvvmkotlin.data.repositories.QuotesRepository
import com.studycode.mvvmkotlin.utils.lazyDeferred

class QuotesViewModel(repository: QuotesRepository) : ViewModel() {
    val quotes by lazyDeferred { repository.getQuotes() }
}
