package com.server.apis.data.ums.dao

import com.server.apis.data.ums.entity.Profile

interface ProfileDao {
    fun insert(profile: Profile): Result<Unit> {
        throw Exception("Not allowed to call the interface method!!!")
    }

    fun delete(profile: Profile): Result<Unit> {
        throw Exception("Not allowed to call the interface method!!!")
    }

    fun deleteById(profile: String): Result<Unit> {
        throw Exception("Not allowed to call the interface method!!!")
    }

    fun update(profile: Profile): Result<Profile> {
        throw Exception("Not allowed to call the interface method!!!")
    }

    fun queryAll(): Result<List<Profile>> {
        throw Exception("Not allowed to call the interface method!!!")
    }

    fun queryById(userId: String): Result<Profile> {
        throw Exception("Not allowed to call the interface method!!!")
    }

    fun queryByName(userName: String): Result<Profile> {
        throw Exception("Not allowed to call the interface method!!!")
    }

    fun existByName(userName: String): Boolean{
        throw Exception("Not allowed to call the interface method!!!")
    }
}