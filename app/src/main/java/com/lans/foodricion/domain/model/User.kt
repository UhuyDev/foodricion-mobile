package com.lans.foodricion.domain.model

import java.util.Date

data class User(
    val id: String,
    val email: String,
    val fullname: String,
    val registrationDate: Date,
)
