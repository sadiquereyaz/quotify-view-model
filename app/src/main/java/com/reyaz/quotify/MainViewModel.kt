package com.reyaz.quotify

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context: Context) : ViewModel() {
    private var quoteList: Array<EachQuote> = arrayOf()
    var index: Int = 0

    init {
        quoteList = loatQuoteFromAsset()
    }
    private val size:Int = quoteList.size

    private fun loatQuoteFromAsset(): Array<EachQuote> {
        val inputStream = context.assets.open("quotes.json")
        val size: Int = inputStream.available() // size of inputStream
        val buffer = ByteArray(size)      // buffer hold this file
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, Array<EachQuote>::class.java)
    }

    fun getQuote() = quoteList[index]
    fun nextQuote(): EachQuote {
        index = (15 + index + 1) % 15
//        Log.d("TAGGG+", (index).toString())
        return quoteList[index]
    }

    fun previousQuote(): EachQuote {

        index = (size + index - 1) % size
//        Log.d("TAGGG-", (index).toString())
//        Log.d("Size-", (quoteList.size).toString())
        return quoteList[index]
    }
}