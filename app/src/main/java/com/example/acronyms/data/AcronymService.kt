package com.example.acronyms.data

import com.example.acronyms.data.model.AcronymResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AcronymService {

    @GET("software/acromine/dictionary.py")
    suspend fun getAcronyms(
        @Query("sf") input: String
    ): Response<List<AcronymResponseItem>>
}