package com.assignment.englishdictionary.util

import android.util.Log
import org.json.JSONObject

class JSONParser{

   public fun getSynonymnsList(result: String): ArrayList<String> {

        var obj = JSONObject(result)
        var list = arrayListOf<String>()
        var resultsArray = obj.getJSONArray("results")

       for( i in 0..resultsArray.length()-1) {
           var obj: JSONObject = resultsArray.getJSONObject(i);
           var lexicalEntriesArray = obj.getJSONArray("lexicalEntries")

           for(j in 0..lexicalEntriesArray.length()-1) {
               var obj: JSONObject = lexicalEntriesArray.getJSONObject(j);
               var entriesArray = obj.getJSONArray("entries")

               for(k in 0..entriesArray.length()-1) {
                   var obj: JSONObject = entriesArray.getJSONObject(k);
                   var sensesArray = obj.getJSONArray("senses")

                   for(l in 0..sensesArray.length()-1) {
                       var obj: JSONObject = sensesArray.getJSONObject(l);
                       var synonymsArray = obj.getJSONArray("synonyms")

                       for(m in 0..synonymsArray.length()-1) {
                           var obj: JSONObject = synonymsArray.getJSONObject(m);
                           var text = obj.getString("text")

                           list.add(text.toString())
                           Log.v("Niveditha",text.toString())
                       }
                   }

               }
           }
       }

       return list

    }
}