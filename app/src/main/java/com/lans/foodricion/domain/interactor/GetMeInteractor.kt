package com.lans.foodricion.domain.interactor

import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.model.User
import com.lans.foodricion.domain.repository.IUserRepository
import com.lans.foodricion.domain.usecase.GetMeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMeInteractor @Inject constructor(
    private val userRepository: IUserRepository
) : GetMeUseCase {
    override suspend fun invoke(): Flow<Resource<User>> {
        return userRepository.me().flowOn(Dispatchers.IO)
    }
}