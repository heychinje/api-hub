package com.server.apis.ums.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = UserCollectionNames.CREDENTIALS)
data class Credential(
    @Id
    val userId: String,
    val evidence: String,
    val updateTime: Long,
)
