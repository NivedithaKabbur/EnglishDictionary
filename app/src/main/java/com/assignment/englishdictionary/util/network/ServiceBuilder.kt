package com.assignment.englishdictionary.util.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceBuilder {

    private val URL: String = "https://od-api.oxforddictionaries.com/api/v1/"

    //Create okHttpClient
    private val okHttp: OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor( object: Interceptor {

        override fun intercept(chain: Interceptor.Chain?): Response {

            Log.v("Niveditha", "intercept called...")
            var request: Request = chain?.request()!!

            request = request.newBuilder()
                    .addHeader("app_id", "89aa363b")
                    .addHeader("app_key", "b54bb6e8546b7f3ec6e65418b63b3b14")
                    .build()

            Log.v("Niveditha", "Chained request  :"+chain.proceed(request).toString())
            return chain.proceed(request)
        }
    })

    private val builder: Retrofit.Builder = Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).client(okHttp.build())
    private val retrofit : Retrofit = builder.build()

     fun <S> buildService(serviceType: Class<S>): S {

        return retrofit.create(serviceType)
    }
}