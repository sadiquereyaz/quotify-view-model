package com.reyaz.quotify

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var quoteText: TextView
    private lateinit var quoteAuthor: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(application)).get(MainViewModel::class.java)

        quoteText = findViewById(R.id.quoteTV)
        quoteAuthor = findViewById(R.id.authorTV)

        setQuote(mainViewModel.getQuote())
    }

    private fun setQuote(quote: EachQuote) {
        quoteText.text = quote.text
        quoteAuthor.text = quote.author
    }

    fun previous(view: View) {
        setQuote(mainViewModel.previousQuote())
    }

    fun next(view: View) {
        setQuote(mainViewModel.nextQuote())
    }

    fun share(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        val quote = mainViewModel.getQuote()
        intent.putExtra(Intent.EXTRA_TEXT, "${quote.text} - ${quote.author}")
        startActivity(Intent.createChooser(intent, "Share Quote"))
    }
}
