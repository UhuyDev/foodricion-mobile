package com.lans.foodricion.data.source.network.api

import com.lans.foodricion.data.source.network.dto.ApiResponse
import com.lans.foodricion.data.source.network.dto.request.SignUpRequestDto
import com.lans.foodricion.data.source.network.dto.response.SignInResponseDto
import com.lans.foodricion.data.source.network.dto.response.SignUpResponseDto
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface FoodricionApi {
    @FormUrlEncoded
    @POST("login")
    suspend fun signin(
        @Field("username") username: String,
        @Field("password") password: String
    ): SignInResponseDto

    @POST("register")
    suspend fun signup(
        @Body requestBody: SignUpRequestDto,
    ): ApiResponse<SignUpResponseDto>
}