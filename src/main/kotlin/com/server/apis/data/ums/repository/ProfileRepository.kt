package com.server.apis.data.ums.repository

import com.server.apis.data.ums.entity.Profile
import org.springframework.data.mongodb.repository.MongoRepository

interface ProfileRepository : MongoRepository<Profile, String> {
    fun findByUserId(id: String): Profile?
}