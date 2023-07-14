package com.server.apis.ums

import com.server.apis.ums.entity.Credential
import com.server.apis.ums.entity.User

interface UserManagementService {
    fun register(userName: String, password: String): Result<User>

    fun login(userName: String, password: String): Result<Credential>

    fun resetPassword(userName: String, password: String): Result<Credential>

    fun changePassword(userName: String, oldPassword: String, newPassword: String): Result<Credential>
}