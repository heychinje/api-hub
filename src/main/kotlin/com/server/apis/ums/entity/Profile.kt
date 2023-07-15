package com.server.apis.ums.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = UserCollectionNames.PROFILE)
data class Profile(
    @Id
    val userId: String,
    val nickName: String? = null,
    val email: String? = null,
    val phoneNumber: String? = null,
)
