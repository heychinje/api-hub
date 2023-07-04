package com.server.apis.data.ums.entity

data class User(
    val id: String,
    val userName: String,
    val profile: Profile? = null,
)
