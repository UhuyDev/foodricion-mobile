package com.lans.foodricion.domain.usecase.validator

import com.lans.foodricion.domain.model.ValidationResult

interface ValidateOTPUseCase {
    fun invoke(input: String): ValidationResult
}