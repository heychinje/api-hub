package com.server.apis.ums

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/ums")
class UmsController {
    @Autowired
    private lateinit var ums: UserManagementService

    @GetMapping("/register")
    fun register(
        @RequestParam(value = "userName", defaultValue = "Tom") userName: String,
        @RequestParam(value = "password", defaultValue = "123") password: String
    ): String {
        println(ums)
        return ums.register(userName, password).toString()
    }

    @GetMapping("/login")
    fun login(
        @RequestParam(value = "userName", defaultValue = "Tom") userName: String,
        @RequestParam(value = "password", defaultValue = "123") password: String
    ): String {
        println(ums)
        return ums.login(userName, password).toString()
    }
}