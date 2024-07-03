package com.lans.foodricion.data.source.network

import com.lans.foodricion.common.Constant.HEADER_AUTHORIZATION
import com.lans.foodricion.common.Constant.TOKEN_TYPE
import com.lans.foodricion.data.source.local.DataStoreManager
import com.lans.foodricion.data.source.network.api.RefreshTokenService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    private val refreshTokenService: RefreshTokenService
) : Authenticator, SafeApiCall {
    override fun authenticate(route: Route?, response: Response): Request? {
        val currentRefreshToken = runBlocking {
            dataStoreManager.getRefreshToken().first()
        }

        synchronized(this) {
            val newToken = runBlocking {
                refreshTokenService.refreshToken(currentRefreshToken)
            }

            if (newToken.code != 200) {
                return null
            }

            newToken.let { body ->
                runBlocking {
                    dataStoreManager.storeData(
                        DataStoreManager.ACCESS_TOKEN,
                        body.data!!.accessToken
                    )
                    dataStoreManager.storeData(
                        DataStoreManager.REFRESH_TOKEN,
                        body.data.refreshToken
                    )
                    dataStoreManager.storeData(DataStoreManager.EXPIRED_AT, body.data.expiredAt)
                }

                return response.request.newBuilder()
                    .header(HEADER_AUTHORIZATION, "$TOKEN_TYPE ${body.data!!.accessToken}")
                    .build()
            }
        }
    }
}