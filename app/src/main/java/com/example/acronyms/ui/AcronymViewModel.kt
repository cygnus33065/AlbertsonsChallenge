package com.example.acronyms.ui

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.acronyms.data.model.AcronymResponseItem
import com.example.acronyms.data.repository.AcronymRepository
import com.example.acronyms.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AcronymViewModel @Inject constructor(
    private val acronymRepository: AcronymRepository
) : ViewModel() {

    private val _dataState = MutableLiveData(DataState<List<AcronymResponseItem>>())
    val dataState: LiveData<DataState<List<AcronymResponseItem>>> get() = _dataState

    private val _input = MutableLiveData<String>("")
    val input: LiveData<String> get() = _input

    fun getAcronyms() {
        if (input.value.isNullOrEmpty()) {
            _dataState.value = _dataState.value?.copy(error = "Search cannot be empty.")
            return
        } else {
            viewModelScope.launch {
                acronymRepository.getAcronyms(input.value.orEmpty()).collect {
                    _input.value = ""
                    _dataState.value = it
                }
            }
        }

    }

    fun onInput(input: Editable) {
        _input.value = input.toString()
    }

    // Resets error state to avoid showing error multiple times
    fun onErrorShown() {
        _dataState.value?.let { value ->
            _dataState.value = value.copy(error = null)
        }
    }

}