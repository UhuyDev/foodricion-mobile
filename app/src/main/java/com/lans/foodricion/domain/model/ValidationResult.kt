package com.lans.foodricion.domain.model

data class ValidationResult(
    val isSuccess: Boolean,
    val errorMessage: String? = null
)
