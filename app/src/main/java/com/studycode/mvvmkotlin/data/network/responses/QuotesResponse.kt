package com.studycode.mvvmkotlin.data.network.responses

import com.studycode.mvvmkotlin.data.db.entities.Quote

data class QuotesResponse(
    val isSuccessful:Boolean,
    val quote: List<Quote>
)