package com.server.apis.data.ums.service

import com.server.apis.data.ums.dao.CredentialDao
import com.server.apis.data.ums.dao.UserDao
import com.server.apis.data.ums.entity.Credential
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
    private lateinit var credentialDao: CredentialDao

    override fun register(userName: String, password: String): Result<User> {
        // check username
        if (userName.isEmpty() || userName.isBlank()) return Result.failure(InvalidUserNameException)
        if (userName.isLegal().not()) return Result.failure(IllegalUserNameException)
        if (userDao.existByName(userName)) return Result.failure(ExistedUserNameException)

        // check password
        if (password.isEmpty() || password.isBlank()) return Result.failure(InvalidPasswordException)
        if (password.isSecurity().not()) return Result.failure(InsecurityPasswordException)

        // create new user
        val user = generateUser(userName)

        // save new user
        userDao.insert(user)

        // create new credential
        val timestamp = System.currentTimeMillis()
        val credential = generateCredential(
            userId = user.userId,
            token = generateToken(userName, password, timestamp),
            registerTime = timestamp,
            lastModifiedTime = timestamp,
        )
        credentialDao.insert(credential)

        return Result.success(user)
    }

    override fun login(userName: String, password: String): Result<Credential> {
        // check username and password format
        if (userName.isEmpty() || userName.isBlank()) return Result.failure(InvalidUserNameException)

        // check username exist
        if (userDao.existByName(userName).not()) return Result.failure(NotFoundUserNameException)

        // check and get user
        val user = userDao.queryByName(userName).getOrNull() ?: return Result.failure(NotFoundUserException)

        // check and get credential
        val credential = credentialDao.queryById(user.userId).getOrNull() ?: return Result.failure(NotFoundUserCredentialException)

        return credential.let {
            // verify password
            val isPasswordVerified = generateToken(userName, password, it.lastModifiedTime) == it.token

            // update last logged in time
            if (isPasswordVerified) {
                val timestamp = System.currentTimeMillis()
                val newToken = generateToken(userName, password, timestamp)
                val newCredential1 = it.copy(token = newToken, lastModifiedTime = timestamp)
                credentialDao.update(newCredential1)
                Result.success(newCredential1)
            } else {
                Result.failure(IncorrectPasswordException)
            }
        }
    }

    override fun resetPassword(userName: String, password: String): Result<Credential> {
        // check username
        if (userName.isEmpty() || userName.isBlank()) return Result.failure(InvalidUserNameException)
        if (userDao.existByName(userName).not()) return Result.failure(NotFoundUserNameException)

        // check password
        if (password.isEmpty() || password.isBlank()) return Result.failure(InvalidPasswordException)
        if (password.isSecurity().not()) return Result.failure(InsecurityPasswordException)

        // check and get user
        val user = userDao.queryByName(userName).getOrNull() ?: return Result.failure(NotFoundUserException)

        // check and get credential
        val credential = credentialDao.queryById(user.userId).getOrNull() ?: return Result.failure(NotFoundUserCredentialException)

        return credential.let {
            // update last logged in time
            val timestamp = System.currentTimeMillis()
            val newToken = generateToken(userName, password, timestamp)
            val newCredential = it.copy(token = newToken, lastModifiedTime = timestamp)
            credentialDao.update(newCredential)
            Result.success(newCredential)
        }
    }

    override fun changePassword(userName: String, oldPassword: String, newPassword: String): Result<Credential> {
        // check username
        if (userName.isEmpty() || userName.isBlank()) return Result.failure(InvalidUserNameException)
        if (userDao.existByName(userName).not()) return Result.failure(NotFoundUserNameException)

        // check password
        if (newPassword.isEmpty() || newPassword.isBlank()) return Result.failure(InvalidPasswordException)
        if (newPassword.isSecurity().not()) return Result.failure(InsecurityPasswordException)

        // check and get user
        val user = userDao.queryByName(userName).getOrNull() ?: return Result.failure(NotFoundUserException)

        // check and get credential
        val credential = credentialDao.queryById(user.userId).getOrNull() ?: return Result.failure(NotFoundUserCredentialException)

        return credential.let {
            // verify password
            val isPasswordVerified = generateToken(userName, oldPassword, it.lastModifiedTime) == it.token

            // update last logged in time
            if (isPasswordVerified) {
                val timestamp = System.currentTimeMillis()
                val newToken = generateToken(userName, newPassword, timestamp)
                val newCredential = it.copy(token = newToken, lastModifiedTime = timestamp)
                credentialDao.update(newCredential)
                Result.success(newCredential)
            } else {
                Result.failure(IncorrectPasswordException)
            }
        }
    }

    private fun generateUser(userName: String): User {
        val id = userName.sha256(userName.md5)
        return User(id, userName)
    }

    private fun generateCredential(userId: String, token: String, registerTime: Long, lastModifiedTime: Long, ): Credential = Credential(userId, token, registerTime, lastModifiedTime)

    private fun generateToken(userName: String, password: String, timestamp: Long): String = password.sha256(userName.md5("$timestamp"))

    private fun String.isSecurity(): Boolean = this.length >= 2

    private fun String.isLegal(): Boolean = this.contains(" ")
}