package com.example.acronyms.data.repository

import com.example.acronyms.data.AcronymService
import com.example.acronyms.data.model.AcronymResponseItem
import com.example.acronyms.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AcronymRepository @Inject constructor(
    private val acronymService: AcronymService
) : IAcronymRepository {
    override suspend fun getAcronyms(input: String) = flow<DataState<List<AcronymResponseItem>>> {
        emit(DataState(isLoading = true))
        val result = runCatching {
            val response = acronymService.getAcronyms(input)
            if (response.isSuccessful) {
                DataState(data = response.body())
            } else {
                DataState(error = "Empty Response")
            }
        }.getOrElse {
            DataState(error = "Couldn't fetch data.")
        }
        emit(result)
    }
}

interface IAcronymRepository {
    suspend fun getAcronyms(input: String): Flow<DataState<List<AcronymResponseItem>>>
}