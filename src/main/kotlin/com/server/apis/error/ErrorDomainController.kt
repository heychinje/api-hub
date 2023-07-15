package com.server.apis.error

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/error")
class ErrorDomainController {

    @GetMapping("/")
    fun getError(): String {
        return "Oops~~~, something went wrong, please check your url address and try again."
    }
}