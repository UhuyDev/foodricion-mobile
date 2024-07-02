package com.lans.foodricion.domain.usecase.validator

import com.lans.foodricion.domain.model.ValidationResult

interface ValidateConfirmPasswordUseCase {
    fun invoke(password: String, confirmPassword: String): ValidationResult
}