package com.server.apis.ums.usecase

import com.server.apis.ums.dao.UserDao
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component


@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
class VerifyUserNameExistUseCase(
    private val userDao: UserDao,
) {
    operator fun invoke(userName: String): Boolean = userDao.existByName(userName)
}