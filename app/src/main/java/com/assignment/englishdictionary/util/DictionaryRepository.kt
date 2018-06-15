package com.assignment.englishdictionary.util

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.assignment.englishdictionary.util.data.AntonymsData
import com.assignment.englishdictionary.util.data.SynonymsData
import com.assignment.englishdictionary.util.data.WordInfoData
import com.assignment.englishdictionary.util.network.ServiceBuilder
import com.assignment.englishdictionary.util.network.WordService
import retrofit2.Call
import retrofit2.Callback
import java.net.HttpURLConnection


class DictionaryRepository {

    fun getWordInfo(word: String): MutableLiveData<WordInfoData>{

        val data = MutableLiveData<WordInfoData>()
        var wordService = ServiceBuilder().buildService(WordService::class.java)
        val request = wordService.getWordInfo("en", word,"pronunciations")

        request.enqueue(object : Callback<WordInfoData> {
            override fun onResponse(call: Call<WordInfoData>, response: retrofit2.Response<WordInfoData>) {

                if(response.code().equals(HttpURLConnection.HTTP_OK)) {
                    Log.v("Niveditha", response.body().toString())
                    data.value = response.body()
                }
            }

            override fun onFailure(call: Call<WordInfoData>, t: Throwable) {

                Log.v("Niveditha","Falied...")
            }
        })

        return data
    }

    fun getSynonyms(word: String): MutableLiveData<SynonymsData> {

        val synonyms = MutableLiveData<SynonymsData>()
        var wordService = ServiceBuilder().buildService(WordService::class.java)
        val request = wordService.getSynonyms("en", word)

        request.enqueue(object : Callback<SynonymsData> {
            override fun onResponse(call: Call<SynonymsData>, response: retrofit2.Response<SynonymsData>) {

                if(response.code().equals(HttpURLConnection.HTTP_OK)) {
                    Log.v("Niveditha", response.body().toString())
                    synonyms.value = response.body()
                }
            }

            override fun onFailure(call: Call<SynonymsData>, t: Throwable) {

                Log.v("Niveditha","Falied...")
            }
        })

        return synonyms
    }

    fun getAntonyms(word: String): MutableLiveData<AntonymsData> {

        val antonyms = MutableLiveData<AntonymsData>()
        var wordService = ServiceBuilder().buildService(WordService::class.java)
        val request = wordService.getAntonyms("en", word)

        request.enqueue(object : Callback<AntonymsData> {
            override fun onResponse(call: Call<AntonymsData>, response: retrofit2.Response<AntonymsData>) {

                if(response.code().equals(HttpURLConnection.HTTP_OK)) {
                    Log.v("Niveditha", response.body().toString())
                    antonyms.value = response.body()
                }
            }

            override fun onFailure(call: Call<AntonymsData>, t: Throwable) {

                Log.v("Niveditha","Falied...")
            }
        })

        return antonyms
    }
}