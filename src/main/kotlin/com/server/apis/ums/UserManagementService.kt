package com.server.apis.ums

import com.server.apis.ums.dto.*

interface UserManagementService {
    fun register(userName: String, password: String): RegisterDTO

    fun login(userName: String, password: String): LoginDTO

    fun resetPassword(userName: String, password: String): ResetPasswordDTO

    fun changePassword(userName: String, oldPassword: String, newPassword: String): ChangePasswordDTO

    fun subscribeService(
        userId: String,
        serviceName: String,
        serviceDescription: String? = null,
        serviceAccessCredential: String,
    ): AvailableServiceDTO

    fun fetchAvailableServices(userId: String): AvailableServiceDTO
}