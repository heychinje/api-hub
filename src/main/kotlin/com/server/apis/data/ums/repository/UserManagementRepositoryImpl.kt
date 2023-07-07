//package com.server.apis.data.ums.repository
//
//import com.server.apis.data.ums.entity.Profile
//import com.server.apis.data.ums.entity.User
//import com.server.apis.extension.md5
//import com.server.apis.extension.sha256
//import org.slf4j.LoggerFactory
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.stereotype.Service
//
//@Service
//class UserManagementRepositoryImpl : UserManagementRepository {
//    private val log = LoggerFactory.getLogger("UMS")
////
////    override fun register(userName: String, password: String): User {
////        log.info("<$userName>[$password]: register new user.")
////        val id = userName.sha256(userName.md5)
////        val registerTime = System.currentTimeMillis()
////        val credential = password.sha256(userName.md5("$registerTime"))
////        val profile = Profile(id, userName, credential, null, null, null, registerTime)
////        val newUser = User(id, userName, profile)
////        userDataSource.insert(listOf(newUser))
////        return newUser
////    }
////
////    override fun login(userName: String, password: String): User {
////        return User("", userName)
////    }
//}