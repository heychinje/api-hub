package com.server.apis.data.ums.entity

data class Profile(
    val id: String,
    val userName: String,
    val credential: String,
    val nickName: String? = null,
    val email: String? = null,
    val phoneNumber: String? = null,
    val registerTime: Long
)
