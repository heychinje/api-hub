package com.server.apis.ums.usecase

import com.server.apis.ums.*
import com.server.apis.ums.entity.Credential
import com.server.apis.ums.entity.User
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
class LoginUserUseCase(
    private val log: Logger = LoggerFactory.getLogger("[UMS]: login: "),
    private val verifyUserNameFormatUseCase: VerifyUserNameFormatUseCase,
    private val verifyUserNameExistUseCase: VerifyUserNameExistUseCase,
    private val verifyCredentialUseCase: VerifyCredentialUseCase,
    private val verifyPasswordFormatUseCase: VerifyPasswordFormatUseCase,
    private val queryUserUseCase: QueryUserUseCase,
    private val queryCredentialUseCase: QueryCredentialUseCase,
    private val createUserEvidenceUseCase: CreateUserEvidenceUseCase,
    private val updateCredentialUseCase: UpdateCredentialUseCase,
) {
    operator fun invoke(userName: String, password: String): Result<User> {
        val timestamp = System.currentTimeMillis()

        // check username format
        verifyUserNameFormatUseCase(userName)?.let { return Result.failure(it) }

        // check username exist
        if (verifyUserNameExistUseCase(userName).not()) return Result.failure(NotFoundUserNameException)

        // check password
        verifyPasswordFormatUseCase(password)?.let { return Result.failure(it) }

        // get user
        val user = queryUserUseCase(userName).let {
            if (it.isSuccess) it.getOrThrow()
            else return Result.failure(it.exceptionOrNull() ?: UnknownException)
        }

        // check and get credential
        val credential = queryCredentialUseCase(user.userId).let {
            if (it.isSuccess) it.getOrThrow()
            else return Result.failure(it.exceptionOrNull() ?: UnknownException)
        }



        return credential.let {
            // verify password
            val isPasswordVerified = verifyCredentialUseCase(userName, password, credential)

            // update last logged in time
            if (isPasswordVerified) {
                val newEvidence = createUserEvidenceUseCase(userName, password, timestamp)
                val newCredential = it.copy(evidence = newEvidence, updateTime = timestamp)

                updateCredentialUseCase(newCredential).let { result ->
                    if (result.isSuccess) Result.success(user)
                    else Result.failure(result.exceptionOrNull() ?: UnknownException)
                }
            } else {
                Result.failure(IncorrectPasswordException)
            }
        }
    }
}