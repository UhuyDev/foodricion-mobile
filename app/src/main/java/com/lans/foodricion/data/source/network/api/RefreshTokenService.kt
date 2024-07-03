package com.lans.foodricion.data.source.network.api

import com.lans.foodricion.data.source.network.dto.ApiResponse
import com.lans.foodricion.data.source.network.dto.response.RefreshTokenResponseDto
import retrofit2.http.POST
import retrofit2.http.Query

interface RefreshTokenService {
    @POST("refresh-token")
    suspend fun refreshToken(
        @Query("refresh_token") refreshToken: String,
    ): ApiResponse<RefreshTokenResponseDto>
}