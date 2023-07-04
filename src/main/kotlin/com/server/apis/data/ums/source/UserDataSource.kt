package com.server.apis.data.ums.source

import com.server.apis.data.ums.entity.User

interface UserDataSource {
    fun insert(users: List<User>)
    fun update(users: List<User>)
    fun queryAll(): List<User>
    fun queryBy(id: String? = null, userName: String? = null): List<User>
    fun delete(users: List<User>)
}