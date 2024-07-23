package com.lans.foodricion.data.source.network

import com.lans.foodricion.common.Constant.HEADER_AUTHORIZATION
import com.lans.foodricion.common.Constant.TOKEN_TYPE
import com.lans.foodricion.data.source.local.DataStoreManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = runBlocking {
            dataStoreManager.getAccessToken().first()
        }

        val request = chain.request().newBuilder()
            .addHeader(HEADER_AUTHORIZATION, "$TOKEN_TYPE $accessToken")
            .build()

        return chain.proceed(request)
    }
}