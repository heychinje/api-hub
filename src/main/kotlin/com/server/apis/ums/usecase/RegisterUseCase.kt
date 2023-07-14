package com.server.apis.ums.usecase

import com.server.apis.ums.*
import com.server.apis.ums.entity.User
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
class RegisterUseCase(
    private val verifyUserNameExistUseCase: VerifyUserNameExistUseCase,
    private val verifyUserNameFormatUseCase: VerifyUserNameFormatUseCase,
    private val verifyPasswordFormatUseCase: VerifyPasswordFormatUseCase,
    private val createUserUseCase: CreateUserUseCase,
    private val createCredentialUseCase: CreateCredentialUseCase,
    private val saveUserUseCase: SaveUserUseCase,
    private val saveCredentialUseCase: SaveCredentialUseCase
) {
    operator fun invoke(userName: String, password: String): Result<User> {
        val timestamp = System.currentTimeMillis()

        // check username format
        verifyUserNameFormatUseCase(userName)?.let { return Result.failure(it) }

        // check username exist
        if (verifyUserNameExistUseCase(userName)) return Result.failure(ExistedUserNameException)

        // check password
        verifyPasswordFormatUseCase(password)?.let { return Result.failure(it) }

        // create new user
        val user = createUserUseCase(userName, timestamp)

        // save new user
        saveUserUseCase(user)

        // create new credential
        val credential = createCredentialUseCase(user.userId, userName, password, timestamp)

        // save user's credential
        saveCredentialUseCase(credential)

        return Result.success(user)
    }
}