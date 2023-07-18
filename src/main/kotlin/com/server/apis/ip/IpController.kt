package com.server.apis.ip

import com.fasterxml.jackson.databind.ObjectMapper
import com.server.apis.ip.usecase.ParseRealIpUseCase
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/apis")
class IpController(
    private val objectMapper: ObjectMapper = ObjectMapper(),
    private val parseRealIpUseCase: ParseRealIpUseCase
) {

    @GetMapping("/ip")
    fun parseIp(request: HttpServletRequest): String = parseRealIpUseCase(request) ?: "Cannot parse your ip"
}