package com.server.apis.ums.usecase

import com.server.apis.ums.IllegalUserNameException
import com.server.apis.ums.InvalidUserNameException
import com.server.apis.ums.UMSException
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
class VerifyUserNameFormatUseCase {
    operator fun invoke(userName: String): UMSException? = when {
        userName.isEmpty() || userName.isBlank() -> InvalidUserNameException
        userName.isLegal().not() -> IllegalUserNameException
        else -> null
    }

    private fun String.isLegal(): Boolean = !this.contains(" ")
}