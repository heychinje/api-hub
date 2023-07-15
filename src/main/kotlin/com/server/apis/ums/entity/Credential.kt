package com.server.apis.ums.entity

import com.server.apis.ums.UMSConstants
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = UMSConstants.TABLE_CREDENTIALS)
data class Credential(
    @Id
    val userId: String,
    val evidence: String,
    val updateTime: Long,
)
