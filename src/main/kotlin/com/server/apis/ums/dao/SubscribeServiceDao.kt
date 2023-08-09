package com.server.apis.ums.dao

import com.server.apis.ums.entity.SubscribedService

interface SubscribeServiceDao {
    fun insert(subscribedService: SubscribedService): Result<Unit>

    fun delete(subscribedService: SubscribedService): Result<Unit>

    fun deleteBy(userId: String?, serviceId: String?): Result<Unit>

    fun update(subscribedService: SubscribedService): Result<Unit>

    fun queryAll(userId: String?): Result<List<SubscribedService>>
}