package com.lans.foodricion.domain.interactor.validator

import com.lans.foodricion.domain.usecase.validator.ValidateConfirmPasswordUseCase
import com.lans.foodricion.domain.usecase.validator.ValidateEmailUseCase
import com.lans.foodricion.domain.usecase.validator.ValidateFullnameUseCase
import com.lans.foodricion.domain.usecase.validator.ValidatePasswordUseCase
import com.lans.foodricion.domain.usecase.validator.ValidatorUseCase
import javax.inject.Inject

class ValidatorInteractor @Inject constructor(
    override val email: ValidateEmailUseCase,
    override val fullname: ValidateFullnameUseCase,
    override val password: ValidatePasswordUseCase,
    override val confirmPassword: ValidateConfirmPasswordUseCase
) : ValidatorUseCase