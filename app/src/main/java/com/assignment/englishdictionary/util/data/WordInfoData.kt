package com.assignment.englishdictionary.util.data

data class WordInfoData(
        val metadata: Metadata,
        val results: List<Result>
) {

    data class Metadata(
        val provider: String
    )


    data class Result(
            val id: String,
            val language: String,
            val lexicalEntries: List<LexicalEntry>,
            val type: String,
            val word: String
    ) {

        data class LexicalEntry(
                val language: String,
                val lexicalCategory: String,
                val pronunciations: List<Pronunciation>,
                val text: String
        ) {

            data class Pronunciation(
                val audioFile: String,
                val dialects: List<String>,
                val phoneticNotation: String,
                val phoneticSpelling: String
            )
        }
    }
}