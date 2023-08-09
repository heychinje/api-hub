package com.server.apis.ums.impl

import com.server.apis.ums.dao.SubscribeServiceDao
import com.server.apis.ums.entity.SubscribedService
import org.springframework.stereotype.Component

@Component
class SubscribeServiceDaoImpl:SubscribeServiceDao {
    override fun insert(subscribedService: SubscribedService): Result<Unit> {
        TODO("Not yet implemented")
    }

    override fun delete(subscribedService: SubscribedService): Result<Unit> {
        TODO("Not yet implemented")
    }

    override fun deleteBy(userId: String?, serviceId: String?): Result<Unit> {
        TODO("Not yet implemented")
    }

    override fun update(subscribedService: SubscribedService): Result<Unit> {
        TODO("Not yet implemented")
    }

    override fun queryAll(userId: String?): Result<List<SubscribedService>> {
        TODO("Not yet implemented")
    }
}