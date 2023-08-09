package com.server.apis.ums.usecase

import com.server.apis.ums.dao.SubscribeServiceDao
import com.server.apis.ums.entity.SubscribedService

class SubscribeServiceUseCase(
    private val subscribeServiceDao: SubscribeServiceDao
) {
    operator fun invoke(subscribedService: SubscribedService) = subscribeServiceDao.insert(subscribedService)
}