package com.server.apis.ums.impl

import com.server.apis.ums.dao.CredentialDao
import com.server.apis.ums.entity.Credential
import com.server.apis.ums.NotFoundUserCredentialException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Component

@Component
class CredentialDaoImpl : CredentialDao {
    @Autowired
    private lateinit var mt: MongoTemplate

    override fun insert(credential: Credential): Result<Unit> = runCatching { mt.insert(credential) }

    override fun delete(credential: Credential): Result<Unit> = runCatching { mt.remove(credential) }

    override fun deleteById(userId: String): Result<Unit> = runCatching {
        Criteria.where("userId").`is`(userId).let {
            Query().apply { addCriteria(it) }
        }.let {
            mt.remove(it, Credential::class.java)
        }
    }

    override fun update(credential: Credential): Result<Unit> = runCatching {
        Criteria.where("userId").`is`(credential.userId).let {
            Query().apply { addCriteria(it) }
        }.let {
            val update = Update().apply {
                set("evidence", credential.evidence)
                set("updateTime", credential.updateTime)
            }
            mt.upsert(it, update, Credential::class.java)
        }
    }

    override fun queryAll(): Result<List<Credential>> = runCatching { mt.findAll(Credential::class.java) }

    override fun <V> queryBy(key: String, value: V): Result<Credential> = runCatching {
        Criteria.where(key).`is`(value).let {
            Query().apply { addCriteria(it) }
        }.let {
            mt.find(it, Credential::class.java).firstOrNull()
        } ?: throw NotFoundUserCredentialException
    }

    override fun queryById(userId: String): Result<Credential> = queryBy("userId", userId)

    override fun <V> existBy(key: String, value: V): Boolean = Criteria.where(key).`is`(value).let {
        Query().apply { addCriteria(it) }
    }.let {
        mt.exists(it, Credential::class.java)
    }

    override fun existById(userId: String): Boolean = existBy("userId", userId)

}