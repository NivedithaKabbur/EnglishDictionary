package com.assignment.englishdictionary

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import com.assignment.englishdictionary.util.DictionaryViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        var word = findViewById<EditText>(R.id.edit_word)

        btn_find.setOnClickListener {

            if(word.text.toString()!= "") {

                Toast.makeText(this, word.text.toString(),Toast.LENGTH_LONG).show()
                getSynonyms(word.text.toString())
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun getSynonyms(text: String) {

//        var dataFetcher = DictionaryFetcher()
//        dataFetcher.getData(text, "synonyms", this)

        var mModel = ViewModelProviders.of(this).get(DictionaryViewModel::class.java)
        mModel.init(text, "synonyms", this)

        Log.v("Niveditha", "mModel initialized")

        mModel.getDictionary().observe(this, Observer<ArrayList<String>>{synonyms ->

            textView_result.setText(synonyms.toString())

        })

    }
}

