package com.lans.foodricion.domain.usecase

import android.net.Uri

interface GetImageTempUriUseCase {
    fun invoke(authority: String): Uri
}