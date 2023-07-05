package com.server.apis.data.ums.repository

import com.server.apis.data.ums.entity.User

interface UserManagementRepository {
    fun register(userName: String, password: String): User
    fun login(userName: String, password: String): User
}