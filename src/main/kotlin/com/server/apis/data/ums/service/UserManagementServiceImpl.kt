package com.server.apis.data.ums.service

import com.server.apis.data.ums.dao.ProfileDao
import com.server.apis.data.ums.dao.UserDao
import com.server.apis.data.ums.entity.Profile
import com.server.apis.data.ums.entity.User
import com.server.apis.data.ums.exception.*
import com.server.apis.extension.md5
import com.server.apis.extension.sha256
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserManagementServiceImpl : UserManagementService {
    private val log = LoggerFactory.getLogger("UMS")

    @Autowired
    private lateinit var userDao: UserDao

    @Autowired
    private lateinit var profileDao: ProfileDao

    override fun register(userName: String, password: String): Result<User> {
        log.info("<$userName>[$password]: register new user.")
        // check username
        if (userName.isEmpty() || userName.isBlank()) return Result.failure(InvalidUserNameException)
        if (userDao.existByName(userName)) return Result.failure(ExistedUserNameException)

        // check password
        if (password.isEmpty() || password.isBlank()) return Result.failure(InvalidPasswordException)
        if (password.isSecurity().not()) return Result.failure(InsecurityPasswordException)

        // create new user
        val newUser = generateUser(userName)

        // save new user
        userDao.insert(newUser)

        // create new profile
        val timestamp = System.currentTimeMillis()
        val credential = createCredential(userName, password, timestamp)
        val newProfile = generateProfile(
            userId = newUser.id,
            userName = newUser.userName,
            credential = credential,
            registerTime = timestamp,
            lastLoggedInTime = timestamp,
        )
        profileDao.insert(newProfile)

        return Result.success(newUser)
    }

    override fun login(userName: String, password: String): Result<Profile> {
        // check username and password format
        if (userName.isEmpty() || userName.isBlank()) return Result.failure(InvalidUserNameException)
        if (password.isEmpty() || password.isBlank()) return Result.failure(InvalidPasswordException)

        // check user exist
        if (profileDao.existByName(userName).not()) return Result.failure(NotFoundUserNameException)

        // validate password
        val profile = profileDao.queryByName(userName).getOrNull() ?: return Result.failure(NotFoundUserProfileException)

        return profileDao.queryById(profile.userId).getOrNull()?.let {
            if (createCredential(it.userName, password, it.lastLoggedInTime) == it.credential) {
                val timestamp = System.currentTimeMillis()
                val newCredential = createCredential(it.userName, password, timestamp)
                val newProfile = it.copy(credential = newCredential, lastLoggedInTime = timestamp)
                profileDao.update(newProfile)
                Result.success(newProfile)
            } else {
                Result.failure(IncorrectPasswordException)
            }
        } ?: Result.failure(NotFoundUserProfileException)
    }

    private fun generateUser(userName: String): User {
        val id = userName.sha256(userName.md5)
        return User(id, userName)
    }

    private fun generateProfile(
        userId: String,
        userName: String,
        credential: String,
        registerTime: Long,
        lastLoggedInTime: Long,
        nickName: String? = null,
        email: String? = null,
        phoneNumber: String? = null,
    ): Profile = Profile(userId, userName, credential, nickName, email, phoneNumber, registerTime, lastLoggedInTime)

    private fun createCredential(userName: String, password: String, timestamp: Long): String =
        password.sha256(userName.md5("$timestamp"))

    private fun String.isSecurity(): Boolean {
        return this.length >= 2
    }
}