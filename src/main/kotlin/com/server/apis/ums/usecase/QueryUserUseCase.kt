package com.server.apis.ums.usecase

import com.server.apis.ums.dao.UserDao
import com.server.apis.ums.entity.User
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
class QueryUserUseCase(
    private val userDao: UserDao,
) {
    operator fun invoke(userName: String): Result<User> = userDao.queryByName(userName)
}