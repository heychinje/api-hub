package com.server.apis.data.ums

import com.server.apis.extension.md5
import com.server.apis.extension.sha256
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class UserManagementServiceImpl : UserManagementService {
    private val log = LoggerFactory.getLogger("UMS")

    override fun register(userName: String, password: String): User {
        log.info("<$userName>[$password]: register new user.")
        val id = userName.sha256(userName.md5)
        val registerTime = System.currentTimeMillis()
        val token = password.sha256(userName.md5("$registerTime"))
        val profile = Profile(id, userName, null, null, null, registerTime)
        return User(id, userName, profile)
    }

    override fun login(userName: String, password: String): User {
        return User("", userName)
    }
}