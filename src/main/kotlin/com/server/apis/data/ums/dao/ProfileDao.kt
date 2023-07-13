package com.server.apis.data.ums.dao

import com.server.apis.data.ums.entity.Profile

interface ProfileDao {
    fun insert(profile: Profile): Result<Unit>

    fun delete(profile: Profile): Result<Unit>

    fun deleteById(profile: String): Result<Unit>

    fun update(profile: Profile): Result<Unit>

    fun queryAll(): Result<List<Profile>>

    fun <V : Any?> queryBy(key: String, value: V): Result<Profile>

    fun queryById(userId: String): Result<Profile>

    fun queryByName(userName: String): Result<Profile>

    fun <V : Any?> existBy(key: String, value: V): Boolean

    fun existByName(userName: String): Boolean
}