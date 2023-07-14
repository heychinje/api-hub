package com.server.apis.ums.usecase

import com.server.apis.ums.entity.Credential
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component


@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
class VerifyCredentialUseCase(
    private val createUserEvidenceUseCase: CreateUserEvidenceUseCase
) {
    operator fun invoke(
        userName: String,
        password: String,
        oldCredential: Credential
    ): Boolean = createUserEvidenceUseCase(userName, password, oldCredential.updateTime) == oldCredential.evidence
}