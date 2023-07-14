package com.server.apis.ums.usecase

import com.server.apis.extension.md5
import com.server.apis.extension.sha256
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component


@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
class CreateUserEvidenceUseCase {
    operator fun invoke(
        userName: String,
        password: String,
        timestamp: Long
    ): String = password.sha256(userName.md5("$timestamp"))
}