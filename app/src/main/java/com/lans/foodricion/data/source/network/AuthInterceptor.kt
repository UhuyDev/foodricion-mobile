package com.lans.foodricion.data.source.network

import com.lans.foodricion.common.Constant.HEADER_AUTHORIZATION
import com.lans.foodricion.common.Constant.TOKEN_TYPE
import com.lans.foodricion.data.source.local.DataStoreManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val dateStoreManager: DataStoreManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        dateStoreManager.getAccessToken().let {
            request.addHeader(HEADER_AUTHORIZATION, "$TOKEN_TYPE $it")
        }
        return chain.proceed(request.build())
    }
}