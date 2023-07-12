package com.server.apis.data.ums.dao.impl

import com.server.apis.data.ums.dao.ProfileDao
import com.server.apis.data.ums.entity.Profile
import org.springframework.stereotype.Component

@Component
class ProfileDaoImpl : ProfileDao {
    override fun insert(profile: Profile): Result<Unit> {
        TODO("Not yet implemented")
    }

    override fun delete(profile: Profile): Result<Unit> {
        TODO("Not yet implemented")
    }

    override fun deleteById(profile: String): Result<Unit> {
        TODO("Not yet implemented")
    }

    override fun update(profile: Profile): Result<Profile> {
        TODO("Not yet implemented")
    }

    override fun queryAll(): Result<List<Profile>> {
        TODO("Not yet implemented")
    }

    override fun queryById(userId: String): Result<Profile> {
        TODO("Not yet implemented")
    }

    override fun queryByName(userName: String): Result<Profile> {
        TODO("Not yet implemented")
    }

    override fun existByName(userName: String): Boolean {
        TODO("Not yet implemented")
    }

}