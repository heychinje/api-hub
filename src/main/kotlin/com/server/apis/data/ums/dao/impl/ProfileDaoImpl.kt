package com.server.apis.data.ums.dao.impl

import com.server.apis.data.ums.dao.ProfileDao
import com.server.apis.data.ums.entity.Profile
import com.server.apis.data.ums.exception.NotFoundUserProfileException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Component

@Component
class ProfileDaoImpl : ProfileDao {
    @Autowired
    private lateinit var mt: MongoTemplate

    override fun insert(profile: Profile): Result<Unit> = runCatching { mt.insert(profile) }

    override fun delete(profile: Profile): Result<Unit> = runCatching { mt.remove(profile) }

    override fun deleteById(profile: String): Result<Unit> = runCatching {
        Criteria.where("userId").`is`(profile).let {
            Query().apply { addCriteria(it) }
        }.let {
            mt.remove(it, Profile::class.java)
        }
    }

    override fun update(profile: Profile): Result<Unit> = runCatching {
        Criteria.where("userId").`is`(profile.userId).let {
            Query().apply { addCriteria(it) }
        }.let {
            val update = Update().apply {
                set("userName", profile.userName)
                set("credential", profile.credential)
                set("nickName", profile.nickName)
                set("email", profile.email)
                set("phoneNumber", profile.phoneNumber)
                set("registerTime", profile.registerTime)
                set("lastLoggedInTime", profile.lastModifiedTime)
            }
            mt.upsert(it, update, Profile::class.java)
        }
    }

    override fun queryAll(): Result<List<Profile>> = runCatching { mt.findAll(Profile::class.java) }

    override fun <V> queryBy(key: String, value: V): Result<Profile> = runCatching {
        Criteria.where(key).`is`(value).let {
            Query().apply { addCriteria(it) }
        }.let {
            mt.find(it, Profile::class.java).firstOrNull()
        } ?: throw NotFoundUserProfileException
    }

    override fun queryById(userId: String): Result<Profile> = queryBy("userId", userId)

    override fun queryByName(userName: String): Result<Profile> = queryBy("userName", userName)

    override fun <V> existBy(key: String, value: V): Boolean = Criteria.where(key).`is`(value).let {
        Query().apply { addCriteria(it) }
    }.let {
        mt.exists(it, Profile::class.java)
    }

    override fun existByName(userName: String): Boolean = existBy("userName", userName)

}