package com.assignment.englishdictionary.util.network

import com.assignment.englishdictionary.util.data.AntonymsData
import com.assignment.englishdictionary.util.data.SynonymsData
import com.assignment.englishdictionary.util.data.WordInfoData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WordService {

    @GET("entries/{source_lang}/{word_id}/{filters}")
    fun getWordInfo(@Path("source_lang") sourceLang: String, @Path("word_id") wordId: String, @Path("filters") filters: String): Call<WordInfoData>

    @GET("entries/{source_lang}/{word_id}/synonyms")
    fun getSynonyms(@Path("source_lang") sourceLang: String, @Path("word_id") wordId: String): Call<SynonymsData>

    @GET("entries/{source_lang}/{word_id}/antonyms")
    fun getAntonyms(@Path("source_lang") sourceLang: String, @Path("word_id") wordId: String): Call<AntonymsData>
}