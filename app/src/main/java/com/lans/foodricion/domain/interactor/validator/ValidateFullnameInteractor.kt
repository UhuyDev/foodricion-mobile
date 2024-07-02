package com.lans.foodricion.domain.interactor.validator

import com.lans.foodricion.domain.model.ValidationResult
import com.lans.foodricion.domain.usecase.validator.ValidateFullnameUseCase

class ValidateFullnameInteractor : ValidateFullnameUseCase {
    override fun invoke(input: String): ValidationResult {
        if (input.isBlank()) {
            return ValidationResult(
                isSuccess = false,
                errorMessage = "Fullname must be filled"
            )
        }

        return ValidationResult(
            isSuccess = true
        )
    }
}