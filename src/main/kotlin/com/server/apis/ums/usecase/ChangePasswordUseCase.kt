package com.server.apis.ums.usecase

import com.server.apis.ums.*
import com.server.apis.ums.entity.Credential
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component


@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
class ChangePasswordUseCase(
    private val verifyUserNameFormatUseCase: VerifyUserNameFormatUseCase,
    private val verifyUserNameExistUseCase: VerifyUserNameExistUseCase,
    private val verifyPasswordFormatUseCase: VerifyPasswordFormatUseCase,
    private val verifyCredentialUseCase: VerifyCredentialUseCase,
    private val queryUserUseCase: QueryUserUseCase,
    private val queryCredentialUseCase: QueryCredentialUseCase,
    private val createUserEvidenceUseCase: CreateUserEvidenceUseCase,
    private val updateCredentialUseCase: UpdateCredentialUseCase,
) {
    operator fun invoke(userName: String, oldPassword: String, newPassword: String): Result<Credential> {
        val timestamp = System.currentTimeMillis()

        // check username format
        verifyUserNameFormatUseCase(userName)?.let { return Result.failure(it) }

        // check username exist
        if (verifyUserNameExistUseCase(userName).not()) return Result.failure(NotFoundUserNameException)

        // check password
        verifyPasswordFormatUseCase(newPassword)?.let { return Result.failure(it) }

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
            val isPasswordVerified = verifyCredentialUseCase(userName, oldPassword, credential)

            // update last logged in time
            if (isPasswordVerified) {
                val newEvidence = createUserEvidenceUseCase(userName, newPassword, timestamp)
                val newCredential = it.copy(evidence = newEvidence, updateTime = timestamp)
                updateCredentialUseCase(credential)
                Result.success(newCredential)
            } else {
                Result.failure(IncorrectPasswordException)
            }
        }
    }
}