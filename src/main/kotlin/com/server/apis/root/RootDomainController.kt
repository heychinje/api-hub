package com.server.apis.root

import com.server.apis.Constants.DOMAIN_ROOT
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(DOMAIN_ROOT)
class RootDomainController  {
    private val messages by lazy {
        javaClass.getResource("/html/index.html")?.readText() ?: "No message found"
    }

    @GetMapping
    fun getRoot(): String {
        return messages
    }
}