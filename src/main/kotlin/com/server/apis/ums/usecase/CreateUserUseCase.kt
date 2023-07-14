package com.server.apis.ums.usecase

import com.server.apis.extension.md5
import com.server.apis.extension.sha256
import com.server.apis.ums.entity.User
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
class CreateUserUseCase {
    operator fun invoke(userName: String, timestamp: Long): User {
        val id = userName.sha256(userName.md5)
        return User(id, userName, timestamp)
    }
}