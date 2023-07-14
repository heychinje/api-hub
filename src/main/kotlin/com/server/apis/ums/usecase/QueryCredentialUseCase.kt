package com.server.apis.ums.usecase

import com.server.apis.ums.dao.CredentialDao
import com.server.apis.ums.entity.Credential
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component


@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
class QueryCredentialUseCase(
    private val credentialDao: CredentialDao,
) {
    operator fun invoke(userId: String): Result<Credential> = credentialDao.queryById(userId)

}