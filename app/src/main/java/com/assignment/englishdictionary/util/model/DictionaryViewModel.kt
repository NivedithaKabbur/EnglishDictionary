package com.assignment.englishdictionary.util.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.assignment.englishdictionary.util.DictionaryRepository
import com.assignment.englishdictionary.util.data.AntonymsData
import com.assignment.englishdictionary.util.data.SynonymsData
import com.assignment.englishdictionary.util.data.WordInfoData


class DictionaryViewModel: ViewModel() {

    private var result = MutableLiveData<ArrayList<String>>()
    private var wordInfo = MutableLiveData<WordInfoData>()
    private var dictionaryFetcher = DictionaryRepository()

    fun init(text: String, type: String, context: Context): LiveData<ArrayList<String>> {
     result =  dictionaryFetcher.getData(text, type, context)

        return result
    }

    fun getWordInfo(text: String) : LiveData<WordInfoData> {
        wordInfo = dictionaryFetcher.getWordInfo(text)

        return wordInfo
    }

    fun getSynonyms(text: String) : LiveData<SynonymsData> {
       return DictionaryRepository().getSynonyms(text)
    }

    fun getAntonyms(text: String) :LiveData<AntonymsData> {
        return DictionaryRepository().getAntonyms(text)
    }
}