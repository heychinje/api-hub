package com.server.apis.ums.entity

import com.server.apis.ums.UMSConstants
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = UMSConstants.TABLE_PROFILE)
data class Profile(
    @Id
    val userId: String,
    val nickName: String? = null,
    val email: String? = null,
    val phoneNumber: String? = null,
)
