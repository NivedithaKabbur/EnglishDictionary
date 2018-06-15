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
import com.assignment.englishdictionary.util.model.DictionaryViewModel
import com.assignment.englishdictionary.util.data.AntonymsData
import com.assignment.englishdictionary.util.data.SynonymsData
import com.assignment.englishdictionary.util.data.WordInfoData
import kotlinx.android.synthetic.main.activity_dictionary.*
import kotlinx.android.synthetic.main.content_dictionary.*


class DictionaryActivity : AppCompatActivity() {

    var mModel: DictionaryViewModel?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary)
        setSupportActionBar(toolbar)

        mModel = ViewModelProviders.of(this).get(DictionaryViewModel::class.java)

        var word = findViewById<EditText>(R.id.edit_word)

        btn_find.setOnClickListener {

            textView_syn.setText("")
            textView_atyn.setText("")

            if(word.text.toString()!= "") {

                textView_word.setText(word.text.toString())

                fetchWordInfo(word.text.toString())
                getSynonyms(word.text.toString())
                getAntonyms(word.text.toString())
            }
            else {
                Toast.makeText(this, "Please enter a word", Toast.LENGTH_SHORT).show()
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

    fun fetchWordInfo(word : String) {

        Log.v("Niveditha","Fetch word called...")

        mModel?.getWordInfo(word)?.observe(this, Observer<WordInfoData> { wordInfo ->

            tv_phonetic_notation.text = wordInfo?.results?.get(0)?.lexicalEntries?.get(0)?.pronunciations?.get(0)?.phoneticNotation
            tv_phonetic_spelling.text = wordInfo?.results?.get(0)?.lexicalEntries?.get(0)?.pronunciations?.get(0)?.phoneticSpelling

        })

    }

    fun getSynonyms(word: String) {

        mModel?.getSynonyms(word)?.observe(this, Observer<SynonymsData> { word ->

            var str: List<SynonymsData.Result.LexicalEntry.Entry.Sense.Synonym>? = word?.results?.get(0)?.lexicalEntries?.get(0)?.entries?.get(0)?.senses?.get(0)?.synonyms
            var syn_str = ""

            for(i in 0..(str?.size!! -1 )) {

                syn_str = syn_str+"| "+str.get(i).text
                        textView_syn.text = syn_str+""
            }
        })
    }

    fun getAntonyms(word: String) {

        mModel?.getAntonyms(word)?.observe(this, Observer<AntonymsData> { word ->

            var str: List<AntonymsData.Result.LexicalEntry.Entry.Sense.Antonym>? = word?.results?.get(0)?.lexicalEntries?.get(0)?.entries?.get(0)?.senses?.get(0)?.antonyms
            var atny_str = ""

            for(i in 0..(str?.size!! -1 )) {

                atny_str = atny_str+"| "+str.get(i).text
                textView_atyn.text = atny_str+""
            }
        })
    }
}

