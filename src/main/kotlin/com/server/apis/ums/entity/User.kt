package com.server.apis.ums.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = UserCollectionNames.USERS)
data class User(
    @Id
    val userId: String,
    val userName: String,
    val createTime: Long
)
