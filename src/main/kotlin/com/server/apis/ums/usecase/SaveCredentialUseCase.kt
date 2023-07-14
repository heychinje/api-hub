package com.server.apis.ums.usecase

import com.server.apis.ums.dao.CredentialDao
import com.server.apis.ums.entity.Credential
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
class SaveCredentialUseCase(
    private val credentialDao: CredentialDao,
) {
    operator fun invoke(credential: Credential): Result<Unit> = credentialDao.insert(credential)
}