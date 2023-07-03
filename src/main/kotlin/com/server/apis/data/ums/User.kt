package com.server.apis.data.ums

data class User(
    val id: String,
    val userName: String,
    val profile: Profile? = null,
)
