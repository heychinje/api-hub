package com.server.apis.data.ums.entity

import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
data class User(
    val id: String,
    val userName: String,
)
