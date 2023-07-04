package com.server.apis.data.ums.service

import com.server.apis.data.ums.entity.User

interface UserManagementService {
    fun register(userName: String, password: String): User
    fun login(userName: String, password: String): User
}