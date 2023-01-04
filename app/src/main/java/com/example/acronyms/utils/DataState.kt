package com.example.acronyms.utils

data class DataState<T>(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: T? = null
)