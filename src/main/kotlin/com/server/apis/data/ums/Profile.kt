package com.server.apis.data.ums

data class Profile(
    val id: String,
    val userName: String,
    val nickName: String? = null,
    val email: String? = null,
    val phoneNumber: String? = null,
    val registerTime: Long
)
