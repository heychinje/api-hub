package com.server.apis.ums.impl

import com.server.apis.ums.dao.CredentialDao
import com.server.apis.ums.dao.UserDao
import com.server.apis.ums.entity.Credential
import com.server.apis.ums.entity.User
import com.server.apis.extension.md5
import com.server.apis.extension.sha256
import com.server.apis.ums.*
import com.server.apis.ums.UserManagementService
import com.server.apis.ums.usecase.ChangePasswordUseCase
import com.server.apis.ums.usecase.LoginUserUseCase
import com.server.apis.ums.usecase.RegisterUseCase
import com.server.apis.ums.usecase.ResetPasswordUseCase
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service


@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Service
class UserManagementServiceImpl(
    private val log: Logger = LoggerFactory.getLogger("UMS"),
    private val registerUseCase: RegisterUseCase,
    private val loginUserUseCase: LoginUserUseCase,
    private val resetPasswordUseCase: ResetPasswordUseCase,
    private val changePasswordUseCase: ChangePasswordUseCase
) : UserManagementService {

    override fun register(userName: String, password: String): Result<User> = registerUseCase(userName, password)

    override fun login(userName: String, password: String): Result<Credential> = loginUserUseCase(userName, password)

    override fun resetPassword(userName: String, password: String): Result<Credential> =
        resetPasswordUseCase(userName, password)

    override fun changePassword(userName: String, oldPassword: String, newPassword: String): Result<Credential> =
        changePasswordUseCase(userName, oldPassword, newPassword)
}