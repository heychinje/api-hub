package com.server.apis.data.ums.entity

import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = UserCollectionNames.CREDENTIALS)
data class Credential(
    val userId: String,
    val token: String,
    val registerTime: Long,
    val lastModifiedTime: Long,
)
