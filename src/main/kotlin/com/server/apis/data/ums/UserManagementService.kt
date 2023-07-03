package com.server.apis.data.ums

interface UserManagementService {
    fun register(userName: String, password: String): User
    fun login(userName: String, password: String): User
}