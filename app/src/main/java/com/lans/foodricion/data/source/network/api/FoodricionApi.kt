package com.lans.foodricion.data.source.network.api

import com.lans.foodricion.data.source.network.dto.ApiResponse
import com.lans.foodricion.data.source.network.dto.request.UpdateProfileRequestDto
import com.lans.foodricion.data.source.network.dto.request.ChangePasswordRequestDto
import com.lans.foodricion.data.source.network.dto.request.ChatbotRequestDto
import com.lans.foodricion.data.source.network.dto.request.ForgotPasswordRequestDto
import com.lans.foodricion.data.source.network.dto.request.SignInRequestDto
import com.lans.foodricion.data.source.network.dto.request.SignUpRequestDto
import com.lans.foodricion.data.source.network.dto.request.VerifyOTPRequestDto
import com.lans.foodricion.data.source.network.dto.response.ChatbotHistoryResponseDto
import com.lans.foodricion.data.source.network.dto.response.GetFoodResponseDto
import com.lans.foodricion.data.source.network.dto.response.MeResponseDto
import com.lans.foodricion.data.source.network.dto.response.RefreshTokenResponseDto
import com.lans.foodricion.data.source.network.dto.response.SignInResponseDto
import com.lans.foodricion.data.source.network.dto.response.SignUpResponseDto
import com.lans.foodricion.data.source.network.dto.response.UpdateProfileResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface FoodricionApi {
    @POST("/login")
    suspend fun signin(
        @Body requestDto: SignInRequestDto
    ): ApiResponse<SignInResponseDto>

    @POST("/register")
    suspend fun signup(
        @Body requestBody: SignUpRequestDto,
    ): ApiResponse<SignUpResponseDto>

    @POST("/update-profile")
    suspend fun updateProfile(
        @Body requestBody: UpdateProfileRequestDto,
    ): ApiResponse<UpdateProfileResponseDto>

    @GET("/me")
    suspend fun me(): MeResponseDto

    @POST("/refresh-token")
    suspend fun refreshToken(
        @Query("refresh_token") refreshToken: String,
    ): ApiResponse<RefreshTokenResponseDto>

    @POST("/forgot-password")
    suspend fun forgotPassword(
        @Body requestBody: ForgotPasswordRequestDto,
    ): ApiResponse<Any>

    @POST("/verify-otp")
    suspend fun verifyOTP(
        @Body requestBody: VerifyOTPRequestDto,
    ): ApiResponse<Any>

    @POST("/me/change-password")
    suspend fun changePassword(
        @Body requestBody: ChangePasswordRequestDto,
    ): ApiResponse<Any>

    @POST("/chatbot")
    suspend fun chatbot(
        @Body requestBody: ChatbotRequestDto,
    ): ApiResponse<ChatbotRequestDto>

    @GET("/chatbot-history")
    suspend fun chatbotHistory(): ApiResponse<List<ChatbotHistoryResponseDto>>

    @GET("/foods/nutrition")
    suspend fun getFood(
        @Query("food_name") foodName: String,
    ): ApiResponse<GetFoodResponseDto>

    @GET("/foods")
    suspend fun getFoods(): ApiResponse<List<GetFoodResponseDto>>
}