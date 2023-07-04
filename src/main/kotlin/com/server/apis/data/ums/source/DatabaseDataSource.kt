package com.server.apis.data.ums.source

import com.server.apis.data.ums.entity.User
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class DatabaseDataSource:UserDataSource {
    private val log = LoggerFactory.getLogger("Database")

    override fun insert(users: List<User>) {
        log.info("insert: $users")
    }

    override fun update(users: List<User>) {
        log.info("update: $users")
    }

    override fun queryAll(): List<User> {
        log.info("query all: ")
        return listOf()
    }

    override fun queryBy(id: String?, userName: String?):List<User> {
        log.info("query by: id=$id, userName=$userName")
        return listOf()
    }

    override fun delete(users: List<User>) {
        log.info("delete: $users")
    }
}