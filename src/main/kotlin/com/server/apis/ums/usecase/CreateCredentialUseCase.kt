package com.server.apis.ums.usecase

import com.server.apis.ums.entity.Credential
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
class CreateCredentialUseCase(
    private val createUserEvidenceUseCase: CreateUserEvidenceUseCase
) {
    operator fun invoke(
        userId: String,
        userName: String,
        password: String,
        timestamp: Long,
    ): Credential {
        val evidence = createUserEvidenceUseCase(userName, password, timestamp)
        return Credential(userId, evidence, timestamp)
    }

}