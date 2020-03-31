package com.studycode.mvvmkotlin.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.studycode.mvvmkotlin.data.db.AppDatabase
import com.studycode.mvvmkotlin.data.db.entities.Quote
import com.studycode.mvvmkotlin.data.network.MyApi
import com.studycode.mvvmkotlin.data.network.SafeApiRequest
import com.studycode.mvvmkotlin.data.preferences.PreferenceProvider
import com.studycode.mvvmkotlin.utils.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

private val MINIMUM_INTERVAL = 6

class QuotesRepository(
    private val api:MyApi,
    private val db:AppDatabase,
    private val prefs:PreferenceProvider
):SafeApiRequest(){
    private val quotes = MutableLiveData<List<Quote>>()

    init {
        quotes.observeForever { saveQuotes(it) }
    }

    suspend fun getQuotes():LiveData<List<Quote>>{
        return withContext(Dispatchers.IO){
            fetchQuotes()
            db.getQuoteDao().getQuotes()
        }
    }

    private suspend fun fetchQuotes(){
        val lastSavedAt = prefs.getlastSavedAt()
        if(lastSavedAt==null||isFetchNeeded(LocalDateTime.parse(lastSavedAt))){
            val response = apiRequest { api.getQuotes() }
            quotes.postValue(response.quote)
        }
    }

    private fun isFetchNeeded(savedAt: LocalDateTime):Boolean{
        return ChronoUnit.HOURS.between(savedAt, LocalDateTime.now())> MINIMUM_INTERVAL
    }

    private fun   saveQuotes(quotes:List<Quote>){
        Coroutines.io {
            prefs.savelastSavedAt(LocalDateTime.now().toString())
            db.getQuoteDao().saveAllQuotes(quotes)
        }
    }
}