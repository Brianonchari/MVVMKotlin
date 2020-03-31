package com.studycode.mvvmkotlin.ui.home.quotes

import com.studycode.mvvmkotlin.R
import com.studycode.mvvmkotlin.data.db.entities.Quote
import com.studycode.mvvmkotlin.databinding.ItemQuoteBinding
import com.xwray.groupie.databinding.BindableItem

class QuoteItem(private val quote:Quote): BindableItem<ItemQuoteBinding>(){
    override fun getLayout() = R.layout.item_quote
    override fun bind(viewBinding: ItemQuoteBinding, position: Int) {
        viewBinding.setQuote(quote)
    }

}