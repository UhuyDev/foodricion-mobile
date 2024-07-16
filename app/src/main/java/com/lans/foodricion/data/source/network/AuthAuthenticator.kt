package com.lans.foodricion.data.source.network

import com.lans.foodricion.common.Constant.BASE_URL
import com.lans.foodricion.common.Constant.HEADER_AUTHORIZATION
import com.lans.foodricion.common.Constant.TOKEN_TYPE
import com.lans.foodricion.data.Resource
import com.lans.foodricion.data.source.local.DataStoreManager
import com.lans.foodricion.data.source.network.api.FoodricionApi
import com.lans.foodricion.data.source.network.dto.ApiResponse
import com.lans.foodricion.data.source.network.dto.response.RefreshTokenResponseDto
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : Authenticator, SafeApiCall {
    override fun authenticate(route: Route?, response: Response): Request? {
        val currentRefreshToken = runBlocking {
            dataStoreManager.getRefreshToken().first()
        }

        synchronized(this) {
            val newToken = runBlocking {
                safeCall {
                    refreshToken(currentRefreshToken)
                }
            }

            var refreshTokenResponse: RefreshTokenResponseDto? = null

            when (newToken) {
                is Resource.Success -> {
                    refreshTokenResponse = newToken.data.data
                }

                is Resource.Error -> {
                    runBlocking {
                        dataStoreManager.clear()
                    }
                    return null
                }

                else -> Unit
            }

            refreshTokenResponse.let { body ->
                runBlocking {
                    dataStoreManager.storeData(
                        DataStoreManager.ACCESS_TOKEN,
                        body!!.accessToken
                    )
                    dataStoreManager.storeData(
                        DataStoreManager.REFRESH_TOKEN,
                        body.refreshToken
                    )
                    dataStoreManager.storeData(DataStoreManager.EXPIRED_AT, body.expiredAt)
                }

                return response.request.newBuilder()
                    .header(HEADER_AUTHORIZATION, "$TOKEN_TYPE ${body!!.accessToken}")
                    .build()
            }
        }
    }

    private suspend fun refreshToken(refreshToken: String): ApiResponse<RefreshTokenResponseDto> {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        val service = retrofit.create(FoodricionApi::class.java)
        return service.refreshToken(refreshToken)
    }
}