package com.assignment.englishdictionary.util

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.android.volley.AuthFailureError
import com.android.volley.NetworkResponse
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.assignment.englishdictionary.R
import org.json.JSONObject
import java.net.HttpURLConnection
import java.util.*


class DictionaryFetcher {

    private var status_code = 0;

    fun getData(text : String, type: String, context: Context): MutableLiveData<ArrayList<String>> {

        var data = MutableLiveData<ArrayList<String>>()

        val jsonObjectRequest = object: JsonObjectRequest(Request.Method.GET, context.getString(R.string.url)+text+"/"+type, null,
                Response.Listener { response ->

                    if(status_code == HttpURLConnection.HTTP_OK) {

                        var parser = JSONParser()
                        var list =  parser.getSynonymnsList(response.toString())

                        data.value = list
                    }
                },
                Response.ErrorListener { error ->
                    // TODO: Handle error
                    error.printStackTrace()
                }
        ){
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {

                val headers = HashMap<String, String>()
                headers["app_id"] = context.getString(R.string.app_id)
                headers["app_key"] = context.getString(R.string.app_key)
                return headers
            }

            override fun parseNetworkResponse(response: NetworkResponse?): Response<JSONObject> {
                status_code = response?.statusCode!!
                return super.parseNetworkResponse(response)
            }

        }

        // Access the RequestQueue through your singleton class.
        VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest)

        return data
    }
}