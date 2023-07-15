package com.server.apis.ums.entity

import com.server.apis.ums.UMSConstants
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = UMSConstants.TABLE_USERS)
data class User(
    @Id
    val userId: String,
    val userName: String,
    val createTime: Long
)
