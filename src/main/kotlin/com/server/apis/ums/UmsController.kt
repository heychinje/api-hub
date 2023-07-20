package com.server.apis.ums

import com.fasterxml.jackson.databind.ObjectMapper
import com.server.apis.Constants.DOMAIN_LOGIN
import com.server.apis.Constants.DOMAIN_REGISTER
import com.server.apis.Constants.DOMAIN_UMS
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(DOMAIN_UMS)
class UmsController(
    private val ums: UserManagementService,
    private val objectMapper: ObjectMapper = ObjectMapper()
) {
    @GetMapping(DOMAIN_REGISTER)
    fun register(
        @RequestParam(value = "userName", defaultValue = "Tom") userName: String,
        @RequestParam(value = "password", defaultValue = "123") password: String
    ): String {
        return ums.register(userName, password).let { objectMapper.writeValueAsString(it) }
    }

    @GetMapping(DOMAIN_LOGIN)
    fun login(
        @RequestParam(value = "userName", defaultValue = "Tom") userName: String,
        @RequestParam(value = "password", defaultValue = "123") password: String
    ): String {
        return ums.login(userName, password).let { objectMapper.writeValueAsString(it) }
    }
}