package com.lans.foodricion.data.source.network.api

import com.lans.foodricion.data.source.network.dto.ApiResponse
import com.lans.foodricion.data.source.network.dto.request.ChangePasswordRequestDto
import com.lans.foodricion.data.source.network.dto.request.ForgotPasswordRequestDto
import com.lans.foodricion.data.source.network.dto.request.SignInRequestDto
import com.lans.foodricion.data.source.network.dto.request.SignUpRequestDto
import com.lans.foodricion.data.source.network.dto.request.VerifyOTPRequestDto
import com.lans.foodricion.data.source.network.dto.response.RefreshTokenResponseDto
import com.lans.foodricion.data.source.network.dto.response.SignInResponseDto
import com.lans.foodricion.data.source.network.dto.response.SignUpResponseDto
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

interface FoodricionApi {
    @FormUrlEncoded
    @POST("login")
    suspend fun signin(
        @Body requestDto: SignInRequestDto
    ): ApiResponse<SignInResponseDto>

    @POST("register")
    suspend fun signup(
        @Body requestBody: SignUpRequestDto,
    ): ApiResponse<SignUpResponseDto>

    @POST("refresh-token")
    suspend fun refreshToken(
        @Query("refresh_token") refreshToken: String,
    ): ApiResponse<RefreshTokenResponseDto>

    @POST("forgot-password")
    suspend fun forgotPassword(
        @Body requestBody: ForgotPasswordRequestDto,
    ): ApiResponse<Nothing>

    @POST("verify-otp")
    suspend fun verifyOTP(
        @Body requestBody: VerifyOTPRequestDto,
    ): ApiResponse<Nothing>

    @POST("/me/change-password")
    suspend fun changePassword(
        @Body requestBody: ChangePasswordRequestDto,
    ): ApiResponse<Nothing>
}