package com.assignment.englishdictionary.util

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.util.Log


class DictionaryViewModel: ViewModel() {

    private var synonyms = MutableLiveData<ArrayList<String>>()
    private var dictionaryFetcher = DictionaryFetcher()

    fun init(text: String, type: String, context: Context) {
//        if(this.synonyms!= null) {
//
//            return
//        }
        synonyms =  dictionaryFetcher.getData(text, type, context)

        Log.v("Niveditha", "Dictionary fetcher called...")
    }

     fun getDictionary() : LiveData<ArrayList<String>> {
        return synonyms
    }

}