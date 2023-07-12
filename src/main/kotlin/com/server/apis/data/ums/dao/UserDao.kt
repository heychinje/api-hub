package com.server.apis.data.ums.dao

import com.server.apis.data.ums.entity.User

interface UserDao {
    fun insert(user: User): Result<Unit> {
        throw Exception("Not allowed to call the interface method!!!")
    }

    fun delete(user: User): Result<Unit> {
        throw Exception("Not allowed to call the interface method!!!")
    }

    fun deleteById(userId: String): Result<Unit> {
        throw Exception("Not allowed to call the interface method!!!")
    }

    fun update(user: User): Result<User> {
        throw Exception("Not allowed to call the interface method!!!")
    }

    fun queryAll(): Result<List<User>> {
        throw Exception("Not allowed to call the interface method!!!")
    }

    fun queryById(userId: String): Result<User> {
        throw Exception("Not allowed to call the interface method!!!")
    }

    fun queryByName(userName: String): Result<User> {
        throw Exception("Not allowed to call the interface method!!!")
    }

    fun existByName(userName: String): Boolean {
        throw Exception("Not allowed to call the interface method!!!")
    }
}