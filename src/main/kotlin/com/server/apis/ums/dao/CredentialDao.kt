package com.server.apis.ums.dao

import com.server.apis.ums.entity.Credential

interface CredentialDao {
    fun insert(credential: Credential): Result<Unit>

    fun delete(credential: Credential): Result<Unit>

    fun deleteById(userId: String): Result<Unit>

    fun update(credential: Credential): Result<Unit>

    fun queryAll(): Result<List<Credential>>

    fun <V : Any?> queryBy(key: String, value: V): Result<Credential>

    fun queryById(userId: String): Result<Credential>

    fun <V : Any?> existBy(key: String, value: V): Boolean

    fun existById(userId: String): Boolean
}