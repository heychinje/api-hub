package com.server.apis.data.ums.entity

import org.springframework.data.mongodb.core.mapping.Document

@Document(collation = "users-profile")
data class Profile(
    val userId: String,
    val userName: String,
    val credential: String,
    val nickName: String? = null,
    val email: String? = null,
    val phoneNumber: String? = null,
    val registerTime: Long,
    val lastLoggedInTime: Long,
)
