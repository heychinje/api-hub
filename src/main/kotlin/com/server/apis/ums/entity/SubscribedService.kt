package com.server.apis.ums.entity

import com.server.apis.ums.UMSConstants
import org.springframework.data.mongodb.core.mapping.Document


@Document(UMSConstants.TABLE_SUBSCRIBED_SERVICES)
data class SubscribedService(
    val userId: String,
    val serviceId: String,
    val serviceName: String,
    val serviceDescription: String,
    val subscribeTime: Long,
    val serviceAccessCredential: String,
    val isBlocked: Boolean
)
