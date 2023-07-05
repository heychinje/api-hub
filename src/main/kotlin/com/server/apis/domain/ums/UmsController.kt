package com.server.apis.domain.ums

import com.server.apis.data.ums.repository.UserManagementRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UmsController {
    private companion object {
        private const val DOMAIN = "/ums"
    }

    @Autowired
    lateinit var userManagementRepository: UserManagementRepository

    @GetMapping("$DOMAIN/register")
    fun register(
        @RequestParam(value = "userName", defaultValue = "Tom") userName: String,
        @RequestParam(value = "password", defaultValue = "123") password: String
    ): String {
        return userManagementRepository.register(userName, password).toString()
    }
}