package com.server.apis.ums.usecase

import com.server.apis.ums.dao.SubscribeServiceDao

class AcquireSubscribedServicesUseCase(
    private val subscribeServiceDao: SubscribeServiceDao
) {
    operator fun invoke(userId: String) = subscribeServiceDao.queryAll(userId)
}