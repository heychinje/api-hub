package com.server.apis.ums.dto

import com.server.apis.authentication.entity.Status

data class SubscribedServicesDTO(
    val status: Status,
    val data: Data?
) {
    data class Data(
        val userId: String,
        val subscribedServices: List<AvailableServiceDTO>
    )
}

