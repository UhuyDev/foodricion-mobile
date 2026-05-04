package com.lans.foodricion.domain.interactor

import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.repository.IUserRepository
import com.lans.foodricion.domain.usecase.ChangePasswordUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ChangePasswordInteractor @Inject constructor(
    private val userRepository: IUserRepository
) : ChangePasswordUseCase {
    override suspend fun invoke(oldPassword: String, newPassword: String): Flow<Resource<Boolean>> {
        return userRepository.changePassword(oldPassword, newPassword).flowOn(Dispatchers.IO)
    }
}