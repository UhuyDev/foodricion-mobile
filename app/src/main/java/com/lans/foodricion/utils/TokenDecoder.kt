package com.lans.foodricion.utils

import com.auth0.android.jwt.JWT

fun decodeToken(token: String): String {
    val jwt = JWT(token)
    return jwt.getClaim("sub").asString()!!
}