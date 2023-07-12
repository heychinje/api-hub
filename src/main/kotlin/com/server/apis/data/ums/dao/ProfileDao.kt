package com.server.apis.data.ums.dao

import com.server.apis.data.ums.entity.Profile

interface ProfileDao {
    fun insert(profile: Profile): Result<Unit>

    fun delete(profile: Profile): Result<Unit>

    fun deleteById(profile: String): Result<Unit>

    fun update(profile: Profile): Result<Profile>

    fun queryAll(): Result<List<Profile>>

    fun queryById(userId: String): Result<Profile>

    fun queryByName(userName: String): Result<Profile>

    fun existByName(userName: String): Boolean
}