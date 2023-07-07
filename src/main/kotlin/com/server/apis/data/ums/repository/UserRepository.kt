package com.server.apis.data.ums.repository

import com.server.apis.data.ums.entity.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, String>{
    fun findByUserName(userName: String): User?
}