package com.lans.foodricion.domain.interactor

import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.repository.IUserRepository
import com.lans.foodricion.domain.usecase.UpdateProfileUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UpdateProfileInteractor @Inject constructor(
    private val userRepository: IUserRepository
) : UpdateProfileUseCase {
    override suspend fun invoke(fullname: String, email: String): Flow<Resource<Boolean>> {
        return userRepository.updateProfile(fullname, email).flowOn(Dispatchers.IO)
    }
}