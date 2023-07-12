package com.server.apis.data.ums.dao.impl

import com.server.apis.data.ums.dao.UserDao
import com.server.apis.data.ums.entity.User
import com.server.apis.data.ums.exception.NotFoundUserException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Component

@Component
class UserDaoImpl : UserDao {
    @Autowired
    private lateinit var mt: MongoTemplate

    override fun insert(user: User): Result<Unit> = runCatching { mt.insert(user) }

    override fun delete(user: User): Result<Unit> = runCatching { mt.remove(user) }

    override fun deleteById(userId: String): Result<Unit> = runCatching {
        Criteria.where("id").let {
            Query().apply { addCriteria(it) }
        }.let {
            mt.remove(it, User::class.java)
        }
    }

    override fun queryAll(): Result<List<User>> = runCatching { mt.findAll(User::class.java) }

    override fun <V> queryBy(key: String, value: V): Result<User> = runCatching {
        Criteria.where(key).`is`(value).let {
            Query().apply { addCriteria(it) }
        }.let {
            mt.find(it, User::class.java).firstOrNull()
        } ?: throw NotFoundUserException
    }

    override fun queryById(userId: String): Result<User> = queryBy("userId", userId)

    override fun queryByName(userName: String): Result<User> = queryBy("userName", userName)

    override fun <V> existBy(key: String, value: V): Boolean = Criteria.where(key).`is`(value).let {
        Query().apply { addCriteria(it) }
    }.let {
        mt.exists(it, User::class.java)
    }

    override fun existByName(userName: String): Boolean = existBy("userName", userName)
}