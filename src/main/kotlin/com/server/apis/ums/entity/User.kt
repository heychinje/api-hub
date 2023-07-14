package com.server.apis.ums.entity

import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = UserCollectionNames.USERS)
data class User(
    val userId: String,
    val userName: String,
    val createTime: Long
)
