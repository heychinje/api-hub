package com.server.apis.ums.usecase

import com.server.apis.ums.InsecurityPasswordException
import com.server.apis.ums.InvalidPasswordException
import com.server.apis.ums.UMSException
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
class VerifyPasswordFormatUseCase {
    operator fun invoke(password: String): UMSException? = when {
        password.isEmpty() || password.isBlank() -> InvalidPasswordException
        password.isSecurity().not() -> InsecurityPasswordException
        else -> null
    }

    private fun String.isSecurity(): Boolean = this.length >= 2

}