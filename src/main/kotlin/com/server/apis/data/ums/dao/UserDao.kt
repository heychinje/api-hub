package com.server.apis.data.ums.dao

import com.server.apis.data.ums.entity.User

interface UserDao {
    fun insert(user: User): Result<Unit>

    fun delete(user: User): Result<Unit>

    fun deleteById(userId: String): Result<Unit>

    fun queryAll(): Result<List<User>>

    fun <V : Any?> queryBy(key: String, value: V): Result<User>

    fun queryById(userId: String): Result<User>

    fun queryByName(userName: String): Result<User>

    fun <V : Any?> existBy(key: String, value: V): Boolean

    fun existByName(userName: String): Boolean
}