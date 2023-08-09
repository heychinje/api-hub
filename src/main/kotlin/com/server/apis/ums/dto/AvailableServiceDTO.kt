package com.server.apis.ums.dto

data class AvailableServiceDTO(
    val serviceId: String,
    val serviceName: String,
    val serviceDescription: String,
    val subscribeTime: Long,
    val serviceAccessCredential: String,
    val isBlocked: Boolean
)