package com.server.apis.ums.dto

import com.server.apis.authentication.entity.Status

data class ChangePasswordDTO(
    val status: Status,
    val data: Data?
) {
    data class Data(
        val user: User
    )
}
