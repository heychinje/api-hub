package com.server.apis.data.ums.service

import com.server.apis.data.ums.entity.Credential
import com.server.apis.data.ums.entity.User

interface UserManagementService {
    fun register(userName: String, password: String): Result<User>

    fun login(userName: String, password: String): Result<Credential>

    fun resetPassword(userName: String, password: String): Result<Credential>

    fun changePassword(userName: String, oldPassword: String, newPassword: String): Result<Credential>
}