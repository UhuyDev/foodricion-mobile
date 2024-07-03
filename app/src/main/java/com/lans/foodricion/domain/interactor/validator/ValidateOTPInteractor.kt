package com.lans.foodricion.domain.interactor.validator

import com.lans.foodricion.domain.model.ValidationResult
import com.lans.foodricion.domain.usecase.validator.ValidateOTPUseCase

class ValidateOTPInteractor: ValidateOTPUseCase {
    override fun invoke(input: String): ValidationResult {
        if (input.isBlank()) {
            return ValidationResult(
                isSuccess = false,
                errorMessage = "OTP must be filled"
            )
        }

        return ValidationResult(
            isSuccess = true
        )
    }
}