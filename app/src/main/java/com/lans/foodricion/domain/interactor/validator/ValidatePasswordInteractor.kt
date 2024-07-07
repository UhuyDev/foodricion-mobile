package com.lans.foodricion.domain.interactor.validator

import com.lans.foodricion.domain.model.ValidationResult
import com.lans.foodricion.domain.usecase.validator.ValidatePasswordUseCase
import java.util.regex.Pattern

class ValidatePasswordInteractor : ValidatePasswordUseCase {
    override fun invoke(input: String): ValidationResult {
        if (input.isBlank()) {
            return ValidationResult(
                isSuccess = false,
                errorMessage = "Password must be filled"
            )
        }

        if (input.length < 8) {
            return ValidationResult(
                isSuccess = false,
                errorMessage = "Password must be at least 8 characters long"
            )
        }

        if (Pattern.matches("[a-zA-Z0-9]", input)) {
            return ValidationResult(
                isSuccess = false,
                errorMessage = "Please include at least one letter and one number in your password"
            )
        }

        return ValidationResult(
            isSuccess = true,
            errorMessage = null
        )
    }
}