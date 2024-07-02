package com.lans.foodricion.domain.usecase.validator

interface ValidatorUseCase {
    val email: ValidateEmailUseCase
    val fullname: ValidateFullnameUseCase
    val password: ValidatePasswordUseCase
    val confirmPassword: ValidateConfirmPasswordUseCase
}